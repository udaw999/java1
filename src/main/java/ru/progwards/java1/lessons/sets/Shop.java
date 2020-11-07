package ru.progwards.java1.lessons.sets;

import java.util.List;
//магазин
public class Shop{
    private List<Product> products;//товары имеющиеся в магазине

    public Shop(List<Product> products){
        this.products = products;
    }

    public List<Product> getProducts(){
        return products;
    }

    @Override
    public String toString() {
        return ""+products;
    }
}