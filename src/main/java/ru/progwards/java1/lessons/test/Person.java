package ru.progwards.java1.lessons.test;

public class Person {
    private final String name;
    private final int god;

    public Person(String name, int god) {

        this.name = name;
        this.god = god;
    }

    public String getName() {
        return name;
    }

    public int getGod() {
        return god;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", god=" + god +
                '}';
    }
}
