package ru.progwards.java1.lessons.register1;
//A5.1 Электроника в беззнаковых числах
public  class Bit {
    boolean value;

    public Bit(){}
    public Bit(boolean value){
        this.value = value;
    }
    public  String toString(){
        if(value == true){
            return "1";
        } else {
            return "0";
        }
    }
}
