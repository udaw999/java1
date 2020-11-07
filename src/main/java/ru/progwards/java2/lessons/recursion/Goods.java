package ru.progwards.java2.lessons.recursion;

import java.time.Instant;

public class Goods {
    public String name;// - наименование
    public String number;// - артикул
    public int available;// - количество на складе
    public double price;// - цена
    public Instant expired;// - срок годности

    public Goods(String name, String number, int available, double price, String expired) {
        this.name = name;
        this.number = number;
        this.available = available;
        this.price = price;
        this.expired = Instant.parse(expired);
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public int getAvailable() {
        return available;
    }

    public double getPrice() {
        return price;
    }

    public Instant getExpired() {
        return expired;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", available=" + available +
                ", price=" + price +
                ", expired=" + expired +
                '}';
    }
}
