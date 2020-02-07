package ru.progwards.java1.lessons.classes;

public class Animal {
    double weight;

    public Animal(double weight){

        this.weight = weight;
    }

    enum AnimalKind{ANIMAL, COW, HAMSTER, DUCK}

    public AnimalKind getKind(){
        return AnimalKind.ANIMAL;
    }

    enum FoodKind{UNKNOWN, HAY, CORN}

    public FoodKind getFoodKind(){
        return FoodKind.UNKNOWN;
    }

    public double getWeight(){
        return weight;
    }

    public double getFoodCoeff(){
        return 0.02;
    }

    public double calculateFoodWeight(){
        return weight * getFoodCoeff();
    }

    @Override
    public String toString(){
        return "I am " + getKind() + ", eat " + getFoodKind() + " " + calculateFoodWeight() ;
    }

    public String toStringFull(){
        return "I am " + getKind() + ", eat " + getFoodKind() + " " + calculateFoodWeight() ;
    }

    public static void main(String[] args){
        Animal animal = new Animal(68);
        System.out.println(animal.getKind());
        System.out.println(animal.getFoodKind());
        System.out.println(animal.toString());
        System.out.println(animal.toStringFull());

        Cow cow = new Cow(68);
        System.out.println(cow.getKind());
        System.out.println(cow.getFoodKind());
        System.out.println(cow.toString());
        System.out.println(cow.toStringFull());

        Hamster hamster = new Hamster(68);
        System.out.println(hamster.getKind());
        System.out.println(hamster.getFoodKind());
        System.out.println(hamster.toString());
        System.out.println(hamster.toStringFull());

        Duck duck = new Duck(68);
        System.out.println(duck.getKind());
        System.out.println(duck.getFoodKind());
        System.out.println(duck.toString());
        System.out.println(duck.toStringFull());
    }
}
