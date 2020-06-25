package ru.progwards.java2.lessons.generics;

public class Orange extends Fruit {
    private final double WIEGHT = 1.5f;

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
