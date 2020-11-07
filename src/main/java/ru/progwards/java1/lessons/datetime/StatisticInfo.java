package ru.progwards.java1.lessons.datetime;

public class StatisticInfo {

    public String sectionName; //- имя секции
    public int fullTime; //- полное время выполнения секции в миллисекундах.
    public int selfTime; //- чистое время выполнения секции в миллисекундах.
    public int count = 1; //- количество вызовов.
    public long start;
    public String sectionNameParent;

    public StatisticInfo(String sectionName,long start,String sectionNameParent){
        this.sectionName = sectionName;
        this.start = start;
        this.sectionNameParent = sectionNameParent;
    }

    public String getSectionName() {
        return sectionName;
    }



    @Override
    public String toString() {
        return "StatisticInfo{" +
                "sectionName='" + sectionName + '\'' +
                ", fullTime=" + fullTime +
                ", selfTime=" + selfTime +
                ", count=" + count +
                ", start=" + start +
                ", sectionNameParent=" + sectionNameParent +
                '}';
    }


}
