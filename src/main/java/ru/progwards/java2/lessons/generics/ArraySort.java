package ru.progwards.java2.lessons.generics;

public class ArraySort<T extends Comparable>{

    public static <T extends Comparable> T[] sort(T[] a){
        for (int i=0; i<a.length ; i++){
            for (int j=i+1 ; j<a.length; j++){
                if(a[i].compareTo(a[j]) > 0){

                    T aj = (T)a[j];
                    T ai = a[i];
                    a[i] = aj;
                    a[j] = ai;
                }
            }

        }
        //for (T value : a) {           System.out.println("Элемент " + value);       }
        return a;
    }


    public static void main(String[] args) {
        Integer[] array ={5,7,12,3,8,4,22,47,13};

            //System.out.println(sort(array));
        for (Integer value : sort(array)) {           System.out.println("Элемент " + value);       }


    }

}
