package com.iot.sensordataapi.utils;


import com.iot.sensordataapi.exception.SensorDataApiException;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.iot.sensordataapi.constants.Constants.DATETIME_EXCEPTION_MESSAGE;
import static com.iot.sensordataapi.constants.Constants.DATETIME_FORMAT;

@UtilityClass
public class DateUtil {

    public static boolean isValidTimeFrame(String start, String end) {

        LocalDateTime startDate = getLocalDateTimeFromString(start);
        LocalDateTime endDate = getLocalDateTimeFromString(end);
        if (!startDate.isBefore(endDate)) {
            throw new SensorDataApiException(DATETIME_EXCEPTION_MESSAGE);
        }
        return true;
    }

    private static LocalDateTime getLocalDateTimeFromString(String timestamp) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
        return LocalDateTime.parse(timestamp, format);
    }
}
