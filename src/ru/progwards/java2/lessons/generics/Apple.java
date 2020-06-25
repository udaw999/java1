package ru.progwards.java2.lessons.generics;

public class Apple extends Fruit {
    private final double WIEGHT = 1.0f;

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
