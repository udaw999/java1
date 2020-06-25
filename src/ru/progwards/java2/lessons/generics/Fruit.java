package ru.progwards.java2.lessons.generics;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;

public class Fruit<T> {
    HashMap<T, FruitBox> stockFruit;
    public Fruit(){
        stockFruit = new HashMap<>();

    }

    @Override
    public String toString() {
        return "Fruit{" +
                "stockFruit=" + stockFruit +
                '}';
    }

    private void  addFruit(int countFruit, T nameFruit){
        System.out.println("yuy " + nameFruit.getClass().getSimpleName());
        if (stockFruit.containsKey(nameFruit.getClass().getSimpleName())){
            for (int i = 0; i < countFruit; i++){

                stockFruit.get(nameFruit.getClass().getSimpleName()).add(nameFruit);
            }

        } else {

            FruitBox fruitBox = new FruitBox();
            for (int i = 0; i < countFruit; i++)
                fruitBox.add(nameFruit);

            stockFruit.put((T) nameFruit.getClass().getSimpleName(),fruitBox);
        }



    }

    public static void main(String[] args) {
        Fruit fruit = new Fruit();
        fruit.addFruit(9,new Apple());
        fruit.addFruit(3,new Orange());
        fruit.addFruit(2,new Orange());
        fruit.addFruit(9,new Apple());
        fruit.addFruit(3,new Orange());
        fruit.addFruit(3,new ArraySort());
        System.out.println(fruit.stockFruit);

    }

}
