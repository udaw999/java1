package ru.progwards.java1.lessons.queues;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public  class StackCalc {
    public static ArrayDeque<Double> arrayDeque = new ArrayDeque();
    // положить value на вершину стека

    public void push(double value){
        arrayDeque.offerLast(value);
    }

//взять (убрать) значение с вершины стека

    public double pop(){
        return  arrayDeque.pollLast();
    }


    //сложить 2 верхних значения на стеке, результат положить на стек. В итогу в стеке должно быть на один элемент меньше
    public static void add(){
        arrayDeque.offerLast(arrayDeque.pollLast() + arrayDeque.pollLast());

    }
    //вычесть верхнее значение на стеке, из следующего по глубине, результат положить на стек. В итоге в стеке должно быть на один элемент меньше

    public static void sub(){
        double dobEnd = arrayDeque.pollLast();
        arrayDeque.offerLast( arrayDeque.pollLast() - dobEnd);
    }
    // умножить 2 верхних значения на стеке, результат положить на стек. В итогу в стеке должно быть на один элемент меньше
    public static void mul(){
        arrayDeque.offerLast(arrayDeque.pollLast() * arrayDeque.pollLast());
    }
    //
    //    // поделить на верхнее значение на стеке, следующее по глубине, результат положить на стек. В итоге в стеке должно быть на один элемент меньше
    public static void div(){
        double dobEnd = arrayDeque.pollLast();
        arrayDeque.offerLast( arrayDeque.pollLast() / dobEnd);
    }
    @Override
    public String toString() {
        return "StackCalc{" +
                "arrayDeque=" + arrayDeque +
                '}';
    }

    public static void main(String[] args) {
        StackCalc stackCalc = new StackCalc();
        Collection<Double> collection = new LinkedList<>(List.of(25.3,36.2,48.5,85.5,145.0,25.0));
        stackCalc.arrayDeque.addAll(collection);

        System.out.println(stackCalc);
        //stackCalc.add();
        //stackCalc.sub();
        //stackCalc.mul();
        stackCalc.div();
        System.out.println(stackCalc);
    }
}
