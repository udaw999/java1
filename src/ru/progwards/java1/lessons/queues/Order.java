package ru.progwards.java1.lessons.queues;

public class Order {
    private double sum;//  - сумма заказа
    private int num = 0;//  - номер заказа

    public Order(double sum) {
        int num = 0;
        this.sum = sum;
        this.num = num++;
    }

    public double getSum() {
        return sum;
    }

    public int getNum() {
        return num;
    }


}
