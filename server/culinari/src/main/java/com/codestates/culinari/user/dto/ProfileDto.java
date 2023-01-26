package com.codestates.culinari.user.dto;

import com.codestates.culinari.product.dto.ProductInquiryDto;
import com.codestates.culinari.product.dto.ProductReviewDto;
import com.codestates.culinari.user.constant.GenderType;
import com.codestates.culinari.user.dto.request.SignUpDto;
import com.codestates.culinari.user.entitiy.Profile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record ProfileDto(
        Long id,
        String name,
        String email,
        String phoneNumber,
        BigDecimal point,
        GenderType gender,
        LocalDate birthDate,
        List<ProductInquiryDto> productInquiryDtos,

        List<ProductReviewDto> productReviewDtos
) {

    public static ProfileDto of(SignUpDto signUpDto) {
        return new ProfileDto(
                null,
                signUpDto.name(),
                signUpDto.email(),
                signUpDto.phoneNumber(),
                null,
                signUpDto.genderType(),
                signUpDto.birthDate(),
                null,
                null
        );
    }

    public static ProfileDto of(Long id, String name, String email, String phoneNumber, BigDecimal point, GenderType gender, LocalDate birthDate) {
        return new ProfileDto(id, name, email, phoneNumber, point, gender, birthDate, null, null);
    }

    public static ProfileDto from(Profile entity){
        return new ProfileDto(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                entity.getPoint(),
                entity.getGender(),
                entity.getBirthDate(),
                entity.getProductInquiry().stream()
                        .map(ProductInquiryDto::from)
                        .collect(Collectors.toList()),
                entity.getProductReview().stream()
                        .map(ProductReviewDto::from)
                        .collect(Collectors.toList())
        );
    }

    public Profile toEntity() {
        return Profile.of(
                name,
                email,
                phoneNumber,
                point,
                gender,
                birthDate
        );
    }
}
