package ru.progwards.java1.lessons.arrays;

import java.sql.Array;
import java.util.Arrays;

public class DIntArray {
    private int[] masiv ={25,30,27,2,8,45,36,20} ;



    public void add(int num){
        int[] addmasiv = new int[masiv.length+1];
        addmasiv = Arrays.copyOf(masiv,addmasiv.length);
        addmasiv[addmasiv.length-1] = num;


       // for (int value : addmasiv) {           System.out.println("Элемент " + value);       }
    }

    public void atInsert(int pos, int num){
        int[] addmasiv = new int[masiv.length+1];
        System.arraycopy(masiv, 0, addmasiv, 1,masiv.length);
        addmasiv[pos] = num;
        System.arraycopy(masiv, 0, addmasiv, 0,pos);


    }

    public void atDelete(int pos){
        int[] addmasiv = new int[masiv.length-1];
        System.arraycopy(masiv, 1, addmasiv, 0,masiv.length-1);
        System.arraycopy(masiv, 0, addmasiv, 0,pos);


    }

    public int at(int pos){

        return masiv[pos];
    }


    public static void main(String[] args) {
        //int[] a2 = {25,30,27,2,8,45,36,20};
        DIntArray dIntArray = new DIntArray();
        dIntArray.add(55);

        System.out.println();
        dIntArray.atInsert(8, 88);

        dIntArray.atDelete(0);
        System.out.println(dIntArray.at(5));







    }
}
