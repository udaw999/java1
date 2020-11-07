package ru.progwards.java1.lessons.interfaces2;

import java.util.Arrays;

//сортировка в массиве
public class ArraySort {

//сортировку Comparable<Number>
    //не мог реализовать соразу a[i].compareTo((Number) a[j]) потому что делал так a[i].compareTo(a[j])
    public static void sort(Comparable<Number>[] a){
        for (int i=0; i<a.length ; i++){
            for (int j=i+1 ; j<a.length; j++){

               if((a[i].compareTo((Number) a[j])) == 1){
                    Number aj = (Number) a[j];
                    Number ai = (Number) a[i];
                    a[i] = aj;
                    a[j] = ai;
                }
            }
            //for (int value : a) {           System.out.println("Элемент " + value);       }
        }
    }

    public static void main(String[] args) {
        int[] a2 = {25,30,27,2,8,45,36,20};
        //ArraySort arraySort = new ArraySort();
//        System.out.println(Arrays.toString(a2));
//        //sort(a2);
//
//        System.out.println(Arrays.toString(a2));

        DoubleNumber doubleNumber = new DoubleNumber(5.2);
        DoubleNumber doubleNumber2 = new DoubleNumber(3.2);
        DoubleNumber doubleNumber3 = new DoubleNumber(6.2);
        DoubleNumber doubleNumber4 = new DoubleNumber(8.2);
        DoubleNumber[] aray = {doubleNumber, doubleNumber2, doubleNumber3, doubleNumber4};
        for (DoubleNumber value : aray) {           System.out.print(", " + value);       }
        sort(aray);
        System.out.println();
        for (DoubleNumber value : aray) {           System.out.print(", " + value);       }

    }
}
