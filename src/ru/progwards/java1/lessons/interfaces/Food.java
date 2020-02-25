package ru.progwards.java1.lessons.interfaces;

import java.util.Arrays;

//Еда
public class Food implements CompareWeight{
    //вес
    private int weight;
    //конструктор, принимающий и устанавливающий значение веса
    public  Food (int weight){

        this.weight = weight;
    }
    //значение веса
    public int getWeight(){

        return weight;
    }

    @Override
    public CompareResult compareWeight(CompareWeight smthHasWeigt) {
        Food another = (Food) smthHasWeigt;//создаем переменную со
        // вторым весом( не понял способа, ТО ЧТО ПОСЛЕ = )

        if (this.getWeight() < another.getWeight()) {
            return CompareResult.LESS;
        }
        if (this.getWeight()> another.getWeight()) {
            return CompareResult.GREATER;
        }
        return CompareResult.EQUAL;
    }


//    public static void sort(int[] a){
//
//        for (int i=0; i<a.length ; i++){
//            for (int j=i+1 ; j<a.length; j++){
//                if(a[i] > a[j]){
//                    int aj = a[j];
//                    int ai = a[i];
//                    a[i] = aj;
//                    a[j] = ai;
//                }
//            }
//            //for (int value : a) {           System.out.println("Элемент " + value);       }
//        }
//
//    }

    public static void sort(CompareWeight[] a){

        for (int i=0; i<a.length ; i++){

            for (int j=i+1 ; j<a.length; j++){

                if(a[i].compareWeight(a[j]) == CompareResult.GREATER) {
                    CompareWeight aj = a[j];
                    CompareWeight ai = a[i];
                    a[i] = aj;
                    a[j] = ai;
                }
            }
            //for (int value : a) {           System.out.println("Элемент " + value);       }
        }

    }

    public static void main(String[] args) {
        Food food = new Food(50);
        Food food1 = new Food(60);
        Food food2 = new Food(20);
        Food food3 = new Food(100);
        Food food4 = new Food(10);
        CompareWeight[] a = {food, food1, food2, food3, food4 };
        System.out.println(Arrays.toString(a));

        System.out.println(food.getWeight());

       // CompareWeight[] a = new CompareWeight[]{a1,a1};
        //a = {25,30,27,2,22};

                //CompareWeight[] a = {food.compareWeight(food1), food1.compareWeight(food2), food2.compareWeight(food3)};
        System.out.println(food.compareWeight(food1));
        System.out.println("вызов сорт");
        sort(a);
    }
}
//    Вывод для отладки можно сделать так:
//        System.out.println(Arrays.toString(a));
//        Чтобы сделать из функции main там нужно сначала созать массив,
//        сделать вывод на экран, потом вызвать функцию sort, сделать вывод на экран.