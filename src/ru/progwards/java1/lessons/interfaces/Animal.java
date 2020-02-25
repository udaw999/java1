package ru.progwards.java1.lessons.interfaces;

import java.util.Arrays;
import java.util.Objects;

public class Animal implements FoodCompare, CompareWeight {
    double weight;

    public Animal(double weight){

        this.weight = weight;
    }

    enum AnimalKind{ANIMAL, COW, HAMSTER, DUCK}

    public AnimalKind getKind(){
        return AnimalKind.ANIMAL;
    }

    enum FoodKind{UNKNOWN, HAY, CORN}
    //определение еды животного
    public FoodKind getFoodKind(){
        return FoodKind.UNKNOWN;
    }
    //вес
    public double getWeight(){
        return weight;
    }
    //коэффициент питания
    public double getFoodCoeff(){
        return 0.02;
    }
    //расчет веса питания
    public double calculateFoodWeight(){
        return weight * getFoodCoeff();
    }

    @Override
    public String toString(){
        return "I am " + getKind() + ", eat " + getFoodKind();
    }

    public String toStringFull(){
        return "I am " + getKind() + ", eat " + getFoodKind() + " " + calculateFoodWeight() ;
    }
/*сравниваем вес животного
возвращает true, если объекты равны и false если не равны по параметру - вес животного */

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) return true;
        if (anObject == null || getClass() != anObject.getClass()) return false;
        Animal animal = (Animal) anObject;
        return Double.compare(animal.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight);
    }

    //метод который возвращает информацию о цене 1 кг еды.
    public double getFood1kgPrice(){
        switch (getFoodKind()){
            case HAY: return 20;
            case CORN: return 50;
            case UNKNOWN: return 0;
        }
        return 0;
    }
    /*который возвращает информацию о цене еды для данного животного
    по формуле calculateFoodWeight() * getFood1kgPrice()
     */
    public double getFoodPrice(){
        return calculateFoodWeight() * getFood1kgPrice();
    }
    //который возвращает результаты сравнения цены еды для данного животного с ценой еды для другого животного
    @Override
    public int compareFoodPrice(Animal animal){

        return Double.compare(getFoodPrice(), animal.getFoodPrice());
    }
    //Сравнение на основе веса животного
    @Override
    public CompareResult compareWeight(CompareWeight smthHasWeigt) {
        Animal another = (Animal)smthHasWeigt;//создаем переменную со
        // вторым весом( не понял способа, ТО ЧТО ПОСЛЕ = )

        if (this.getWeight() < another.getWeight()) {
            return CompareResult.LESS;
        }
        if (this.getWeight()> another.getWeight()) {
            return CompareResult.GREATER;
        }
        return CompareResult.EQUAL;
    }


    public static void main(String[] args){
        Animal animal = new Animal(20);
        Cow cow = new Cow(300);
        Hamster hamster = new Hamster(40);
        Duck duck = new Duck(68);

        CompareWeight[] a = {animal, cow, hamster, duck };
        System.out.println(Arrays.toString(a));
        Food.sort(a);
        System.out.println(Arrays.toString(a));
//        System.out.println(cow.compareWeight(duck));
//        System.out.println(duck.equals(cow));
//        System.out.println(cow.hashCode());
//        System.out.println(duck.hashCode());
//        System.out.println(cow.weight);
//        System.out.println(cow.weight);
//        System.out.println(cow.getFood1kgPrice());
//        System.out.println("информацию о цене еды для данного животного " + animal.getKind() + " вес " + animal.weight +
//                 "  расчет веса питания " + animal.calculateFoodWeight() + " коэффициент " + animal.getFoodCoeff() +
//                " информацию о цене 1 кг еды " + animal.getFood1kgPrice());
//        System.out.println("цена еды " + animal.getFoodPrice());
//        System.out.println("информацию о цене еды для данного животного " + hamster.getKind() + " вес " + hamster.weight +
//                "  расчет веса питания " + hamster.calculateFoodWeight() + " коэффициент " + hamster.getFoodCoeff() +
//                " информацию о цене 1 кг еды " + hamster.getFood1kgPrice());
//        System.out.println("цена еды " + hamster.getFoodPrice());
//        System.out.println("информацию о цене еды для данного животного " + duck.getKind() + " вес " + duck.weight +
//                "  расчет веса питания " + duck.calculateFoodWeight() + " коэффициент " + duck.getFoodCoeff() +
//                " информацию о цене 1 кг еды " + duck.getFood1kgPrice());
//        System.out.println("цена еды " + duck.getFoodPrice());
//        System.out.println("информацию о цене еды для данного животного " + cow.getKind() + " вес " + cow.weight);
//        System.out.println(cow.getFoodPrice());
//        System.out.println("сравнения цены еды для данного животного с ценой еды для другого животного "  );
//        System.out.println(hamster.getFoodPrice() + " сравниваем с " + animal.getFoodPrice());
//        System.out.println(hamster.compareFoodPrice(animal));
    }
}