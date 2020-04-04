package ru.progwards.java1.lessons.register2;

public class Summator {

    public static void add(Register value1, Register value2){
        int numDop =0;
        int numSum =0;
        for (int i = 0; i < 32; i++) {

            numSum = numDop + ((IntRegister) value1).value[((IntRegister) value1).value.length-i-1] + ((IntRegister) value2).value[((IntRegister) value2).value.length-i-1];
            ((IntRegister) value1).value[((IntRegister) value1).value.length - i - 1] = (byte) numSum;
            if(numSum == 2) {
                ((IntRegister) value1).value[((IntRegister) value1).value.length - i - 1] = 0;
                numDop = 1;
            } else  if (numSum == 3){
                ((IntRegister) value1).value[((IntRegister) value1).value.length - i - 1] = 1;
                numDop = 1;
            } else {
                numDop = 0;
            }


        }
    }

    public static void sub(Register value1, Register value2){

    }
}
