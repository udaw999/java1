package ru.progwards.java1.lessons.queues;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Calculate{
    //2.2*(3+12.1), используя класс StackCalc


    public static double calculation1(){
        StackCalc.arrayDeque.push(12.1);
        StackCalc.arrayDeque.push((double) 3);
        StackCalc.arrayDeque.push(2.2);
        StackCalc.add();
        StackCalc.mul();
        return StackCalc.arrayDeque.pop();
    }// возвращающую результат вычисления следующей формулы:

    public static void main(String[] args) {

        System.out.println(calculation1());
    }
}
