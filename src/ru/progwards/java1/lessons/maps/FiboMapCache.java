package ru.progwards.java1.lessons.maps;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class FiboMapCache {
    private Map<Integer, BigDecimal> fiboCache = new HashMap<>();
    boolean cacheOn;

    public FiboMapCache(boolean cacheOn){
        this.cacheOn = cacheOn;
    }

    public BigDecimal fiboNumber(int n){
        if (cacheOn){
            if (fiboCache.containsKey(n)){
                return fiboCache.get(n);

            } else {
                int i = 1;

                int fib1 = 0;
                int fib2 = 1;
                int fib = 1;

                while (i < n) {

                    fib = fib1 + fib2;
                    fib1 = fib2;
                    fib2 = fib;
                    i++;
                }
                fiboCache.put(n,BigDecimal.valueOf(fib));
                return BigDecimal.valueOf(fib);
            }
        } else {
            int i = 1;

            int fib1 = 0;
            int fib2 = 1;
            int fib = 1;

            while (i < n) {

                fib = fib1 + fib2;
                fib1 = fib2;
                fib2 = fib;
                i++;
            }

            return BigDecimal.valueOf(fib);
        }
    }

    public void clearCahe(){
        fiboCache.clear();
    }

    public  void test(){

        long start = System.currentTimeMillis();
        for (int i=1; i<=1000; i++){
            new FiboMapCache(true).fiboNumber(i);
        }
        long rezult1 = System.currentTimeMillis() - start;

        long start2 = System.currentTimeMillis();
        for (int i=1; i<=1000; i++){
            new FiboMapCache(false).fiboNumber(i);
        }
        long rezult2 = System.currentTimeMillis() - start2;
        System.out.println("fiboNumber cacheOn=true время выполнения " + rezult1);
        System.out.println("fiboNumber cacheOn=false время выполнения " + rezult2);
    }

    public static void main(String[] args) {
        FiboMapCache fiboMapCache = new FiboMapCache(true);

        System.out.println(fiboMapCache.fiboNumber(6));
        System.out.println(fiboMapCache.fiboNumber(25));
        System.out.println(fiboMapCache.fiboNumber(6));
        //fiboMapCache.clearCahe();
        System.out.println(fiboMapCache.fiboCache);

        //test();
    }
}
