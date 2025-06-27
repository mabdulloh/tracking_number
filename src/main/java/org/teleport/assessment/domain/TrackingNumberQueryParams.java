package org.teleport.assessment.domain;

import org.springframework.web.bind.annotation.BindParam;

import java.time.OffsetDateTime;

public record TrackingNumberQueryParams(
        @BindParam("origin_country_id")
        String originCountryId,

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
