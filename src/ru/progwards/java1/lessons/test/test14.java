package ru.progwards.java1.lessons.test;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayDeque;
import java.util.PriorityQueue;

public class test14 {

    public static void  dequeueTest() {
        ArrayDeque deque = new ArrayDeque<>();

        for (int i = 0; i <= 10; i++) {
            deque.offer(i);
            if (i%2 == 0)
                deque.poll();
        }

        System.out.println(deque);//[6, 7, 8, 9, 10]
    }

    public static void pqTest() {
        PriorityQueue pQueue = new PriorityQueue<>();
        pQueue.offer(10);
        pQueue.offer(1);
        pQueue.offer(9);
        pQueue.offer(3);
        System.out.println(pQueue);//[1, 3, 9, 10]
    }

    //test 1
    /*Создайте метод с сигнатурой ArrayDeque<Integer> array2queue(int[] a),
     который копирует содержимое массива в очередь*/
    public static ArrayDeque<Integer> array2queue(int[] a){
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for (int i=0; i<a.length; i++){
            arrayDeque.offer(a[i]);
        }
        return arrayDeque;
    }

    //тесе 2
    /*Реализуйте метод с сигнатурой int sumLastAndFirst(ArrayDeque<Integer> deque) который
    возвращает сумму первого и последнего элемента очереди. При пустой очереди вернуть 0*/
    //[5, 6, 8, 25, 1, 25, 10, 11]
    public static int sumLastAndFirst(ArrayDeque<Integer> deque){

         if (deque.size() ==  1) {
            return deque.poll() * 2;
        } else if (!deque.isEmpty()){
            return deque.pollFirst() + deque.pollLast();
        } else {
            return 0;
        }

    }
    public static void main(String[] args) {
        //dequeueTest();
        //pqTest();
        int[] a = {5, 6, 8, 25, 1, 25, 10, 11};
        System.out.println(array2queue(a));

        System.out.println(sumLastAndFirst(array2queue(a)));

    }
}
