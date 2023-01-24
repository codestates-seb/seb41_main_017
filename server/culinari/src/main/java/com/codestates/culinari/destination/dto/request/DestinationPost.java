package com.codestates.culinari.destination.dto.request;

import com.codestates.culinari.destination.dto.DestinationDto;

public record DestinationPost(
        String destinationName,
        String address,
        String receiverName,
        String receiverPhoneNumber,
        Boolean defaultSelect
) {

    public DestinationDto toDto() {
        return DestinationDto.of(
                destinationName,
                address,
                receiverName,
                receiverPhoneNumber,
                defaultSelect
        );
    }
}
