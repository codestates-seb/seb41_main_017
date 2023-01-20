package com.codestates.culinari.product.service.impl;

import com.codestates.culinari.global.search.SearchFilter;
import com.codestates.culinari.product.entitiy.CategoryDetail;
import com.codestates.culinari.product.entitiy.Product;
import com.codestates.culinari.product.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 상품 페이지네이션")
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl sut;

    @Mock
    ProductRepository productRepository;

    @Mock
    SearchFilter searchFilter;


    @DisplayName("[READ] 상품 Id를 입력하면, 상품을 반환한다")
    @Test
    void givenProductId_whenReadingProduct_thenReturnProduct() {
        // Given
        long productId = 1L;
        Product product = createProduct(1L);

        given(productRepository.findById(productId)).willReturn(Optional.of(product));
        // When

        sut.findProduct(productId);

        // Then
        then(productRepository).should().findById(productId);
    }

    @DisplayName("[READ] 정렬타입과 페이지 정보를 입력하면, 신상품 페이지를 반환한다")
    @Test
    void givenSortTypeAndPageInfo_whenReadingProduct_thenReturnNewestProductPage() throws UnsupportedEncodingException {
        // Given
        String sortType = "lower";
        String filter = "category:001";
        List<String> categoryList = new ArrayList<>();
        List<String> brandList = new ArrayList<>();


        Pageable pageable = PageRequest.of(0,15,Sort.by("price"));

        given(productRepository.findAllWithSortAndFilter(categoryList,brandList,pageable)).willReturn(Page.empty());
        // When

        sut.readProductWithSortedType(sortType,filter, pageable);

        // Then
        then(productRepository).should().findAllWithSortAndFilter(categoryList,brandList,pageable);
    }

    @DisplayName("[READ] 카테고리 코드와 페이지 정보를 입력하면, 카테고리 페이지를 반환한다")
    @Test
    void givenCategoryCodeAndPageInfo_whenReadingProduct_thenReturnCategoryPage() {
        // Given
        String categoryCode = "001";
        String sortType = "newest";
        Pageable pageable = PageRequest.of(0,15, Sort.by("id").descending());

        given(productRepository.findAllByCategoryDetailCategoryDetailCodeContaining(categoryCode,pageable)).willReturn(Page.empty());
        // When

        sut.readProductWithCategoryCode(categoryCode, sortType, pageable);

        // Then
        then(productRepository).should().findAllByCategoryDetailCategoryDetailCodeContaining(categoryCode,pageable);

    }

    private static Product createProduct(Long productId) {
        Product product = Product.of(
                CategoryDetail.of("세부 카테고리 명", "세부 카테고리 코드"),
                "상품 명",
                "상품 설명",
                BigDecimal.valueOf(1000L),
                "운송 방식",
                "브랜드",
                "판매자",
                "포장방식",
                "판매 단위",
                "중량",
                "원산지",
                "알레르기 정보"
        );
        ReflectionTestUtils.setField(product, "id", productId);

        return product;
    }
}