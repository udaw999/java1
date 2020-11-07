package ru.progwards.java1.lessons.arrays;

public class ATest {
    static final int count1 = 100_000_000;
    static final int count2 = 1000000;

    public static void main(String[] args) {
        PIntArray a1 = new PIntArray(1000);
        BIntArray a2 = new BIntArray(1000, 1000);

        long start = System.currentTimeMillis();
        for(int i=0; i<count1; i++)
            a1.add(i);
        long middle = System.currentTimeMillis();
        for(int i=0; i<count1; i++)
            a2.add(i);
        long stop = System.currentTimeMillis();

//            for(int i=0; i<count1; i++)
//                System.out.println(i+" "+a2.get(i)+" "+a1.get(i));
        System.out.println("PIntArray add time ="+(middle-start));
        System.out.println("BIntArray add time ="+(stop-middle));
    }
}
