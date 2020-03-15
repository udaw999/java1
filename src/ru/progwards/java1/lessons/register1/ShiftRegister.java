package ru.progwards.java1.lessons.register1;

public class ShiftRegister {

    public static void left(ByteRegister value){
        //System.out.println(value);

        for (int i = 0; i < 7; i++) {

            value.value[i]=value.value[i+1];

        }
        value.value[7]=0;
       // for (int values : value.value) {           System.out.print("," + values);       }
    }

    public static void right(ByteRegister value){

        for (int i = 0; i < 7; i++) {

            value.value[value.value.length-i-1]=value.value[value.value.length-i-2];

        }
        value.value[0] = 0;

    }

    public static void main(String[] args) {
        ByteRegister b = new ByteRegister((byte) 1);

        left(b);
        System.out.println();
        System.out.println(b.toDecString());
    }
}
