package com.university.examination.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static com.university.examination.util.DataUtil.isNullOrEmpty;

public class DateTimeConvert {
    public static String dateTimeToString(LocalDateTime date, String pattern) {
        if (date == null || isNullOrEmpty(pattern)) {
            return null;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return df.format(date);
    }

    public static String dateToString(LocalDate date, String pattern) {
        if (date == null || isNullOrEmpty(pattern)) {
            return null;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return df.format(date);
    }

    public static String timeToString(LocalTime time, String pattern) {
        if (time == null || isNullOrEmpty(pattern)) {
            return null;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return df.format(time);
    }

}
