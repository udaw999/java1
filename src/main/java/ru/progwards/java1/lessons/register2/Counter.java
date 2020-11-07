package ru.progwards.java1.lessons.register2;

public class Counter {

    public static void inc(Register value){
        for (int i = 0; i < 32; i++) {

            if(((IntRegister) value).value[((IntRegister) value).value.length-i-1] == 0){
                ((IntRegister) value).value[((IntRegister) value).value.length-i-1] = 1;
                break;
            } else {
                ((IntRegister) value).value[((IntRegister) value).value.length-i-1] = 0;
            }


        }
        //for (int values : value.value) {           System.out.print("," + values);       }

    }

    public static void dec(Register value){
        for (int i = 0; i < 32; i++) {

            if(((IntRegister) value).value[((IntRegister) value).value.length-i-1] == 1){
                ((IntRegister) value).value[((IntRegister) value).value.length-i-1] = 0;
                break;
            } else {
                ((IntRegister) value).value[((IntRegister) value).value.length-i-1] = 1;
            }


        }
    }

    public static void main(String[] args) {

    }

}
