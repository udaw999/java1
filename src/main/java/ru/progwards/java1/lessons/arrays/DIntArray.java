package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;

public class DIntArray {
    private int[] masiv = {};



    public void add(int num){

        int[] masiv = new int[this.masiv.length+1];
        masiv = Arrays.copyOf(this.masiv,masiv.length);
        masiv[masiv.length-1] = num;

        this.masiv = masiv;

        //for (int value : masiv) {           System.out.println("Элемент " + value);       }
    }

    public void atInsert(int pos, int num){
        int[] addmasiv = new int[masiv.length+1];
        System.arraycopy(masiv, 0, addmasiv, 1,masiv.length);
        addmasiv[pos] = num;
        System.arraycopy(masiv, 0, addmasiv, 0,pos);
        this.masiv = addmasiv;

    }

    public void atDelete(int pos){
        int[] addmasiv = new int[masiv.length-1];
        System.arraycopy(masiv, 1, addmasiv, 0,masiv.length-1);
        System.arraycopy(masiv, 0, addmasiv, 0,pos);
        this.masiv = addmasiv;
        for (int value : masiv) {           System.out.println("Элемент " + value);       }
    }

    public int at(int pos){

        return this.masiv[pos];
    }


    public static void main(String[] args) {
        //int[] a2 = {25,30,27,2,8,45,36,20};
        DIntArray dIntArray = new DIntArray();

        dIntArray.add(63);
        dIntArray.add(-91);
        dIntArray.add(84);
        dIntArray.add(-11);
        dIntArray.add(-98);
        dIntArray.add(65);
        dIntArray.add(41);
        dIntArray.add(43);

        dIntArray.add(96);
        dIntArray.add(96);
        dIntArray.add(-67);
        dIntArray.add(-23);
        dIntArray.add(15);
        dIntArray.add(-46);
        dIntArray.add(-36);
        dIntArray.add(68);
        dIntArray.add(-30);
        dIntArray.add(-75);
        System.out.println();

       //dIntArray.atInsert(4, 17);
        System.out.println();
        dIntArray.atDelete(6);
        System.out.println(dIntArray.at(6));







    }
}
