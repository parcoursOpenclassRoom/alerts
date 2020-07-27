package com.safetynet.alerts.Manager.util;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class DateUtil {

    // convert Date to LocalDateTime
    public static LocalDate convertToLocalDateTime(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
