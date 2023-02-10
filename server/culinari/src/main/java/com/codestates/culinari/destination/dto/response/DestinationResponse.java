package com.codestates.culinari.destination.dto.response;

import com.codestates.culinari.destination.dto.DestinationDto;

public record DestinationResponse(
        Long id,
        String destinationName,
        String address,
        String receiverName,
        String receiverPhoneNumber,
        Boolean defaultSelect
) {

    public static DestinationResponse from(DestinationDto destinationDto){
        return new DestinationResponse(
                destinationDto.id(),
                destinationDto.destinationName(),
                destinationDto.address(),
                destinationDto.receiverName(),
                destinationDto.receiverPhoneNumber(),
                destinationDto.defaultSelect()
        );
    }
}
