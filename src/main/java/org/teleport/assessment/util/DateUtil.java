package org.teleport.assessment.util;

import lombok.experimental.UtilityClass;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;

@UtilityClass
public class DateUtil {

    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");

    public String fromDate(Timestamp date){
        return DATE_FORMAT.format(date);
    }

    public Timestamp toDate(String date){
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(date);
        return Timestamp.from(offsetDateTime.toInstant());
    }
}
