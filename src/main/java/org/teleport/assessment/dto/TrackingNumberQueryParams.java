package org.teleport.assessment.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.BindParam;


public record TrackingNumberQueryParams(
        @NotNull(message = "Origin country ID cannot be null")
        @BindParam("origin_country_id")
        String originCountryId,

        @NotNull(message = "Destination country ID cannot be null")
        @BindParam("destination_country_id")
        String destinationCountryId,

        @BindParam("customer_id")
        String customerId,

        @BindParam("customer_name")
        String customerName,

        @BindParam("customer_slug")
        String customerSlug,

        @BindParam("created_at")
        String createdAt,

        @BindParam("weight")
        double weight
) {
}
