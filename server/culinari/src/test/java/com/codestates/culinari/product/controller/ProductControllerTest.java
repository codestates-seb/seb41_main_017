//package com.codestates.culinari.product.controller;
//
//import com.codestates.culinari.config.security.dto.CustomPrincipal;
//import com.codestates.culinari.order.Stub.Stub;
//import com.codestates.culinari.product.dto.request.ProductInquiryRequest;
//import com.codestates.culinari.product.dto.request.ProductReviewLikeRequest;
//import com.codestates.culinari.product.dto.request.ProductReviewRequest;
//import com.codestates.culinari.product.dto.response.ProductResponse;
//import com.codestates.culinari.product.entitiy.CategoryDetail;
//import com.codestates.culinari.product.entitiy.Product;
//import com.codestates.culinari.product.service.ProductCsService;
//import com.codestates.culinari.product.service.ProductService;
//import com.codestates.culinari.user.entitiy.Profile;
//import com.codestates.culinari.user.service.ProfileService;
//import config.TestSecurityConfig;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.util.ReflectionTestUtils;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.FileInputStream;
//import java.math.BigDecimal;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.codestates.culinari.order.Stub.Stub.createPrincipal;
//import static com.codestates.culinari.order.Stub.Stub.createProfile;
//import static org.mockito.BDDMockito.*;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@DisplayName("컨트롤러 - 상품 상세")
//@Import(TestSecurityConfig.class)
//@WebMvcTest(ProductController.class)
//class ProductControllerTest {
//
//    private final MockMvc mvc;
//    @MockBean
//    private ProductCsService productCsService;
//
//    @MockBean
//    private ProductService productService;
//
//    @MockBean
//    private ProfileService profileService;
//
//    public ProductControllerTest(@Autowired MockMvc mvc){this.mvc = mvc;}
//
//    @DisplayName("[GET] 상품 단건 조회 - 정상호출")
//    @Test
//    void givenNothing_whenRequestGetProduct_thenReturnProduct() throws Exception {
//        // Given
//        long productId = 1L;
//        Product product = Stub.createProduct(1L);
//        ProductResponse response = ProductResponse.from(product);
//        given(productService.readProductWithCS(productId)).willReturn(response);
//        // When
//        mvc.perform(get("/product/{product-id}", productId)
//                        .accept(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().isOk());
//        // Then
//        then(productService).should().readProductWithCS(anyLong());
//    }
//
//    @DisplayName("[POST] 상품 문의 등록 - 정상호출")
//    @WithMockUser
//    @Test
//    void givenProductInquiry_whenRequesting_thenSavingNewProductInquiry() throws Exception {
//        // Given
//        Authentication auth = new UsernamePasswordAuthenticationToken(createPrincipal("사용자 명", 1L, 1L), null);
//        long productId = 1L;
//        String requestBody = """
//                {
//                 "title": "문의 확인",
//                 "content": "문의 내용"
//                }
//                """;
//
//        willDoNothing().given(productCsService).createProductInquiry(any(ProductInquiryRequest.class), any(CustomPrincipal.class), anyLong());
//        // When
//        mvc.perform(post("/product/"+productId+"/inquiry")
//                        .with(authentication(auth))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestBody)
//
//                )
//                .andExpect(status().isResetContent());
//        // Then
//        then(productCsService).should().createProductInquiry(any(ProductInquiryRequest.class), any(CustomPrincipal.class), anyLong());
//    }
//
//    //TODO content-type 관련 해결하기
//    @DisplayName("[POST] 상품 후기 등록 - 정상호출")
//    @Test
//    void givenProductReview_whenRequesting_thenSavingNewReview() throws Exception {
//        // Given
//        Authentication auth = new UsernamePasswordAuthenticationToken(createPrincipal("사용자 명", 1L, 1L), null);
//        long productId = 1L;
//        String requestBody = """
//                {
//                 "title": "후기 확인",
//                 "content": "후기 내용",
//                 "reviewStar": 1
//                }
//                """;
//        String fileName = "testImage";
//        String filePath = "src/test/resources/testImage/" + fileName + ".jpeg";
//        FileInputStream fileInputStream = new FileInputStream(filePath);
//        MockMultipartFile image = new MockMultipartFile(
//                "images",
//                "testImage.jpeg",
//                MediaType.IMAGE_JPEG_VALUE,
//                fileInputStream
//        );
//        MockMultipartFile request = new MockMultipartFile(
//                "request",
//                requestBody,
//                MediaType.APPLICATION_JSON_VALUE, //contentType
//                requestBody.getBytes(StandardCharsets.UTF_8)
//        );
//        List<MultipartFile> images = new ArrayList<>();
//        images.add(image);
//        Product product = createProduct(productId);
//        Profile profile = createProfile(1L);
//
//        willDoNothing().given(productCsService).createProductReview(any(ProductReviewRequest.class), any(CustomPrincipal.class), anyLong(),anyList());
//        // When
//        mvc.perform(multipart("/product/" + productId + "/review")
//                        .file(image)
//                        .file(request)
//                        .with(authentication(auth))
////                        .contentType(MediaType.MULTIPART_MIXED_VALUE)
//
//                )
//                .andExpect(status().isResetContent());
//        // Then
//        then(productCsService).should().createProductReview(any(ProductReviewRequest.class), any(CustomPrincipal.class), anyLong(),anyList());
//    }
//
//    @DisplayName("[PATCH] 상품 문의 수정 - 정상호출")
//    @Test
//    void givenProductInquiryUpdate_whenRequesting_thenUpdatingProductInquiry() throws Exception {
//        // Given
//        Authentication auth = new UsernamePasswordAuthenticationToken(createPrincipal("사용자 명", 1L, 1L), null);
//        long inquiryId = 1L;
//        String requestBody = """
//                {
//                 "title": "문의 확인 수정",
//                 "content": "문의 내용 수정"
//                }
//                """;
//        willDoNothing().given(productCsService).updateProductInquiry(any(ProductInquiryRequest.class), any(CustomPrincipal.class), anyLong());
//
//        // When
//        mvc.perform(patch("/product/inquiry/{inquiry-id}",inquiryId)
//                        .with(authentication(auth))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestBody)
//                )
//                .andExpect(status().isResetContent());
//        // Then
//        then(productCsService).should().updateProductInquiry(any(ProductInquiryRequest.class), any(CustomPrincipal.class), anyLong());
//    }
////TODO 이미지 업로드 테스트 추가
//    @DisplayName("[PATCH] 상품 후기 수정 - 정상호출")
//    @Test
//    void givenProductReviewUpdate_whenRequesting_thenUpdatingProductReview() throws Exception {
//        // Given
//        Authentication auth = new UsernamePasswordAuthenticationToken(createPrincipal("사용자 명", 1L, 1L), null);
//        long reviewId = 1L;
//        String requestBody = """
//                {
//                 "title": "리뷰 확인 수정",
//                 "content": "리뷰 내용 수정"
//                }
//                """;
//        willDoNothing().given(productCsService).updateProductReview(any(ProductReviewRequest.class), any(CustomPrincipal.class), anyLong());
//
//        // When & Then
//        mvc.perform(patch("/product/review/{review-id}" ,reviewId)
//                        .with(authentication(auth))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestBody)
//                )
//                .andExpect(status().isResetContent());
//
//        then(productCsService).should().updateProductReview(any(ProductReviewRequest.class), any(CustomPrincipal.class), anyLong());
//    }
//
//    @DisplayName("[PATCH] 상품 후기 좋아요 - 정상호출")
//    @Test
//    void givenProductReviewLike_whenRequesting_thenUpdatingProductReviewLike() throws Exception {
//        // Given
//        Authentication auth = new UsernamePasswordAuthenticationToken(createPrincipal("사용자 명", 1L, 1L), null);
//        long reviewId = 1L;
//        String requestBody = """
//                {
//                    "like": 1
//                }
//                """;
//        willDoNothing().given(productCsService).updateLike(any(ProductReviewLikeRequest.class), any(CustomPrincipal.class), anyLong());
//
//        // When & Then
//        mvc.perform(patch("/product/review/{reviewId}/like",reviewId)
//                        .with(authentication(auth))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestBody)
//                )
//                .andExpect(status().isResetContent());
//        then(productCsService).should().updateLike(any(ProductReviewLikeRequest.class), any(CustomPrincipal.class), anyLong());
//    }
//
//    @DisplayName("[DELETE] 상품 문의 삭제 - 정상호출")
//    @Test
//    void givenProductInquiryId_whenRequesting_thenDeletingProductInquiry() throws Exception {
//        // Given
//        Authentication auth = new UsernamePasswordAuthenticationToken(createPrincipal("사용자 명", 1L, 1L), null);
//        long productId = 1L;
//        Long InquiryId = 1L;
//        willDoNothing().given(productCsService).deleteProductInquiry(any(CustomPrincipal.class),anyLong());
//
//        // When
//        mvc.perform(delete("/product/inquiry/{inquiry-id}", InquiryId)
//                        .with(authentication(auth))
//                )
//                .andExpect(status().isNoContent()
//                );
//
//        // Then
//        then(productCsService).should().deleteProductInquiry(any(CustomPrincipal.class),anyLong());
//    }
//
//    @DisplayName("[DELETE] 상품 후기 삭제 - 정상호출")
//    @Test
//    void given_when_then() throws Exception {
//        // Given
//        Authentication auth = new UsernamePasswordAuthenticationToken(createPrincipal("사용자 명", 1L, 1L), null);
//        Long reviewId = 1L;
//        willDoNothing().given(productCsService).deleteProductReview(any(CustomPrincipal.class),anyLong());
//
//        // When
//        mvc.perform(delete("/product/review/{review-id}", reviewId)
//                        .with(authentication(auth)))
//                .andExpect(status().isNoContent());
//
//        // Then
//        then(productCsService).should().deleteProductReview(any(CustomPrincipal.class),anyLong());
//    }
//
//    public static Product createProduct(Long productId) {
//        Product product = Product.of(
//                CategoryDetail.of("세부 카테고리 명", "세부 카테고리 코드"),
//                "상품 명",
//                "상품 설명",
//                BigDecimal.valueOf(1000L),
//                "운송 방식",
//                "브랜드",
//                "판매자",
//                "포장방식",
//                "판매 단위",
//                "중량",
//                "원산지",
//                "알레르기 정보"
//        );
//        ReflectionTestUtils.setField(product, "id", productId);
//        return product;
//    }
//}
