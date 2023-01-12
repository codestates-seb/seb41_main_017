package com.codestates.culinari.order.dto.request;

import com.codestates.culinari.order.dto.OrderDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record OrderRequest(
        @NotEmpty(message = "최소 1개 이상의 상품을 주문해야합니다.")
        List<@Positive Long> productIds,
        @NotBlank(message = "주소 입력은 필수입니다.")
        String address,
        @NotBlank(message = "수령자 입력은 필수입니다.")
        String receiverName,
        @Pattern(regexp = "^[0-9]{3}-[0-9]{4}-[0-9]{4}$", message = "전화번호의 형식은 000-0000-0000 입니다.")
        String receiverPhoneNumber
) {

    public static OrderRequest of(List<Long> productIds, String address, String receiverName, String receiverPhoneNumber) {
        return new OrderRequest(productIds, address, receiverName, receiverPhoneNumber);
    }

    public OrderDto toDto() {
        return OrderDto.of(
                address,
                receiverName,
                receiverPhoneNumber
        );
    }
}
