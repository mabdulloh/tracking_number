package org.teleport.assessment.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record TrackingNumberResponse (
        String trackingNumber,
        String createdAt
) { }
