package ru.progwards.java1.lessons.queues;

import java.util.*;

public class OrderQueue {

    public static PriorityQueue<Order> orderQueue = new PriorityQueue<>();

    public static void add(Order order){

        orderQueue.offer(order);


    }
    public static Order get(){

        return orderQueue.poll();

    }

    public static void main(String[] args) {

        Order order = new Order(57000);
        Order order1 = new Order(6580);
        Order order2 = new Order(18800);
        Order order3 = new Order(19600);
        Order order4 = new Order(200);
        Order order5 = new Order(6500);
        Order order6 = new Order(18000);
        Order order7 = new Order(9000);

        System.out.println(order.getNum());
        System.out.println(order1.getNum());
        add(order);
        add(order1);
        add(order2);
        add(order3);


        while (!orderQueue.isEmpty()) {
            System.out.println(get());
        }
    }
}
