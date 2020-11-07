package ru.progwards.java1.lessons.datetime;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Profiler {
    public static long start;
    public static long stop;
    public static String nameParent = null;

    public static List<StatisticInfo> statisticInfos = new ArrayList<>();


    public static void enterSection(String name){
        //StatisticInfo.sectionName = name;
        start = System.currentTimeMillis();//время входа

        //System.out.println("start (" + name + ") - " + start);
        StatisticInfo sect = doesSectionExist(name,statisticInfos);//существует ли секция, если есть то получаем ее
        if(sect == null){
            statisticInfos.add(new StatisticInfo(name,start,nameParent));

       //     selfTimeMemoryDuble = selfTimeMemory;
      //      selfTimeMemory =0;
            nameParent = name;
           // System.out.println("tyt- name");
        } else {
            sect.start = start;
      //      selfTimeMemory =0;
            nameParent = name;
        }



    }

    public static void exitSection(String name){
        stop = System.currentTimeMillis();//время выхода

        StatisticInfo sect = doesSectionExist(name,statisticInfos);//существует ли секция, если есть то получаем ее
        if(sect != null){
            if (sect.fullTime == 0){
                sect.fullTime = (int)(stop - sect.start);
                sect.selfTime = sect.fullTime;
            } else {
                sect.fullTime += (int)(stop - sect.start);
                sect.selfTime = sect.fullTime;
                sect.count += 1;

            }
            nameParent = sect.sectionNameParent;
/*
            sect.selfTime = sect.fullTime - selfTimeMemory;
            selfTimeMemory = sect.selfTime;
*/

  //          System.out.println(selfTimeMemoryDuble);
        }

    }

    public static List<StatisticInfo> getStatisticInfo(){

        for (int i=0; i<statisticInfos.size(); i++) {
            for (int j=i; j<statisticInfos.size(); j++) {
                if (statisticInfos.get(i).sectionName.equals(statisticInfos.get(j).sectionNameParent)){
                    statisticInfos.get(i).selfTime = statisticInfos.get(i).selfTime - statisticInfos.get(j).selfTime;
                }
            }

        }

        for (StatisticInfo section : statisticInfos) {
            System.out.println(section);
        }
        System.out.println();

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
        enterSection("Process1");

        testCod();

        exitSection("Process1");
        enterSection("Process1");

        testCod();

        for (int j=0; j<3 ;j++){
            enterSection("Process2");

            testCod();

                enterSection("Process3");

                    testCod();

                 exitSection("Process3");

            exitSection("Process2");

        }

        exitSection("Process1");
        enterSection("Process1");

        testCod();

        exitSection("Process1");
        enterSection("Process1");

        testCod();

        exitSection("Process1");
        //========================
      /**/  enterSection("1");

            testCod();

        for(int i=0;i<5;i++) {
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

/**/
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
