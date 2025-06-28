package org.teleport.assessment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.teleport.assessment.domain.TrackingNumberQueryParams;
import org.teleport.assessment.domain.TrackingNumberResponse;
import org.teleport.assessment.entity.TrackingNumberEntity;
import org.teleport.assessment.repository.TrackingNumberRepository;
import org.teleport.assessment.util.DateUtil;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TrackingNumberServiceImpl implements TrackingNumberService {

    private final TrackingNumberRepository trackingNumberRepository;
    private static final int MAX_CHAR = 16;
    private static final char[] CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    private static final Random RANDOM = new SecureRandom();

    @Override
    public synchronized TrackingNumberResponse generateTrackingNumber(TrackingNumberQueryParams params) {
        var result = generateAndSave(params);
        return new TrackingNumberResponse()
                .setTrackingNumber(result.getTrackingNumber())
                .setCreatedAt(result.getCreatedAt());
    }

    private TrackingNumberEntity generateAndSave(TrackingNumberQueryParams params) {
        String trackingNumber;
        do {
            trackingNumber = generate(params);
        } while (trackingNumberRepository.existsByTrackingNumber(trackingNumber));
        var newTrackingNumber = new TrackingNumberEntity()
                .setTrackingNumber(trackingNumber)
                .setDestinationCountryId(params.destinationCountryId())
                .setOriginCountryId(params.originCountryId())
                .setWeight(params.weight())
                .setCustomerId(params.customerId())
                .setRequestedAt(params.createdAt())
                .setCreatedAt(DateUtil.fromDate(Timestamp.from(Instant.now())));
        return trackingNumberRepository.save(newTrackingNumber);
    }

    private String generate(TrackingNumberQueryParams params) {
        StringBuilder sbTrackingNumber = buildTrackingNumberFromParams(params);
        int currentLength = sbTrackingNumber.length();
        if (currentLength < MAX_CHAR) {
            sbTrackingNumber.append(buildRemainingTrackingNumber(MAX_CHAR - currentLength));
        }
        return sbTrackingNumber.toString();
    }

    private StringBuilder buildTrackingNumberFromParams(TrackingNumberQueryParams params) {
        StringBuilder sb = new StringBuilder();
        if (params != null) {
            if (params.createdAt() != null) {
                buildFromCreatedAt(params.createdAt(), sb);
            }
            if (params.originCountryId() != null) {
                sb.append(params.originCountryId());
            }
            if (params.destinationCountryId() != null) {
                sb.append(params.destinationCountryId());
            }
        }
        return sb;
    }

    private String buildFromUUID(String uuid) {
        String hex = uuid.replace("-", "");
        BigInteger bi = new BigInteger(hex, 16);
        return bi.toString(MAX_CHAR).toUpperCase().substring(0, 3);
    }

    private void buildFromCreatedAt(String createdAt, StringBuilder sb) {
        var month = createdAt.substring(5, 7);
        var day = createdAt.substring(8, 10);
        sb.append(month).append(day);
    }

    private String buildRemainingTrackingNumber(int remaining) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < remaining; i++) {
            int index = RANDOM.nextInt(CHARSET.length);
            sb.append(CHARSET[index]);
        }
        return sb.toString();
    }
}
