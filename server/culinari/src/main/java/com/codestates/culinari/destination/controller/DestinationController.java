package com.codestates.culinari.destination.controller;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.destination.dto.request.DestinationPatch;
import com.codestates.culinari.destination.dto.request.DestinationPost;
import com.codestates.culinari.destination.dto.response.DestinationResponse;
import com.codestates.culinari.destination.service.DestinationService;
import com.codestates.culinari.response.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/destination")
public class DestinationController {

    private final DestinationService destinationService;

    @PostMapping
    public ResponseEntity postNewDestination(@AuthenticationPrincipal CustomPrincipal customPrincipal,
                                             @RequestBody DestinationPost destinationRequest) {
        DestinationResponse destinationResponse = destinationService.createDestination(customPrincipal, destinationRequest);

        return new ResponseEntity<>(
                new SingleResponseDto<>(destinationResponse),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{destination-id}")
    public ResponseEntity deleteDestination(@AuthenticationPrincipal CustomPrincipal customPrincipal,
                                            @PathVariable("destination-id") Long destinationId) {
        destinationService.deleteDestination(customPrincipal, destinationId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{destination-id}")
    public ResponseEntity updateDestination(@AuthenticationPrincipal CustomPrincipal customPrincipal,
                                            @RequestBody DestinationPatch destinationPatch,
                                            @PathVariable("destination-id") Long destinationId) {
        destinationService.updateDestination(customPrincipal, destinationId, destinationPatch);

        return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
    }

    @PatchMapping("/{destination-id}/representative")
    public ResponseEntity updateDestinationDefaultState(@AuthenticationPrincipal CustomPrincipal customPrincipal,
                                                        @PathVariable("destination-id") Long destinationId) {
        destinationService.updateDestinationDefaultState(customPrincipal, destinationId);

        return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
    }

    @GetMapping
    public ResponseEntity getDestinationList(@AuthenticationPrincipal CustomPrincipal customPrincipal) {
        List<DestinationResponse> destinationResponseList = destinationService.readDestinationList(customPrincipal);

        return new ResponseEntity<>(
                new SingleResponseDto<>(destinationResponseList),
                HttpStatus.OK);
    }

    @GetMapping("/{destination-id}")
    public ResponseEntity getDestination(@AuthenticationPrincipal CustomPrincipal customPrincipal,
                                         @PathVariable("destination-id") Long destinationId) {
        DestinationResponse destinationResponse = destinationService.readDestination(customPrincipal, destinationId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(destinationResponse),
                HttpStatus.OK);
    }
}
