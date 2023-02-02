package com.codestates.culinari.user.dto.response;

public record MyPageCountResponse(
        Long shippingCount,
        Long orderCount,
        Long productLikeCount,
        Long frequentlyOrderedProductCount
) {
    public static MyPageCountResponse of(Long shippingCount, Long orderCount, Long productLikeCount, Long frequentlyOrderedProductCount) {
        return new MyPageCountResponse(shippingCount, orderCount, productLikeCount, frequentlyOrderedProductCount);
    }
}
