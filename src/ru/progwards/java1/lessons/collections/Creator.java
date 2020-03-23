package ru.progwards.java1.lessons.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Creator {

    public static Collection<Integer> fillEven(int n){
        List<Integer> arrayList = new ArrayList();
        int m = 0;
        for (int i=0; i<n; i++){
            m += 2;
            arrayList.add(m);
        }
        return arrayList;
    }

    public static Collection<Integer> fillOdd(int n){
        List<Integer> arrayList = new ArrayList();
        int m = n*3*2+1;
        for (int i=0; i<n*3; i++){
            m -= 2;
            arrayList.add(m);
        }
        return arrayList;
    }

    public static Collection<Integer> fill3(int n){
        List<Integer> arrayList = new ArrayList();
        int m = 0;
        for (int i=0; i<n*3; i++){
            m = Integer.parseInt(i + "" + (i*i) + "" + (i*i*i));
            arrayList.add(m);
        }
        return arrayList;
    }

    public static void main(String[] args) {
        System.out.println("четные по возрастанию");
        System.out.println(fillEven(26));
        System.out.println("нечетных убывающих чисел");
        System.out.println(fillOdd(10));
        System.out.println("тройками чисел");
        System.out.println(fill3(2));
    }
}
