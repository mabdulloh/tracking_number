package org.teleport.assessment.service;

import org.teleport.assessment.domain.TrackingNumberQueryParams;
import org.teleport.assessment.domain.TrackingNumberResponse;

public interface TrackingNumberService {
    public TrackingNumberResponse generateTrackingNumber(TrackingNumberQueryParams params);
}
