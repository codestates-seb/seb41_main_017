package com.codestates.culinari.destination.dto;

import com.codestates.culinari.destination.entity.Destination;
import com.codestates.culinari.user.entitiy.Profile;

public record DestinationDto(
        Long id,
        String destinationName,
        String address,
        String receiverName,
        String receiverPhoneNumber,
        Boolean defaultSelect,
        Profile profile
) {

    public static DestinationDto of(String destinationName, String address, String receiverName, String receiverPhoneNumber, Boolean defaultSelect) {
        return new DestinationDto(
                null,
                destinationName,
                address,
                receiverName,
                receiverPhoneNumber,
                defaultSelect,
                null
        );
    }

    public static DestinationDto from(Destination destination){
        return new DestinationDto(
                destination.getId(),
                destination.getDestinationName(),
                destination.getAddress(),
                destination.getReceiverName(),
                destination.getReceiverPhoneNumber(),
                destination.getDefaultSelect(),
                destination.getProfile()
        );
    }

    public Destination toEntity(Profile profile){
        return Destination.of(
                destinationName,
                address,
                receiverName,
                receiverPhoneNumber,
                defaultSelect,
                profile
        );
    }
}
