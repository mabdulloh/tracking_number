package org.teleport.assessment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Document(collection = "tracking_number")
public class TrackingNumberEntity {
    @Id
    private String id;

    @Indexed(name = "tracking_number_idx", unique = true)
    private String trackingNumber;

    private String originCountryId;

    private String destinationCountryId;

    private String customerId;

    private double weight;

    private String createdAt;

    private String requestedAt;
}
