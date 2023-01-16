package com.codestates.culinari.product.service.impl;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.product.dto.request.ProductInquiryRequest;
import com.codestates.culinari.product.dto.request.ProductReviewLikeRequest;
import com.codestates.culinari.product.dto.request.ProductReviewRequest;
import com.codestates.culinari.product.entitiy.*;
import com.codestates.culinari.product.repository.ProductInquiryRepository;
import com.codestates.culinari.product.repository.ProductRepository;
import com.codestates.culinari.product.repository.ProductReviewLikeRepository;
import com.codestates.culinari.product.repository.ProductReviewRepository;
import com.codestates.culinari.user.constant.GenderType;
import com.codestates.culinari.user.entitiy.Profile;
import com.codestates.culinari.user.repository.ProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 상품 상세")
@ExtendWith(MockitoExtension.class)
class ProductCsServiceImplTest {

    @InjectMocks
    private ProductCsServiceImpl sut;
    @Mock
    private ProfileRepository profileRepository;
    @Mock
    ProductRepository productRepository;
    @Mock
    private ProductInquiryRepository productInquiryRepository;
    @Mock
    private ProductReviewRepository productReviewRepository;
    @Mock
    private ProductReviewLikeRepository productReviewLikeRepository;


    @DisplayName("[CREATE] 상품 문의를 입력하면, 문의를 등록한다.")
    @Test
    void givenProductInquiryData_whenSavingProductInquiry_thenSaveProductInquiry() {
        // Given
        Long productId = 1L;
        ProductInquiryRequest productInquiryRequest = createProductInquiryRequest();
        CustomPrincipal principal = createPrincipal("사용자", 1L, 1L);

        given(productRepository.findById(anyLong())).willReturn(Optional.of(createProduct(1L)));
        given(profileRepository.getReferenceById(anyLong())).willReturn(createProfile(1L));
        given(productInquiryRepository.save(any(ProductInquiry.class))).willReturn(any(ProductInquiry.class));

        // When
        sut.createProductInquiry(productInquiryRequest,principal,productId);

        // Then
        then(productRepository).should().findById(anyLong());
        then(profileRepository).should().getReferenceById(anyLong());
        then(productInquiryRepository).should().save(any(ProductInquiry.class));
    }

    @DisplayName("[CREATE] 상품 리뷰를 입력하면, 문의를 등록한다.")
    @Test
    void givenProductReview_whenSavingProductReview_thenSaveReview() {
        // Given
        Long productId = 1L;
        ProductReviewRequest productReviewRequest = createProductReviewRequest();
        CustomPrincipal principal = createPrincipal("사용자", 1L, 1L);

        given(productRepository.findById(anyLong())).willReturn(Optional.of(createProduct(1L)));
        given(profileRepository.getReferenceById(anyLong())).willReturn(createProfile(1L));
        given(productReviewRepository.save(any(ProductReview.class))).willReturn(any(ProductReview.class));

        // When
        sut.createProductReview(productReviewRequest,principal,productId);

        // Then
        then(productRepository).should().findById(anyLong());
        then(profileRepository).should().getReferenceById(anyLong());
        then(productReviewRepository).should().save(any(ProductReview.class));
    }

    @DisplayName("[UPDATE] 상품 문의를 입력하면, 문의를 수정한다.")
    @Test
    void givenProductInquiryData_whenSavingProductInquiry_thenUpdateProductInquiry() {
        // Given
        Long productInquiryId = 1L;
        ProductInquiryRequest productInquiryRequest = createProductInquiryRequest();
        CustomPrincipal principal = createPrincipal("사용자", 1L, 1L);

        given(productInquiryRepository.findById(anyLong())).willReturn(Optional.of(createInquiry(1L,"제목","내용",1L,1L)));
        // When
        sut.updateProductInquiry(productInquiryRequest,principal,productInquiryId);

        // Then
        then(productInquiryRepository).should().findById(anyLong());
    }

    @DisplayName("[UPDATE] 상품 리뷰를 입력하면, 리뷰를 수정한다.")
    @Test
    void givenProductReview_whenSavingProductReview_thenUpdateReview() {
        // Given
        Long productReviewId = 1L;
        ProductReviewRequest productReviewRequest = createProductReviewRequest();
        CustomPrincipal principal = createPrincipal("사용자", 1L, 1L);

        given(productReviewRepository.findById(anyLong())).willReturn(Optional.of(createReview(1L,"제목","내용",1L,1L)));
        // When
        sut.updateProductReview(productReviewRequest,principal,productReviewId);

        // Then
        then(productReviewRepository).should().findById(anyLong());
    }

    @DisplayName("[DELETE] 문의 Id를 입력하면, 문의를 삭제한다")
    @Test
    void givenProductInquiryId_whenDeletingProductInquiry_thenDeleteProductInquiry() {
        // Given
        Long inquiryId = 1L;
        CustomPrincipal customPrincipal = createPrincipal("사용자", 1L, 1L);

//        given(productReviewRepository.findById(anyLong())).willReturn(Optional.of(createReview(reviewId, "제목", "내용", 1L, 1L)));
        willDoNothing().given(productInquiryRepository).deleteById(anyLong());
        // When
        sut.deleteProductInquiry(customPrincipal,inquiryId);

        // Then
//        then(productReviewRepository).should().findById(anyLong());
        then(productInquiryRepository).should().deleteById(anyLong());
    }

    @DisplayName("[DELETE] 리뷰 Id를 입력하면, 리뷰를 삭제한다")
    @Test
    void givenProductReviewId_whenDeletingProductReview_thenDeleteProductReview() {
        // Given
        Long reviewId = 1L;
        CustomPrincipal customPrincipal = createPrincipal("사용자", 1L, 1L);

//        given(productReviewRepository.findById(anyLong())).willReturn(Optional.of(createReview(reviewId, "제목", "내용", 1L, 1L)));
        willDoNothing().given(productReviewRepository).deleteById(anyLong());
        // When
        sut.deleteProductReview(customPrincipal,reviewId);

        // Then
//        then(productReviewRepository).should().findById(anyLong());
        then(productReviewRepository).should().deleteById(anyLong());
    }
    @DisplayName("[UPDATE] 상품 리뷰 좋아요를 입력하면, 좋아요를 수정한다.")
    @Test
    void givenProductReviewLike_whenSavingProductReviewLike_thenUpdateProductReviewLike() {
        // Given
        Long productReviewId = 1L;
        ProductReviewLikeRequest productReviewLikeRequest = createProductReviewLikeRequest();
        CustomPrincipal principal = createPrincipal("사용자", 1L, 1L);

        given(productReviewRepository.findById(anyLong())).willReturn(Optional.of(createReview(1L)));
        given(productReviewLikeRepository.getReferenceById(productReviewId)).willReturn(createReviewLike(1L,1L));

        // When

        sut.updateLike(productReviewLikeRequest,principal,productReviewId);

        // Then

        then(productReviewRepository).should().findById(anyLong());
    }

    @DisplayName("[UPDATE] 리뷰가 없을 때, 상품 리뷰 좋아요를 입력하면 예외를 던진다")
    @Test
    void givenProductReviewLike_whenSavingProductReviewLike_thenThrowsException() {
        // Given
        Long productReviewId = 1L;
        ProductReviewLikeRequest productReviewLikeRequest = createProductReviewLikeRequest();
        CustomPrincipal principal = createPrincipal("사용자", 1L, 1L);

        // When
        Throwable t = catchThrowable(()->sut.updateLike(productReviewLikeRequest,principal,productReviewId));

        // Then
        assertThat(t)
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("리뷰가 없습니다");
        then(productReviewRepository).should().findById(anyLong());
    }

    public static CustomPrincipal createPrincipal(String username, Long userId, Long profileId) {
        return CustomPrincipal.of(username, userId, profileId);
    }

    private static ProductInquiry createInquiry(Long inquiryId, String title, String content, Long productId,Long profileId){
        ProductInquiry productInquiry = ProductInquiry.of(title, content, createProduct(productId), createProfile(profileId));
        ReflectionTestUtils.setField(productInquiry,"id", inquiryId);
        return productInquiry;
    }

    private static ProductReview createReview(Long reviewId, String title, String content, Long productId,Long profileId){
        ProductReview productReview = ProductReview.of(title, content, createProduct(productId), createProfile(profileId));
        ReflectionTestUtils.setField(productReview,"id", reviewId);
        return productReview;
    }

    private static ProductReviewLike createReviewLike(Long reviewId, Long likeNum){
        ProductReviewLike productReviewLike = ProductReviewLike.of(likeNum,createReview(reviewId));
        ReflectionTestUtils.setField(productReviewLike,"id", reviewId);
        return productReviewLike;
    }


    private ProductInquiryRequest createProductInquiryRequest(){
        return createProductInquiryRequest(1L, "제목", "내용");
    }
    private ProductReviewRequest createProductReviewRequest(){
        return createProductReviewRequest(1L, "제목", "내용");
    }
    private ProductReviewLikeRequest createProductReviewLikeRequest(){
        return createProductReviewLikeRequest(1L, 1L, 1L);
    }

    private ProductInquiryRequest createProductInquiryRequest(Long productId, String title, String content){
        return ProductInquiryRequest.of(productId, title, content);
    }
    public static ProductReviewRequest createProductReviewRequest(Long productId, String title, String content){
        return ProductReviewRequest.of(productId, title, content);
    }
    public static ProductReviewLikeRequest createProductReviewLikeRequest(Long reviewId, Long profileId, Long like){
        return ProductReviewLikeRequest.of(reviewId,profileId,like);
    }

    private static ProductReview createReview(Long reviewId) {
        ProductReview productReview = ProductReview.of(
                "제목",
                "내용",
                createProduct(1L),
                createProfile(1L)
        );
        ReflectionTestUtils.setField(productReview, "id", reviewId);

        return productReview;
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

    private static Profile createProfile(Long profileId) {
        Profile profile = Profile.of(
                "사용자 명",
                "email@email.com",
                "010-0000-0000",
                BigDecimal.valueOf(0L),
                "주소",
                GenderType.MAN,
                LocalDate.now()
        );
        ReflectionTestUtils.setField(profile, "id", profileId);

        return profile;
    }
}
