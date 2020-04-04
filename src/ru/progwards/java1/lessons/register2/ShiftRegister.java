package ru.progwards.java1.lessons.register2;

public class ShiftRegister {

    public static void left(Register value){
        for (int i = 0; i < 31; i++) {

            ((IntRegister) value).value[i]=((IntRegister) value).value[i+1];

        }
        ((IntRegister) value).value[31]=0;
        // for (int values : value.value) {           System.out.print("," + values);       }

    }

    public static void right(Register value){
        for (int i = 0; i < 31; i++) {

            ((IntRegister) value).value[((IntRegister) value).value.length-i-1]=((IntRegister) value).value[((IntRegister) value).value.length-i-2];

        }
        ((IntRegister) value).value[0] = 0;
    }

    public static void main(String[] args) {
        int value = 458;
        Register intRegist = new IntRegister(value);

        right(intRegist);
        System.out.println(intRegist.toDecString());
    }
}
