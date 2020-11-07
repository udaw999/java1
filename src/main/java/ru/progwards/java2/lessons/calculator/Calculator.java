package ru.progwards.java2.lessons.calculator;


import java.util.ArrayList;
import java.util.List;

public class Calculator {

    public static int calculate(String expression){
        List<String> list = new ArrayList<>();
        for (char c : expression.toCharArray()){
            list.add(String.valueOf(c));
        }
        System.out.println(list);
        operation(list, "*");
        operation(list, "/");
        operation(list, "-");
        operation(list, "+");

        return Integer.parseInt(list.get(0));
    }

    private static void operation(List<String> list, String znak) {
        if(list.contains(znak)){
            int index = list.lastIndexOf(znak);
            int raz = Integer.parseInt(String.valueOf(list.get(index - 1)));
            int dva = Integer.parseInt(String.valueOf(list.get(index + 1)));

            int rez = 0;
            if(znak.equals("*")){rez = raz * dva; }
            if(znak.equals("/")){rez = raz / dva; }
            if(znak.equals("+")){rez = raz + dva; }
            if(znak.equals("-")){rez = raz - dva; }

            list.remove(index + 1);
            list.set(index, String.valueOf(rez));
            list.remove(index - 1);


            if(list.contains(znak)){
                operation(list, znak);
            }
        }
    }



    public static void main(String[] args) {

        System.out.println(calculate("2+3*2"));

    }
}

