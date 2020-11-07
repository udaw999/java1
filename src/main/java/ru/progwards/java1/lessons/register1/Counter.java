package ru.progwards.java1.lessons.register1;

public class Counter {
    public static void inc(ByteRegister value){

        for (int i = 0; i < 8; i++) {

            if(value.value[value.value.length-i-1] == 0){
                value.value[value.value.length-i-1] = 1;
                break;
            } else {
                value.value[value.value.length-i-1] = 0;
            }


        }
        //for (int values : value.value) {           System.out.print("," + values);       }
    }
    public static void dec(ByteRegister value){
        for (int i = 0; i < 8; i++) {

            if(value.value[value.value.length-i-1] == 1){
                value.value[value.value.length-i-1] = 0;
                break;
            } else {
                value.value[value.value.length-i-1] = 1;
            }


        }

    }

    public static void main(String[] args) {
        ByteRegister b = new ByteRegister((byte) 100);

        inc(b);
        System.out.println("вызов внизу " + b.toDecString());
    }
}
