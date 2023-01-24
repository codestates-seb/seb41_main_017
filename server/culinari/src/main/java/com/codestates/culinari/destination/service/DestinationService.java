package com.codestates.culinari.destination.service;


import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.destination.dto.request.DestinationPatch;
import com.codestates.culinari.destination.dto.request.DestinationPost;
import com.codestates.culinari.destination.dto.response.DestinationResponse;

import java.util.List;

public interface DestinationService {
    DestinationResponse createDestination(CustomPrincipal customPrincipal, DestinationPost destinationPost);

    void deleteDestination(CustomPrincipal customPrincipal, Long destinationId);

    void updateDestination(CustomPrincipal customPrincipal, Long destinationId,  DestinationPatch destinationPatch);

    List<DestinationResponse> readDestinationList(CustomPrincipal customPrincipal);

    DestinationResponse readDestination(CustomPrincipal customPrincipal, Long destinationId);

    DestinationResponse getDestinationDefaultState(CustomPrincipal customPrincipal);

    void updateDestinationDefaultState(CustomPrincipal customPrincipal, Long destinationId);
}
