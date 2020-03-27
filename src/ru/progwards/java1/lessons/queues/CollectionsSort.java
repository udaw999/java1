package ru.progwards.java1.lessons.queues;

import java.util.*;

import static java.util.Collections.*;

public class CollectionsSort {

    public static void mySort(Collection<Integer> data) {
        List<Integer> rezult = new LinkedList<>();

        while (!data.isEmpty()){
            rezult.add(min(data));
            data.remove(min(data));
        }
        data.addAll(rezult);

    }

    public static void collSort(Collection<Integer> data){

        List<Integer> rezult = new LinkedList<>(data);
        Collections.sort(rezult);
        data.clear();
        data.addAll(rezult);
    }

    public static Collection<String> compareSort(){
        List<String> rezult = new LinkedList<>();
        final int ELEM_COUNT = 1_000;
        List<Integer> collection = new ArrayList<>();
        for (int i = 0; i < ELEM_COUNT; i++) collection.add(ELEM_COUNT - i);
        List<Integer> collection2 = new ArrayList<>();
        for (int i = 0; i < ELEM_COUNT; i++) collection2.add(ELEM_COUNT - i);

        long start = System.currentTimeMillis();
        mySort(collection);
        long rezult1 = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        collSort(collection2);
        long rezult2 = System.currentTimeMillis() - start;

        if( rezult1 >= rezult2){
            rezult.add(0,"collSort");
            rezult.add(1,"mySort");

        } else {
            rezult.add(0,"mySort");
            rezult.add(1,"collSort");
        }


        return rezult;
    }

    public static void main(String[] args) {
        List<Integer> collection = new ArrayList<>(List.of(3, 5, 9, 12, 1, 2, 158, 33));


        //mySort(collection);
        collSort(collection);

        System.out.println(compareSort());
    }
}
