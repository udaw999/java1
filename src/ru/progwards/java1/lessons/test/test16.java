package ru.progwards.java1.lessons.test;

import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class test16 {
/*Напишите метод с сигнатурой Date createDate(), который возвращает дату
- 28 февраля 1986, 0 часов, 0 минут, 0 секунд, 0 миллисекунд*/
    public static Date createDate(){
        TimeZone tz = TimeZone.getTimeZone("Europe/Moscow");
        Locale locale = new Locale("ru", "RU");
        Calendar calendar = Calendar.getInstance(tz,locale);
        //Calendar calendar = Calendar.getInstance(new Locale("ru", "RU"));
        calendar.clear();
        calendar.set(1986, 1, 28);
        //System.out.println(calendar.getTime());


        return calendar.getTime();
    }

    /*Создайте метод с сигнатурой Instant createInstant(), который возвращает Instant,
    соответствующий 1 января 2020 года, 15 часов и одна наносекунда по Московскому времени*/
    public static Instant createInstant(){

        LocalDateTime ldt3= LocalDateTime.of(2020, 1, 1, 15, 0, 0, 1);
        //System.out.println(ldt3);
        ZonedDateTime zdt= ldt3.atZone(ZoneId.of("Europe/Moscow"));

        Instant i1 = Instant.from(zdt);

        //System.out.println(zdt);
        return i1;
    }

    public static void main(String[] args) {
        System.out.println(createInstant());
        System.out.println(createDate());

        LocalDateTime ldt1= LocalDateTime.now();
        LocalDateTime ldt2= ldt1.plusDays(4);
        Duration duration = Duration.between(ldt1,ldt2);
        System.out.println(duration.toHours());//96

        LocalDateTime ldt3= LocalDateTime.of(2019, 05, 05, 22, 24);
        System.out.println(ldt3);//2019-05-05T22:24

        ZoneId zid1 = ZoneId.of("Europe/Moscow");
        System.out.println(zid1.getRules().getOffset(Instant.now()));//+03:00
    }
}
