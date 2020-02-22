package ru.progwards.java1.lessons.interfaces;

import java.util.Objects;

public class Animal implements FoodCompare {
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

        return Double.compare(getFood1kgPrice(), animal.getFood1kgPrice());
    }

    public static void main(String[] args){
        Animal animal = new Animal(1D);
        Cow cow = new Cow(1D);
        Hamster hamster = new Hamster(68);
        Duck duck = new Duck(68);

        System.out.println(duck.equals(cow));
        System.out.println(cow.hashCode());
        System.out.println(duck.hashCode());
        System.out.println(cow.weight);
        System.out.println(cow.weight);
        System.out.println(cow.getFood1kgPrice());
        System.out.println("информацию о цене еды для данного животного " + cow.getKind());
        System.out.println(cow.getFoodPrice());
        System.out.println("информацию о цене еды для данного животного " + duck.getKind());
        System.out.println(duck.getFoodPrice());
        System.out.println("сравнения цены еды для данного животного с ценой еды для другого животного");
        System.out.println(duck.compareFoodPrice(cow));
        System.out.println();
        System.out.println("информацию о цене еды для данного животного " + cow.getKind() + " вес " + cow.weight);
        System.out.println(cow.getFoodPrice());
        System.out.println("информацию о цене еды для данного животного " + duck.getKind() + " вес " + duck.weight);
        System.out.println(duck.getFoodPrice());
        System.out.println("сравнения цены еды для данного животного с ценой еды для другого животного");
        System.out.println(animal.compareFoodPrice(cow));
        System.out.println(animal.equals(cow));
    }
}
