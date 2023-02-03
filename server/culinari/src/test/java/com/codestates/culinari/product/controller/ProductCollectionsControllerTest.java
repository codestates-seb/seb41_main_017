package com.codestates.culinari.product.controller;

import com.codestates.culinari.pagination.service.PaginationService;
import com.codestates.culinari.product.dto.ProductDto;
import com.codestates.culinari.product.entitiy.CategoryDetail;
import com.codestates.culinari.product.entitiy.Product;
import com.codestates.culinari.product.service.ProductService;
import config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.LongStream;

import static com.codestates.culinari.order.Stub.Stub.createPrincipal;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("컨트롤러 - 신상품")
@Import(TestSecurityConfig.class)
@WebMvcTest(ProductCollectionsController.class)
class ProductCollectionsControllerTest {

    private final MockMvc mvc;

    @MockBean
    private ProductService productService;
    @MockBean
    private PaginationService paginationService;


    public ProductCollectionsControllerTest(@Autowired MockMvc mvc){this.mvc = mvc;}

    @DisplayName("[GET] 신상품 페이지 조회 - 정상호출")
    @Test
    void givenPageInfoAndSortType_whenRequestProduct_thenReturnNewestProductPage() throws Exception {
        // Given

        Authentication auth = new UsernamePasswordAuthenticationToken(createPrincipal("사용자", 1L, 1L), null);
        Page<ProductDto> productDtoPage = createProductPage().map(ProductDto::from);

        given(productService.readProductWithSortedType(anyString(),anyString(), any(Pageable.class))).willReturn(productDtoPage);
        given(paginationService.getPaginationBarNumbers(anyInt(), anyInt())).willReturn(List.of(0,1,2));

        // When & Then
        mvc.perform(get("/collections/newproduct")
                        .with(authentication(auth))
                        .param("sortedType", "newest")
                        .param("filter","category:001")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        then(productService).should().readProductWithSortedType(anyString(),anyString(),any(Pageable.class));
        then(paginationService).should().getPaginationBarNumbers(anyInt(), anyInt());
    }

    public static Page<Product> createProductPage() {
        List<Product> products = LongStream.rangeClosed(1L, 5L)
                .mapToObj(l -> createProduct(l))
                .toList();
        Pageable pageable = PageRequest.of(0, 10);

        return new PageImpl<>(products, pageable, products.size());
    }
    public static Product createProduct(Long productId) {
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
