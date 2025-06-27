package org.teleport.assessment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.teleport.assessment.entity.TrackingNumberEntity;

public interface TrackingNumberRepository extends MongoRepository<TrackingNumberEntity, String> {
    boolean existsByTrackingNumber(String trackingNumber);
}
