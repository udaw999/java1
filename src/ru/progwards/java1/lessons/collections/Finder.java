package ru.progwards.java1.lessons.collections;

import java.util.*;

public class Finder {

    public static Collection<Integer> findMinSumPair(Collection<Integer> numbers){
        List<Integer> sumList = new ArrayList();//коллекция сложеных соседних чисел

        List<Integer> linkedList = (List<Integer>) numbers; //существующая коллекция

        for(int i=0; i<numbers.size()-1;i++){//наполняем колекцию sumList сложенными соседними числами
            sumList.add(((List<Integer>) numbers).get(i) + ((List<Integer>) numbers).get(i+1));
        }
        //определяем минимальное значение в колекции сум
        Integer min = Collections.min(sumList);
       // System.out.println("min - " + min);
        List<Integer> indexMinList = new ArrayList();//коллекция индексов чисел сумма которых минимальная
        for(int j=0; j<numbers.size()-1;j++){//наполняем колекцию sumList сложенными соседними числами
            if (((List<Integer>) numbers).get(j) + ((List<Integer>) numbers).get(j+1) == min){

                indexMinList.add(j);
                indexMinList.add(j+1);//наполняем колекцию индексов чисел сумма которых минимальная
            }

        }

        return indexMinList;
    }

    public static void main(String[] args) {

        List<Integer> arrayList = new LinkedList();
        arrayList.add(5);
        arrayList.add(2);
        arrayList.add(28);
        arrayList.add(34);
        arrayList.add(77);
        arrayList.add(315);
        arrayList.add(2);
        arrayList.add(1);
        arrayList.add(44);
        arrayList.add(7);
        arrayList.add(5);
        arrayList.add(2);
        arrayList.add(28);
        arrayList.add(34);
        arrayList.add(77);
        arrayList.add(315);
        arrayList.add(2);
        arrayList.add(1);
        arrayList.add(44);
        arrayList.add(7);
        System.out.println(arrayList);
        System.out.println("resul");
        System.out.println(findMinSumPair(arrayList));
        //System.out.println("min- "+Collections.min(arrayList));

  /*      List<Integer> linkedList = new LinkedList();
        for (int i = 0; i < 20; i++)
            linkedList.add(i + 1);
        System.out.println("Список до изменения:");
        for (Integer intObj : linkedList)
            System.out.println("Значение элемента = " + intObj);
        for (ListIterator<Integer> listIterator = linkedList.listIterator(); listIterator.hasNext(); ) {
            Integer intObj = listIterator.next();
            Integer pre = listIterator.previous();
            listIterator.set(intObj * intObj);
        }
        System.out.println("\nСписок после изменения:");
        for (Integer intObj : linkedList){
            System.out.println("Значение элемента = " + intObj);

        }
        */

    }
}
