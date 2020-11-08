package ru.progwards.java1.lessons.test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class test15 {
    /*Реализуйте метод с сигнатурой HashMap<Integer, String> a2map(int[] akey, String[] aval),
    который создает Map на основе пары массивов akey (ключи) и aval (значения). Первому элементу массива akey
    соответствует первый элемент массива aval, и т.д. Размеры массивов одинаковые.*/
    public  static  HashMap<Integer, String> a2map(int[] akey, String[] aval){
        HashMap<Integer, String> map = new HashMap<>();

        for (int i = 0; i < akey.length; i++ ){
            map.put(akey[i],aval[i]);
        }

        return map;
    }

    /*Создайте метод с сигнатурой int fillHoles(Map<Integer, String> map, int size), который вставляет в
     HashMap пару <n> "Число n", если она там отсутствует,  Проверить от 1 до максимального числа size
     включительно. Метод возвращает количество добавленных элементов. Пример пары: 1 "Число 1"*/

    public  static  int fillHoles(Map<Integer, String> map, int size){

        int counter = 0;
            for (int i = 1; i<= size;i++){

                //System.out.println(map.putIfAbsent(i,"Число " + i));
                if(map.putIfAbsent(i,"Число " + i) == null){
                    map.putIfAbsent(i,"Число " + i);
                    counter++;
                }
            }
        return counter;
    }
/*Реализуйте метод с сигнатурой void checkAndAdd(TreeMap<Integer, String> map, Integer key, String value),
 который добавляет пару key-value в map при выполнении следующих условий:

значение с таким key должно отсутствовать
значение key долно быть больше головы TreeMap
значение key долно быть меньше хвоста TreeMap*/
static void checkAndAdd(TreeMap<Integer, String> map, Integer key, String value) {
    if (key != 0){
        if ((map.ceilingKey(key) != key) && (map.firstEntry().getKey() < key)) {
            if (map.lastEntry().getKey() > key)
                map.put(key, value);
        }
    }




}
    public static void main(String[] args) {
//        int[] key = {1,2,3,4,5,6};
//        String[] val = {"vitas","serg","oleg","masha","olya","bera"};
//
//        System.out.println(a2map(key,val));

//        Map<Integer, String> map1 = new HashMap<>();
//        System.out.print(fillHoles(map1, 4)+":");
//        System.out.print(map1);

//        int akey1[] = {8, 3, 4};
//        String aval1[] = {"Число 8", "Число 3", "Число 4"};
//        Map<Integer, String> map2 = a2map(akey1, aval1);
//        System.out.print(fillHoles(map2, 5)+":");
//        System.out.println(map2);


        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(1, "Один");
        map.put(9, "Девять");
        checkAndAdd(map, 0, "Zero");
        checkAndAdd(map, 3, "Три");
        checkAndAdd(map, 2, "Два");
        checkAndAdd(map, 3, "Three");
        checkAndAdd(map, 10, "Ten");
        System.out.println(map);

        TreeMap<Integer, String> map2 = new TreeMap<>();
        checkAndAdd(map2, 0, "Zero");
        checkAndAdd(map2, 0, "Zero");
        System.out.println(map2);

    }
}
