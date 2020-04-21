package ru.progwards.java1.lessons.datetime;

public class StatisticInfo {

    public String sectionName; //- имя секции
    public int fullTime; //- полное время выполнения секции в миллисекундах.
    public int selfTime; //- чистое время выполнения секции в миллисекундах.
    public int count = 1; //- количество вызовов.
    public long start;

    public StatisticInfo(String sectionName,long start){
        this.sectionName = sectionName;
        this.start = start;
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
                '}';
    }


}
