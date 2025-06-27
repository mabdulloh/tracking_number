package org.teleport.assessment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.teleport.assessment.domain.TrackingNumberQueryParams;
import org.teleport.assessment.entity.TrackingNumberEntity;
import org.teleport.assessment.repository.TrackingNumberRepository;
import org.teleport.assessment.service.TrackingNumberService;
import org.teleport.assessment.service.TrackingNumberServiceImpl;
import org.teleport.assessment.util.DateUtil;


import java.sql.Timestamp;
import java.time.Instant;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class TrackingNumberServiceTest {

    private TrackingNumberService trackingNumberService;

    @Mock
    private TrackingNumberRepository trackingNumberRepository;

    @BeforeEach
    void setUp(){
        trackingNumberService = new TrackingNumberServiceImpl(trackingNumberRepository);
    }

    @Test
    void saveValidParams_shouldGenerateTrackingNumber() {
        var trackingNumber = "1120MYIDAZGTW9A3";
        var originCountry = "MY";
        var destinationCountry = "US";
        var weight = 100;
        var requestedAt = "2025-06-27T09:46:59+00:00";
        var createdAt = DateUtil.fromDate(Timestamp.from(Instant.now()));
        var customerId = "de619854-b59b-425e-9db4-943979e1bd49";
        var customerSlug = "redbox_logistics";
        var customerName = "Redbox Logistics";
        var params = new TrackingNumberQueryParams(
                originCountry,
                destinationCountry,
                customerId,
                customerName,
                customerSlug,
                requestedAt,
                weight
        );
        var entity = new TrackingNumberEntity()
                .setTrackingNumber(trackingNumber)
                .setDestinationCountryId(destinationCountry)
                .setOriginCountryId(originCountry)
                .setCustomerId(customerId)
                .setRequestedAt(requestedAt)
                .setCreatedAt(createdAt)
                .setWeight(weight);
        when(trackingNumberRepository.save(any(TrackingNumberEntity.class))).thenReturn(entity);
        var result = trackingNumberService.generateTrackingNumber(params);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(trackingNumber, result.getTrackingNumber());
        verify(trackingNumberRepository, times(1)).save(any(TrackingNumberEntity.class));
    }
}
