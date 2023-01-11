package com.codestates.culinari.user.dto.response;

import com.codestates.culinari.product.dto.ProductInquiryDto;
import com.codestates.culinari.user.dto.ProfileDto;

import java.util.List;

public record ProfileMyPageInquiryResponse(
        Long id,
        List<ProductInquiryDto> inquiry
) {

    private static ProfileMyPageInquiryResponse of(Long id,List<ProductInquiryDto> inquiry) {
        return new ProfileMyPageInquiryResponse(id, inquiry);
    }

    public static ProfileMyPageInquiryResponse from(ProfileDto dto){
        return ProfileMyPageInquiryResponse.of(
                dto.id(),
                dto.productInquiryDtos()
        );
    }
}
