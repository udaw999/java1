package ru.progwards.java1.lessons.datetime;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Profiler {
    public static long start;
    public static long stop;
    public static int selfTimeMemory = 0;
    public static int selfTimeMemoryDuble = 0;

    public static List<StatisticInfo> statisticInfos = new ArrayList<>();


    public static void enterSection(String name){
        //StatisticInfo.sectionName = name;
        start = System.currentTimeMillis();//время входа

        //System.out.println("start (" + name + ") - " + start);
        StatisticInfo sect = doesSectionExist(name,statisticInfos);//существует ли секция, если есть то получаем ее
        if(sect == null){
            statisticInfos.add(new StatisticInfo(name,start));

            selfTimeMemoryDuble = selfTimeMemory;
            selfTimeMemory =0;

        } else {
            sect.start = start;
            selfTimeMemory =0;
        }



    }

    public static void exitSection(String name){
        stop = System.currentTimeMillis();//время выхода

        StatisticInfo sect = doesSectionExist(name,statisticInfos);//существует ли секция, если есть то получаем ее
        if(sect != null){
            if (sect.fullTime == 0){
                sect.fullTime = (int)(stop - sect.start);
            } else {
                sect.fullTime += (int)(stop - sect.start);
                sect.count += 1;

            }


            sect.selfTime = sect.fullTime - selfTimeMemory;
            selfTimeMemory = sect.selfTime;


            System.out.println(selfTimeMemoryDuble);
        }

    }

    public static List<StatisticInfo> getStatisticInfo(){

            //statisticInfos.add();
        return statisticInfos;
    }

    //существует ли секция
    public static StatisticInfo doesSectionExist(
            String name, List<StatisticInfo> statisticInfos) {

        for (StatisticInfo section : statisticInfos) {
            if (section.getSectionName().equals(name)) {
                return section;
            }
        }
        return null;
    }

    //это просто тест код для примера
    public static void testCod(){
      final int ELEMENTS_COUNT = 250_000;
        List<Integer> linkedList = new LinkedList();
        for (int i = 0; i < ELEMENTS_COUNT; i++) {
            linkedList.add( 0, i);
        }

    }

    public static void main(String[] args) {
        enterSection("1");

            testCod();

     /**/   for(int i=0;i<5;i++) {
            enterSection("2");


                enterSection("3");

                    testCod();

                exitSection("3");

                testCod();

            exitSection("2");
        }


        enterSection("4");

        testCod();

        exitSection("4");

    testCod();

        exitSection("1");


        System.out.println(getStatisticInfo());
        //System.out.println(".contains - " + statisticInfos.remove(1));
       // StatisticInfo st = statisticInfos.remove(1);
      //  System.out.println(st.sectionName);
   /*     String name = "5";
        Iterator<StatisticInfo> iterator = statisticInfos.iterator();
        while (iterator.hasNext()) {
            StatisticInfo customer = iterator.next();
            if (customer.getSectionName().equals(name)) {
                System.out.println("customer - " + customer.start);
                System.out.println(customer);
                customer.selfTime = (int)customer.start;
            }

        }*/

        //System.out.println(getStatisticInfo());
    }


}
