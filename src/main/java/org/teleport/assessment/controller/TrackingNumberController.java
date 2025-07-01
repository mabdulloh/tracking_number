package org.teleport.assessment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.teleport.assessment.dto.TrackingNumberQueryParams;
import org.teleport.assessment.dto.TrackingNumberResponse;
import org.teleport.assessment.service.TrackingNumberService;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class TrackingNumberController {
    private final TrackingNumberService trackingNumberService;

    @GetMapping(value = "/next-tracking-number")
    public ResponseEntity<TrackingNumberResponse> generateTrackingNumber(@Valid TrackingNumberQueryParams params) {
        var result = trackingNumberService.generateTrackingNumber(params);
        return ResponseEntity.ok(result);
    }
}
