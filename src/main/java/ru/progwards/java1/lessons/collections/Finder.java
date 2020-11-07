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
        for (int i=1; i<linkedList.size(); i++){
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
        List<String> nameListStr = new LinkedList();
        nameListStr.add("name");
        nameListStr.add("1");

        List<String> strList = (List<String>) names; //существующая коллекция
        for (int i=0; i<strList.size(); i++){
            String name = strList.get(i); //слово
            int coutName = 0; //считает количество рядом стоящих слов
            for (int j=i; j<strList.size(); j++){
                if(strList.get(i).equals(strList.get(j)) == true){
                    coutName += 1;//если следующее слово одинаково то увеличиваем счет на 1
                } else {
                    break;
                }
            }

            if(coutName > Integer.parseInt(nameListStr.get(1))){//если счет слов больше чем цифра в колекции
                //то мы перезаписываем счет и слово
                nameListStr.set(0,name);//слово
                nameListStr.set(1,String.valueOf(coutName));//счет првращаем в строку
            }

        }
        return nameListStr.get(0) + ":" + nameListStr.get(1);
    }
    public static void main(String[] args) {
        List<String> arrayListStr = new LinkedList();

        arrayListStr.add("КОЛЯ");
        arrayListStr.add("КОЛЯ");
        arrayListStr.add("КОЛЯ");
        arrayListStr.add("КОЛЯ");
        arrayListStr.add("КОЛЯ");
        arrayListStr.add("ВАСЯ");
        arrayListStr.add("КОЛЯ");
        arrayListStr.add("ДЕН");
        arrayListStr.add("ВАСЯ");
        arrayListStr.add("ДЕН");
        arrayListStr.add("КОЛЯ");
        arrayListStr.add("ДЕН");
        arrayListStr.add("ДЕН");
        arrayListStr.add("ДЕН");
        arrayListStr.add("ДЕН");
        arrayListStr.add("ДЕН");
        arrayListStr.add("ВАСЯ");


        arrayListStr.add("ВАСЯ");
        arrayListStr.add("КОЛЯ");
        arrayListStr.add("ДЕН");

        List<Integer> arrayList2 = new LinkedList();

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
        System.out.println(arrayListStr);

        System.out.println(findSimilar(arrayListStr));
    }
}
