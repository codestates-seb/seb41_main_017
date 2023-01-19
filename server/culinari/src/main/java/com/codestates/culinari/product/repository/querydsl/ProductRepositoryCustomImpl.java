package com.codestates.culinari.product.repository.querydsl;

import com.codestates.culinari.product.entitiy.Product;
import com.codestates.culinari.product.entitiy.QProduct;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static com.codestates.culinari.product.entitiy.QProduct.product;

@Slf4j
public class ProductRepositoryCustomImpl extends QuerydslRepositorySupport implements ProductRepositoryCustom {

    public ProductRepositoryCustomImpl() {
        super(Product.class);
    }

    @Override
    public Page<Product> findAllWithSortAndFilter(List<String> category, List<String> brand, Pageable pageable) throws UnsupportedEncodingException {
        QProduct product = QProduct.product;

        List<Product> products =
                from(product)
                        .select(product)
                        .where(eqCategoryCode(category),(eqBrand(brand)))
                        .orderBy(productSort(pageable))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetch();

        return new PageImpl<>(products);
    }

    private BooleanExpression eqCategoryCode(List<String> category){
        return category != null ? product.categoryDetail.category.categoryCode.in(category) : null;
    }
    private BooleanExpression eqBrand(List<String> brand){
        return brand != null ? product.brand.in(brand) : null;
    }

    private OrderSpecifier<?> productSort(Pageable pageable) {
        if (!pageable.getSort().isEmpty()) {
            for (Sort.Order order : pageable.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;

                switch (order.getProperty()) {
                    case "id":
                        return new OrderSpecifier<>(direction, product.createdAt);

                    case "price":
                        return new OrderSpecifier<>(direction, product.price);
                }
            }
        }
        return null;
    }
}

