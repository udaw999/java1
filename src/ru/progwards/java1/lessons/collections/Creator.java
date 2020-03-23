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
        int m = n*2+1;
        for (int i=0; i<n; i++){
            m -= 2;
            arrayList.add(m);
        }
        return arrayList;
    }

    public static Collection<Integer> fill3(int n){
        List<Integer> arrayList = new ArrayList();


        for (int i=0; i<n; i++){

            List<Integer> arrayList3 = new ArrayList();
                arrayList3.add(0,i*3);
                arrayList3.add(1,i*i*9);
                arrayList3.add(2,i*i*i*27);

         arrayList.addAll(arrayList3);

        }
        return arrayList;
    }

    public static void main(String[] args) {
        System.out.println("четные по возрастанию");
        System.out.println(fillEven(26));
        System.out.println("нечетных убывающих чисел");
        System.out.println(fillOdd(8));
        System.out.println("тройками чисел");
        System.out.println(fill3(5));
    }
}
