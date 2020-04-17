package ru.progwards.java1.lessons.datetime;

import java.sql.Time;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;
import java.util.concurrent.TimeUnit;

public class Insurance {
    public static enum FormatStyle {SHORT, LONG, FULL}
    private ZonedDateTime start; //- дата-время начала действия страховки.
    private Duration duration; //- продолжительность действия.
    ZoneId zoneId = ZoneId.systemDefault();//зонна по умолчанию
    private String validStr = " is valid";
    public Insurance(ZonedDateTime start){
        this.start = start;
    }

    public Insurance(String strStart, FormatStyle style){

        DateTimeFormatter dtf;
        switch (style){
            case FULL:
                this.start = ZonedDateTime.parse(strStart, DateTimeFormatter.ISO_ZONED_DATE_TIME);
            case LONG:
                 dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                this.start = ZonedDateTime.of( LocalDateTime.parse(strStart, dtf), zoneId);
            case SHORT:
                //LocalDate.parse(strStart,dtf) парсим дату из строки по формату.
                //.atStartOfDay() добавляем время нули
                //zoneId и добавляем зону по умолчанию
                 dtf = DateTimeFormatter.ISO_LOCAL_DATE;
                this.start = ZonedDateTime.of( LocalDate.parse(strStart,dtf).atStartOfDay(), zoneId);
        }
    }

    public void setDuration(Duration duration){
        this.duration = duration;
    }
    //продолжительность получаем из стартовой даты и поступающей в методе
    public void setDuration(ZonedDateTime expiration){
        duration = Duration.between(start,expiration);
    }

    public void setDuration(int months, int days, int hours){
        ZonedDateTime end = start.plusMonths(months).plusDays(days).plusHours(hours);
        duration  = Duration.between(start,end);
    }

    public void setDuration(String strDuration, FormatStyle style){
        switch (style){
            case SHORT:
                duration = Duration.ofMillis(Long.parseLong(strDuration));
            case FULL:
                duration = Duration.parse(strDuration);
            case LONG:
                DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                LocalDateTime localDateTimeTime = LocalDateTime.parse(strDuration, dtf);
                LocalDate localDate = localDateTimeTime.toLocalDate();
                LocalTime localTime = localDateTimeTime.toLocalTime();
                ZonedDateTime end = start.plusYears(localDate.getYear());
                end = end.plusMonths(localDate.getMonthValue());
                end = end.plusDays(localDate.getDayOfMonth());
                end = end.plusHours(localTime.getHour());
                end = end.plusMinutes(localTime.getMinute());
                end = end.plusSeconds(localTime.getSecond());

                duration  = Duration.between(start,end);
        }
    }
    public boolean checkValid(ZonedDateTime dateTime){

        if (duration != null){

            int n = dateTime.compareTo(start.plus(duration));
            if (n > 0){
                validStr = " is valid";
            } else {
                validStr = " is not valid";
            }

            return n > 0;
        } else {
            validStr = " is valid";
            return true;

        }

    }


    @Override
    public String toString() {
        return "Insurance issued on " + start + validStr;
    }

    public static void main(String[] args) {
        ZonedDateTime start = ZonedDateTime.of(2011, 03, 05, 8, 0, 0, 0, ZoneId.of("Europe/Moscow"));
        String full = "2013-01-01T00:00:00-05:00";
        String longg = "2011-12-03T10:15:30";
        String shortt = "2011-02-03";

        String longgD = "0000-01-01T10:00:00";
        ZonedDateTime proverca = ZonedDateTime.of(2011, 04, 04, 0, 0, 0, 0, ZoneId.of("Europe/Moscow"));

        Insurance insurance0 = new Insurance(start);
//        System.out.println("1 конструктор" + insurance0.toString());

        Insurance insurance = new Insurance(shortt,FormatStyle.SHORT);
        //insurance.setDuration(start);
        //insurance.setDuration(2,0,20);
        //insurance.setDuration(longgD,FormatStyle.LONG);
        System.out.println(insurance.checkValid(proverca));

        System.out.println("2k longg - " + insurance.toString());

    }
}
