package ru.progwards.java2.lessons.generics;

import java.util.Arrays;

public class BlockArray<T> {

    static final int MIN_SIZE = 2;
    T[] array;
    int size;

    public BlockArray() {
        array = (T[])new Object[MIN_SIZE];
    }

    public void add(T item) {

        if (size >= array.length) {

            int newSize;
                newSize = array.length * 2;

            T[] newArray = (T[])new Object[newSize];
            if (array != null)
                newArray = Arrays.copyOf(this.array,newArray.length);
            array = newArray;
            System.out.println("tet" + size);
        }
        array[size++] = item;
    }

    public void insert(int pos, T item){
        T[] newArray = null;

        if (pos > array.length){
            newArray = (T[])new Object[pos+1];
            newArray = Arrays.copyOf(array,newArray.length);
            newArray[pos] = item;

        } else {
            if (size >= array.length){
                int newSize;
                newSize = array.length * 2;

                newArray = (T[])new Object[newSize];
                System.arraycopy(array,0,newArray,0,pos);
                newArray[pos] = item;
                System.arraycopy(array,pos,newArray,pos+1,array.length-pos);
            } else {
                newArray = (T[])new Object[array.length];
                System.arraycopy(array,0,newArray,0,pos);
                newArray[pos] = item;
                System.arraycopy(array,pos,newArray,pos+1,array.length-pos-1);
            }
        }
        array = newArray;
    }
    public void remove(int pos){
        T[] newArray = (T[])new Object[array.length-1];
        System.arraycopy(array,0,newArray,0,pos);
        System.arraycopy(array,pos+1,newArray,pos,array.length-1-pos);
        array = newArray;
    }

    public <T> T get(int pos){
        return (T) array[pos];
    }
    public int size(){
        return size;
    }
    public static void main(String[] args) {
        BlockArray dIntArray = new BlockArray();

        dIntArray.add(0);
        dIntArray.add(1);
        dIntArray.add(2);
        dIntArray.add(3);
        dIntArray.add(4);
        dIntArray.add(5);
        dIntArray.add(6);
        dIntArray.add(7);
        dIntArray.add(8);
        dIntArray.add(9);
        dIntArray.add(10);
        dIntArray.add(11);
        dIntArray.add(12);
        dIntArray.add(13);/*
        dIntArray.add(-91);
        dIntArray.add(84);*/

//        for (Object value : dIntArray.array) {           System.out.println("Элемент " + value);       }
//        System.out.println();
        for (int i = 0; i<dIntArray.array.length ; i++){
            System.out.println(dIntArray.array[i]);
        }

        System.out.println();
        //dIntArray.insert(6,5555);
        //dIntArray.remove(8);
        System.out.println();
//        for (int i = 0; i<dIntArray.array.length ; i++){
//            System.out.println(dIntArray.array[i]);
//        }
        System.out.println(dIntArray.get(9));
        System.out.println(dIntArray.size());
    }
}
