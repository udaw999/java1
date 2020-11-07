package ru.progwards.java1.lessons.darrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class DPArray {
    ArrayList<ArrayMy> array;
     private int capacity;
    final Random random = new Random(3);
    int sizeCount;
    private int sizeNev;

    private class ArrayMy {

        static final int MIN_SIZE = 2;
        int[] page;
        int size;

//        private ArrayMy(){
//                this.page = new int[MIN_SIZE];
//                this.size = MIN_SIZE;
//        }
        private ArrayMy(int blockSize){
            this.size = random.nextInt(blockSize/2)+5;
            this.page = new int[size];

        }
        private ArrayMy(int[] page){
            this.page = page;
            this.size = page.length;
        }

        @Override
        public String toString() {
            return "ArrayMy{" +

                    ", page=" + Arrays.toString(page) +
                    ", size=" + size +
                    '}';
        }
    }

    public DPArray(int blockSize) {
        array = new ArrayList<>();
        array.add(new ArrayMy(blockSize));
        capacity = blockSize;
    }

    public void add(int item) {
        ArrayMy pages = array.get(array.size()-1);

        if (sizeCount >= pages.size) {
            int[] pageNul = new int[random.nextInt(capacity/2)+5];
            array.add(new ArrayMy(pageNul));
            pages = array.get(array.size()-1);
            sizeCount = 0;
      }
            pages.page[sizeCount]= item;
            sizeCount++;
    }
    int get(int index) {
        int ind = index;
        for (int i=0; i<array.size();i++){
            ind = ind - array.get(i).size;
//            if (ind==0){
//                return array.get(i+1).page[0];
//            }
            if (ind <= -1) {

               return array.get(i).page[array.get(i).size + ind];
            }
        }

       return 0;
    }

    public int size(){
        int sumSize= 0;
        for (int i=0; i<array.size()-1; i++){
            sumSize += array.get(i).size;
        }
        sumSize += sizeCount;
        return sumSize;
    }
    public void remove(int index){
        if (index > size()){
            System.out.println("идекс больше колличества элементов");
            return;
        }
        int ind = index;
        for (int i=0; i<array.size();i++){
            ind = ind - array.get(i).size;

            if (ind <= -1) {
                int pageRemoveIndex = array.get(i).size + ind;
                int[] pageNev = new int[array.get(i).page.length-1];
                System.out.println("pageRemoveIndex- " + pageRemoveIndex);
                System.out.println("size- " + array.get(i).size );
                if (pageRemoveIndex == array.get(i).size-1){
                    System.arraycopy(array.get(i).page,0,pageNev,0,pageNev.length);
                    array.set(i,new ArrayMy(pageNev));
                    return;
                } else  if (pageRemoveIndex == 0){
                    System.arraycopy(array.get(i).page,1,pageNev,0,pageNev.length);
                    array.set(i,new ArrayMy(pageNev));
                    return;
                } else {
                    System.arraycopy(array.get(i).page,0,pageNev,0,pageNev.length);
                    System.arraycopy(array.get(i).page,pageRemoveIndex+1,pageNev,pageRemoveIndex,pageNev.length-pageRemoveIndex);
                    array.set(i,new ArrayMy(pageNev));
                    return;
                }

            }
        }
    }

    public void add(int index, int item){
        if (index > size()){
            System.out.println("идекс больше колличества элементов");
            return;
        }
        int ind = index;
        for (int i=0; i<array.size();i++) {
            ind = ind - array.get(i).size;

            if (ind <= -1) {
                int pageAddIndex = array.get(i).size + ind;
                int[] pageNev = new int[array.get(i).page.length+1];
                System.out.println("pageAddIndex- " + pageAddIndex);
                if (array.get(i).size != capacity){
                    if (pageAddIndex != 0){
                        System.arraycopy(array.get(i).page,0,pageNev,0,pageNev.length-1);
                        System.arraycopy(array.get(i).page,pageAddIndex,pageNev,pageAddIndex+1,pageNev.length-pageAddIndex-1);
                        pageNev[pageAddIndex] = item;
                        array.set(i,new ArrayMy(pageNev));
                        return;
                    } else {
                        System.arraycopy(array.get(i).page,0,pageNev,1,pageNev.length-1);
                        pageNev[pageAddIndex] = item;
                        array.set(i,new ArrayMy(pageNev));
                        return;
                    }
                } else {
                    int[] pageBefore = new int[pageAddIndex+1];
                    System.arraycopy(array.get(i).page,0,pageBefore,0,pageBefore.length-1);
                    pageBefore[pageBefore.length-1] = item;
                    int[] pageAfter = new int[capacity - pageAddIndex];
                    System.arraycopy(array.get(i).page,pageAddIndex,pageAfter,0,pageAfter.length);
                    array.set(i,new ArrayMy(pageBefore));
                    array.add(i+1,new ArrayMy(pageAfter));
                    return;
                }


            }

        }
    }
    //заполняем пустоты
    public void removeVoid(){
        sizeNev = array.size();
        for (int i=0; i<sizeNev;i++) {

          int free =  capacity - array.get(i).size;//свободное место
            int[] pageNev = new int[capacity];
            System.arraycopy(array.get(i).page,0,pageNev,0,array.get(i).size);
            if (capacity - array.get(i+1).size == free){
                array.remove(i+1);
            }
            if (free < array.get(i+1).size){
                System.arraycopy(array.get(i+1).page,0,pageNev,array.get(i).size,free);
            } else {
                System.arraycopy(array.get(i+1).page,0,pageNev,array.get(i).size,array.get(i+1).page.length);
                free = free-array.get(i+1).page.length;
                array.remove(i+1);
                System.out.println("free" + free);
                System.arraycopy(array.get(i+1).page,0,pageNev,capacity - free,free);
            }

            array.set(i,new ArrayMy(pageNev));
            int[] pageNevPlus1 = new int[array.get(i+1).size-free];
            System.arraycopy(array.get(i+1).page,free,pageNevPlus1,0,array.get(i+1).size-free);
            array.set(i+1,new ArrayMy(pageNevPlus1));
            sizeNev = array.size()-1;
        }
    }
    public static void main(String[] args) {
        final int count1 = 100;
            DPArray a1 = new DPArray(10);
//            a1.add(25);
//        a1.add(35);
//        a1.add(45);
//        a1.add(55);
//        a1.add(65);
//        a1.add(75);
//        a1.add(85);
//        a1.add(95);
//        a1.add(105);

        for(int i=0; i<count1; i++)
            a1.add(i);

                    for(int i=0; i<a1.array.size(); i++)
                System.out.println(i+" "+a1.array.get(i));

                    for(int i=0; i<count1; i++)
                System.out.println(i+"  "+a1.get(i));

//                    a1.remove(34);
//                a1.add(85);
//        a1.add(95);
//        a1.add(105);
        a1.add(39,394);
        a1.add(40,404);
        a1.add(41,414);
        a1.add(42,424);
        a1.add(36,364);
        a1.add(88,884);
        a1.add(44,454);
        a1.add(45,464);
        System.out.println("size- "+a1.size());
        System.out.println();
        for(int i=0; i<a1.size(); i++)
            System.out.println(i+"  "+a1.get(i));

        System.out.println();
        for(int i=0; i<a1.array.size(); i++)
            System.out.println(i+" "+a1.array.get(i));

        a1.removeVoid();
        a1.add(105);
        a1.add(44,454);
        a1.remove(34);
        a1.removeVoid();
        System.out.println();
        for(int i=0; i<a1.array.size(); i++)
            System.out.println(i+" "+a1.array.get(i));
    }

}
