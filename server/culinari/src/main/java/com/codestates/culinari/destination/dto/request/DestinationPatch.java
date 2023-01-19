package com.codestates.culinari.destination.dto.request;

public record DestinationPatch(
        String destinationName,
        String address,
        String receiverName,
        String receiverPhoneNumber
) {
}
