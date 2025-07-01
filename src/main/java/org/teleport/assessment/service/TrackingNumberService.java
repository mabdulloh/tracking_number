package org.teleport.assessment.service;

import org.teleport.assessment.dto.TrackingNumberQueryParams;
import org.teleport.assessment.dto.TrackingNumberResponse;

public interface TrackingNumberService {
    public TrackingNumberResponse generateTrackingNumber(TrackingNumberQueryParams params);
}
