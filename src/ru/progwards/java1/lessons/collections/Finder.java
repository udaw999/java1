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

    public static Collection<Integer> findLocalMax(Collection<Integer> numbers){
        List<Integer> indexList = new ArrayList();//коллекция max num чисел

        List<Integer> linkedList = (List<Integer>) numbers; //существующая коллекция
        for (int i=1; i<linkedList.size()-1; i++){
            if(linkedList.get(i) > linkedList.get(i-1) & linkedList.get(i) > linkedList.get(i+1)){

                indexList.add(linkedList.get(i));//добавляем в колекцию максимальные числа
            }
        }

        return indexList;
    }
    public static boolean findSequence(Collection<Integer> numbers){
        List<Integer> linkedList = (List<Integer>) numbers; //существующая коллекция
        boolean find = false;
        for (int i=0; i<linkedList.size(); i++){
            for (int j=0;j<linkedList.size();j++){
                if (i == linkedList.get(j)){
                    find = true;
                    break;
                } else {
                    find = false;
                }
            }
                if (find==false){
                    break;
                }
        }
        return find;
    }
    public static String findSimilar(Collection<String> names){

        return "null";
    }
    public static void main(String[] args) {
        List<Integer> arrayList2 = new LinkedList();
        arrayList2.add(0);
        arrayList2.add(1);
        arrayList2.add(2);
        arrayList2.add(3);
        arrayList2.add(4);
        arrayList2.add(5);
        arrayList2.add(6);

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
      //  System.out.println(findMinSumPair(arrayList));
        //System.out.println("min- "+Collections.min(arrayList));
        System.out.println(findLocalMax(arrayList));

        System.out.println(findSequence(arrayList2));
    }
}
