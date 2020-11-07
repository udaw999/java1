package ru.progwards.java1.lessons.queues;


import java.util.*;

/*3 - заказы до 10000 руб включительно
2 - заказы от 10000 до 20000 руб включительно
1 - заказы от 20000 руб*/
public class Order implements Comparable<Order> {
    private static int counter = 1;
    private double sum;//  - сумма заказа
    private int num;//  - номер заказа
    private int priority = 0;

    public Order(double sum) {

        this.num = counter++;//систему автонумерации, начиная с 1

        this.sum = sum;



        if (sum < 10000){
            this.priority = 3;
        } else if (sum < 20000 && sum > 10000){
            this.priority = 2;
        } else {
            this.priority = 1;
        }
    }

    @Override   public int compareTo( Order o) {
        int comparePriority = Integer.compare(priority, o.priority);

        return comparePriority == 0 ? Integer.compare(num, o.num) : comparePriority;
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
                ", num=" + getNum() +
                ", priority=" + priority +
                '}';
    }
}
