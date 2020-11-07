package ru.progwards.java1.lessons.sets;
// товар
public class Product{
    private String code;//уникальный артикул товара

    public Product(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }

    @Override
    public String toString() {
        return code;
    }
}
