package ru.progwards.java1.lessons.test;

import java.util.*;

public class test12 {
    // test - 1
/*Напишите метод с сигнатурой public List<Integer> listAction(List<Integer> list), который
выполняет следующие действия:

удаляет минимальный элемент коллекции
по индексу 0 добавляет число равное количеству элементов
по индексу 2 добавляет максимальный элемент из list
возвращает list как результат метода*/
    public static List<Integer> listAction(List<Integer> list){
        //list.sort(null);
        list.remove(Collections.min(list));
        list.add(0,list.size());
        list.add(2,Collections.max(list));

     return list;
    }
//2 test
    /*Напишите метод, с сигнатурой public List<Integer> filter(List<Integer> list) который
    работает по следующему алгоритму
суммирует значения всех элементов списка
удаляет из списка элементы, значение которых меньше суммы, деленной на 100 (целочисленное деление)
возвращает результирующий список */
    public static List<Integer> filter(List<Integer> list){
        int sum = 0;
        for (int i=0; i<list.size(); i++){
            sum = list.get(i) + sum;

           // System.out.print(list.get(i));
        }
        //System.out.println("list.size()- "+list.size());
        for (int j=list.size()-1; j > -1; j--){
            if(list.get(j)>=(sum/100)){
                list.remove(j);
                //System.out.println(list.get(j));

            }

        }

//        System.out.println("end");
//        for (Integer i : arrayList) {
//            System.out.print(","+i);
//        }
//
//        System.out.println();
//        System.out.println(sum);
//        System.out.println(sum/100);

        return list;
    }
//тесе 3
    /*Напишите метод с сигнатурой public void iterator3(ListIterator<Integer> iterator) который заменяет
    значение каждого элемента, которое кратно 3 на значение его индекса.*/
    public static void iterator3(ListIterator<Integer> iterator){

        while (iterator.hasNext()) {
            Integer n = iterator.next();


            if ( n%3 == 0){
                iterator.set(iterator.nextIndex()-1);
                //System.out.println("Значение элемента = " + n);
            }

        }


    }
    public static void main(String[] args) {
    /*  List<Integer> arrayList = new ArrayList();
        arrayList.add(5);
        arrayList.add(2);
        arrayList.add(28);
        arrayList.add(34);
        arrayList.add(77);
        arrayList.add(315);
        arrayList.add(2);
        arrayList.add(158);
        arrayList.add(44);
        arrayList.add(7);
        System.out.println("of array");
      for (Integer i : arrayList) {
            System.out.print(i);
        }
        System.out.println();
        System.out.println("end array");
        for (Integer i : listAction(arrayList)) {
            System.out.print(","+i);
        }

        System.out.println();
        System.out.println(listAction(arrayList));
//2 test
        System.out.println("test 2");
        System.out.println(filter(arrayList));
*/
 //test 3
        List<Integer> linkedList = new LinkedList();
        for (int i = 0; i < 25; i++)
            linkedList.add(i);

        for (Integer i : linkedList) {
            System.out.print(","+i);
        }

        Iterator<Integer> iterator = linkedList.iterator();//создаем интератор

        iterator3((ListIterator<Integer>) iterator);

        for (Integer i : linkedList) {
            System.out.print(","+i);
        }

    }
}
