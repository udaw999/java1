package ru.progwards.java1.lessons.test.test_13;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

public class test18 {

    public static String swapWords(String sentance){
        String toc = "";
        StringTokenizer tokenizer = new StringTokenizer(sentance, " .,-!\n");
        int i=1;
        String s1 = "";
        String s2 = "";

        while (tokenizer.hasMoreTokens()){
            //System.out.print(tokenizer.nextToken());
            if (i % 2 == 0){
               s1 = " " + tokenizer.nextToken();
                toc = toc + s1 + s2;
                s2 = "";
            } else {
                s2 = " " + tokenizer.nextToken();

                s1 = "";
            }
           // System.out.println(" nextElement - " +tokenizer.countTokens());
            if (tokenizer.countTokens() == 0 && i % 2 != 0){
                toc = toc + s2;
            }
            i++;

        }

        return toc.trim();

    }
    static class Person {
        public String name;

        public Date birth;
        public double salary;

        Person(String name, Date birth, double salary) {
            this.name = name;
            this.birth = birth;
            this. salary = salary;
        }


    }
    public static void printPersons(Person[] persons){
        for (int i=0; i<persons.length; i++){
            Locale locale = new Locale("ru");
            System.out.format(locale,"|%-10s|%2$td/%2$tm/%2$tY|%3$,10.2f|\n", persons[i].name, persons[i].birth, persons[i].salary);

        }
    }
    public static void main(String[] args) throws ParseException {
        System.out.println(swapWords("Тех слов, где есть хоть капля яда\n" +
                "И в шутку говорить не надо.\n" +
                "(c) Абу Шукур Балхи"));

        System.out.format("|%04d|%#x|%2.1f|", 2, 15, 3.25);

        Date date = new SimpleDateFormat( "dd.MM.yyyy" ).parse( "28.12.2016" );
        Date date2 = new SimpleDateFormat( "dd.MM.yyyy" ).parse( "20.10.2006" );
        Date date3 = new SimpleDateFormat( "dd.MM.yyyy" ).parse( "08.02.2010" );
        Person person = new Person("Vasya",date, 255555.55);
        Person person1 = new Person("Vera",date2, 200000.355);
        Person person2 = new Person("Sasha",date3, 23355.55);
        Person[] persons = {person,person1,person2};
        System.out.println();
        printPersons(persons);
    }
}
