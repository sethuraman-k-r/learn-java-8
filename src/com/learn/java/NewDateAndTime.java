package com.learn.java;

import javax.print.attribute.standard.Chromaticity;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.TimeZone;

public class NewDateAndTime {

    public static void main(String[] args) {
        /**
         * LocalDate
         * LocalTime
         * Instant
         * Duration
         * Period
         */
        LocalDate date = LocalDate.of(2014, 3, 18);
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek dow = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leap = date.isLeapYear();
        System.out.println(date + " " + year + " " + month + " " + day + " " + dow + " " + len + " " + leap);

        LocalDate today = LocalDate.now();
        System.out.println(today);

        // LocalDate using TemportalField
        System.out.println(date.get(ChronoField.YEAR));
        System.out.println(date.get(ChronoField.MONTH_OF_YEAR));
        System.out.println(date.get(ChronoField.DAY_OF_MONTH));

        LocalTime time = LocalTime.of(13, 45, 20);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        System.out.println(hour + " " + minute + " " + second);

//        LocalTime date = LocalTime.parse("2014-03-18");
//        LocalTime time = LocalTime.parse("13:45:20");

        // Combining a date and a time
        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(13, 45, 20);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);
        System.out.println(dt1 + "\n" + dt2 + "\n" + dt3 + "\n" + dt4 + "\n" + dt5);

        LocalDate date1 = dt1.toLocalDate();
        LocalTime time1 = dt1.toLocalTime();

        System.out.println(Instant.ofEpochSecond(3));
        System.out.println(Instant.ofEpochSecond(3, 0));
        System.out.println(Instant.ofEpochSecond(2, 1_000_000_000));
        System.out.println(Instant.ofEpochSecond(4, -1_000_000_000));
//        System.out.println(Instant.now().get(ChronoField.DAY_OF_MONTH));   // throws java.time.temporal.UnsupportedTemporalTypeException

        Duration d1 = Duration.between(time, time1);
//        Duration.between(dateTime1, dateTime2);
//        Duration.between(instant1, instant2);
        System.out.println(d1);

        Period tenDays = Period.between(LocalDate.of(2014, 3, 8),
                LocalDate.of(2014, 3, 18));
        System.out.println(tenDays);

        Duration threeMinutes = Duration.ofMinutes(3);
//        threeMinutes = Duration.of(3, ChronoUnit.MINUTES);
        System.out.println(threeMinutes);

//        Period tenDays = Period.ofDays(10);
        Period threeWeaks = Period.ofWeeks(3);
        System.out.println(threeWeaks);
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
        System.out.println(twoYearsSixMonthsOneDay);

        // Manipulating dates
        LocalDate date2 = date1.withYear(2011);
        LocalDate date3 = date2.withDayOfMonth(25);
        LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 9);
        date1.plusWeeks(1);
        date1.minusYears(2);
        date1.plus(3, ChronoUnit.MONTHS);

        // Formatting and Parsing dates
        String s1 = date1.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(s1);

        LocalDate date5 = LocalDate.parse("20140318", DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(date5);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println(date1.format(formatter));

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
        System.out.println(date1.format(formatter1));

        // Working with different time zones and calendars
        ZoneId romeZone = ZoneId.of("Europe/Rome");
        System.out.println(romeZone);
        ZoneId zoneId = TimeZone.getDefault().toZoneId();
        System.out.println(zoneId);

        ZonedDateTime zdt1 = date.atStartOfDay(romeZone);
        System.out.println(zdt1);
        ZonedDateTime zdt2 = dt1.atZone(romeZone);
        System.out.println(zdt2);
        ZonedDateTime zdt3 = Instant.now().atZone(romeZone);
        System.out.println(zdt3);

        ZoneOffset newYorkOffset = ZoneOffset.of("-05:00");
        OffsetDateTime dateTimeInNewYork = OffsetDateTime.of(dt1, newYorkOffset);
        System.out.println(dateTimeInNewYork);

    }

}
