package com.lazy.rest.common.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Date utility class
 */
public class DateUtils {
    public static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_DATE = "yyyy-MM-dd";
    public static final String PATTERN_TIME = "HH:mm:ss";
    
    private static final FastDateFormat dateTimeFormat = FastDateFormat.getInstance(PATTERN_DATETIME);
    private static final FastDateFormat dateFormat = FastDateFormat.getInstance(PATTERN_DATE);
    private static final FastDateFormat timeFormat = FastDateFormat.getInstance(PATTERN_TIME);
    
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN_DATETIME);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(PATTERN_DATE);
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(PATTERN_TIME);

    /**
     * Format date to string
     */
    public static String formatDateTime(Date date) {
        return dateTimeFormat.format(date);
    }

    public static String formatDate(Date date) {
        return dateFormat.format(date);
    }

    public static String formatTime(Date date) {
        return timeFormat.format(date);
    }

    /**
     * Parse string to date
     */
    public static Date parseDateTime(String dateStr) throws ParseException {
        return dateTimeFormat.parse(dateStr);
    }

    public static Date parseDate(String dateStr) throws ParseException {
        return dateFormat.parse(dateStr);
    }

    public static Date parseTime(String timeStr) throws ParseException {
        return timeFormat.parse(timeStr);
    }

    /**
     * Convert between Date and LocalDateTime
     */
    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * Get start/end of day
     */
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return toDate(startOfDay);
    }

    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return toDate(endOfDay);
    }

    /**
     * Add/subtract time units
     */
    public static Date addDays(Date date, int days) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        return toDate(localDateTime.plusDays(days));
    }

    public static Date addHours(Date date, int hours) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        return toDate(localDateTime.plusHours(hours));
    }

    public static Date addMinutes(Date date, int minutes) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        return toDate(localDateTime.plusMinutes(minutes));
    }

    /**
     * Compare dates
     */
    public static boolean isSameDay(Date date1, Date date2) {
        LocalDate localDate1 = toLocalDateTime(date1).toLocalDate();
        LocalDate localDate2 = toLocalDateTime(date2).toLocalDate();
        return localDate1.equals(localDate2);
    }

    public static boolean isBeforeDay(Date date1, Date date2) {
        LocalDate localDate1 = toLocalDateTime(date1).toLocalDate();
        LocalDate localDate2 = toLocalDateTime(date2).toLocalDate();
        return localDate1.isBefore(localDate2);
    }

    public static boolean isAfterDay(Date date1, Date date2) {
        LocalDate localDate1 = toLocalDateTime(date1).toLocalDate();
        LocalDate localDate2 = toLocalDateTime(date2).toLocalDate();
        return localDate1.isAfter(localDate2);
    }
} 