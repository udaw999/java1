package ru.progwards.java2.lessons.recursion;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class HanoiTower {
    private int size;
    private int pos;
    private final Deque<Integer> startPin = new ArrayDeque<>();
    private final Deque<Integer> endPin = new ArrayDeque<>();
    private final Deque<Integer> bufferPin = new ArrayDeque<>();

    public HanoiTower(int size, int pos){
        if (pos >= 3 || pos < 0){
            System.out.println("Значение pos введено не верно! " +
                    "pos должно быть 0, 1 или 2. Другие значения не принимаются.");
            this.pos = pos;
            return;
        }
        this.size = size;
        this.pos = pos;
        for (int i = size; i > 0 ; i--){
            this.startPin.addFirst(i);
        }

    }

    public void move(int from, int to){
        if (from == 1 && to == 2)
            endPin.addFirst(startPin.pollFirst());
        if (from == 1 && to == 3)
            bufferPin.addFirst(startPin.pollFirst());
        if (from == 2 && to == 3)
            bufferPin.addFirst(endPin.pollFirst());
        if (from == 2 && to == 1)
            startPin.addFirst(endPin.pollFirst());
        if (from == 3 && to == 1)
            startPin.addFirst(bufferPin.pollFirst());
        if (from == 3 && to == 2)
            endPin.addFirst(bufferPin.pollFirst());
    }

    void print(){
        Iterator<Integer> startIterator = startPin.iterator();
        Iterator<Integer> endIterator = endPin.iterator();
        Iterator<Integer> bufferIterator = bufferPin.iterator();
        if (pos == 0){
            for (int i = size; i > 0 ; i--){
                pyramidPrint(startIterator, i, startPin);
                pyramidPrint(endIterator, i, endPin);
                pyramidPrint(bufferIterator, i, bufferPin);
                System.out.println();
            }

        } else if (pos == 1){
            for (int i = size; i > 0 ; i--){
                pyramidPrint(bufferIterator, i, bufferPin);
                pyramidPrint(startIterator, i, startPin);
                pyramidPrint(endIterator, i, endPin);
                System.out.println();
            }
        } else {
            for (int i = size; i > 0 ; i--){
                pyramidPrint(endIterator, i, endPin);
                pyramidPrint(bufferIterator, i, bufferPin);
                pyramidPrint(startIterator, i, startPin);
                System.out.println();
            }
        }
        System.out.println("=================\n");
    }

    private void pyramidPrint(Iterator<Integer> iterator, int i, Deque<Integer> deguePin) {
        if (deguePin.size() <= i - 1) {
            System.out.print("  I   ");
        } else {
            System.out.printf("<%03d> ", iterator.next());
        }
    }

    private void hanoiTowers(int quantity, int from, int to, int buf_peg)	{
        if (quantity != 0)
        {

            hanoiTowers(quantity-1, from, buf_peg, to);

            move(from,to);
            print();

            hanoiTowers(quantity-1, buf_peg, to, from);
        }
    }
    void setTrace(boolean on){
        if (!on)
            return;
        if (pos >= 3 || pos < 0)
            return;
        print();
        hanoiTowers(size,1,2,3);
    }

    public static void main(String[] args) {
        HanoiTower hanoiTower = new HanoiTower(10, 0);
        hanoiTower.setTrace(true);
    }
}
