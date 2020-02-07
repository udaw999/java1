package ru.progwards.java1.lessons.classes;

public class Duck extends Animal {

    public Duck(double weight){
        super(weight);
    }

    @Override
    public AnimalKind getKind(){
        return AnimalKind.DUCK;
    }

    @Override
    public FoodKind getFoodKind(){
        return FoodKind.CORN;
    }
}
