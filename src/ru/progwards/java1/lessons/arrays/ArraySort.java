package ru.progwards.java1.lessons.arrays;
//сортировка в массиве
public class ArraySort {
    int[] a;

    public static void sort(int[] a){

        for (int i=0; i<a.length ; i++){
            for (int j=i+1 ; j<a.length; j++){
                if(a[i] > a[j]){
                    int aj = a[j];
                    int ai = a[i];
                    a[i] = aj;
                    a[j] = ai;
                }
            }
            //for (int value : a) {           System.out.println("Элемент " + value);       }
        }

    }

    public static void main(String[] args) {
        int[] a2 = {25,30,27,2,8,45,36,20};
        ArraySort arraySort = new ArraySort();
        arraySort.sort(a2);

    }
}
