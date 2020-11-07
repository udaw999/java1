package ru.progwards.java1.lessons.sets;

import java.util.HashSet;
import java.util.Set;

public class SetOperations {
//1.1 Метод  - объединение множеств
    public static Set<Integer> union(Set<Integer> set1, Set<Integer> set2){
        HashSet result = new HashSet(set1);
        result.addAll(set2);
        return result;
    }
//1.2 Метод  - пересечение множеств
    public static Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2){
        HashSet result = new HashSet(set1);
        result.retainAll(set2);
        return result;
    }
//1.3 Метод  - разница множеств
    public static Set<Integer> difference(Set<Integer> set1, Set<Integer> set2){
        HashSet result = new HashSet(set1);
        result.removeAll(intersection(set1,set2));//удалить из 1 колекции пересеченные множества
        return result;
    }
//1.4 Метод  - симметрическая разница
    public static Set<Integer> symDifference(Set<Integer> set1, Set<Integer> set2){
        HashSet result = new HashSet(set1);
        result.addAll(set2);//объединение множеств
        result.removeAll(intersection(set1,set2));//удалить из объединенных множеств пересеченные множества
        return result;
    }
/*Кто подзабыл что значит каждая операция, можно глянуть тут
https://studopedia.ru/14_138615_operatsii-nad-mnozhestvami-i-ih-svoystva.html или OK - google
* */
    public static void main(String[] args) {
        Set<Integer> intSet1 = Set.of(1, 2, 3, 4, 5);
        Set<Integer> intSet2 = Set.of(3, 4, 5, 6, 7, 8, 9);
        System.out.println("1- " + intSet1);
        System.out.println("2- " + intSet2);

        Set<Integer> set = union(intSet1,intSet2);
        System.out.println(set);

        Set<Integer> set1 = intersection(intSet1,intSet2);
        System.out.println(set1);

        Set<Integer> set2 = difference(intSet1,intSet2);
        System.out.println(set2);

        Set<Integer> set3 = symDifference(intSet1,intSet2);
        System.out.println(set3);
    }
}
