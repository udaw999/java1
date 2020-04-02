package ru.progwards.java1.lessons.queues;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
/*3 - заказы до 10000 руб включительно
2 - заказы от 10000 до 20000 руб включительно
1 - заказы от 20000 руб*/
public class Order implements Comparable<Order> {
    private double sum;//  - сумма заказа
    private int num = 1;//  - номер заказа
    private int priority = 0;

    public Order(double sum) {

        this.sum = sum;


        if (sum < 10000){
            this.priority = 1;
        } else if (sum < 20000 && sum > 10000){
            this.priority = 2;
        } else {
            this.priority = 3;
        }
    }

    @Override   public int compareTo( Order o) {
        return Integer.compare(priority, o.priority);
    }

    public double getSum() {
        return sum;
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return "Order{" +
                "sum=" + sum +
                ", num=" + num +
                ", priority=" + priority +
                '}';
    }
}
