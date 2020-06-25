package ru.progwards.java2.lessons.generics;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;

public class Fruit<T>{
    private FruitBox<T> boxFruit;


    private void  addFruit(FruitBox<? extends Fruit> boxFruit){
        if (boxFruit.isEmpty()){
            System.out.println("бокс пуст");
            return;
        }

        this.boxFruit = (FruitBox<T>) boxFruit;
    }

    private float getWeight(){
        String name = boxFruit.get(0).getClass().getSimpleName();
        float result = 0;
        if (name.equals("Apple"))
            result = 1.0f * boxFruit.size();
        if (name.equals("Orange"))
            result = 1.5f * boxFruit.size();
        return result;
    }

    private void moveTo(FruitBox<T> boxFruitNev){
        String name = boxFruit.get(0).getClass().getSimpleName();
        String nameN = boxFruitNev.get(0).getClass().getSimpleName();
        if (name.equals(nameN)){
            boolean added = boxFruitNev.addAll(boxFruit);
            if (added)
                boxFruit.clear();
        } else {
            System.out.println("UnsupportedOperationException");
        }


    }

    private int сompareTo(FruitBox<T> boxFruitNev){
        int rezult = 0;
        Fruit fruitNev = new Fruit();
        fruitNev.addFruit(boxFruitNev);
        if (getWeight() > (fruitNev.getWeight())) {
            rezult = 1;
        }
        if (getWeight() < (fruitNev.getWeight())) {
            rezult = -1;
        }
        return rezult;
    }
    public static void main(String[] args) {

        FruitBox<Apple> aple1 = new FruitBox();
        for (int i = 0; i < 2; i++)
            aple1.add(new Apple());

        FruitBox<Orange> orange = new FruitBox();
        for (int i = 0; i < 0; i++)
            orange.add(new Orange());

        FruitBox<Orange> orange2 = new FruitBox();
        for (int i = 0; i < 9; i++)
            orange2.add(new Orange());

        FruitBox<Orange> orange3 = new FruitBox();
        for (int i = 0; i < 2; i++)
            orange3.add(new Orange());

        Fruit fruit = new Fruit();
        fruit.addFruit(aple1);
        //System.out.println(fruit.getWeight());
        System.out.println(fruit.сompareTo(orange3));

//        fruit.addFruit(orange);
//        System.out.println(fruit.getWeight());
//
//        fruit.addFruit(orange2);
//        System.out.println(fruit.getWeight());
        System.out.println(orange3.toString());
        System.out.println(fruit.boxFruit.toString());
//        fruit.addFruit(9,new Apple());
//        fruit.addFruit(3,new Orange());
     //   fruit.addFruit(orange3);
        //System.out.println(fruit.stockFruit);

    }


}
