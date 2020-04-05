package ru.progwards.java1.lessons.register2;

public class Summator {

    public static void add(Register value1, Register value2){

        int numDop =0;
        int numSum =0;
        for (int i = 0; i < 32; i++) {

            numSum = numDop + ((IntRegister) value1).value[((IntRegister) value1).value.length-i-1] +
                    ((IntRegister) value2).value[((IntRegister) value2).value.length-i-1];
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

        toTwosComplement(value2);
        add(value1,value2);

    }
    private static Register toTwosComplement(Register value){
        for (int i = 0; i < 32; i++) {
            //инвертация
            ((IntRegister) value).value[i] = (byte) (((IntRegister) value).value[i] ^ 1);
        }
        Counter.inc(value);
        System.out.println("ушло - " + value);
        return value;
    }
    public static void main(String[] args) {

        Register val1 = new IntRegister(541);
        Register val2 = new IntRegister((byte)-41);

        sub(val1,val2);
        System.out.println(val1.toDecString());
//        int value = 451502843;
//        Register intRegist = new IntRegister(value);
//
//        //dec(intRegist);
//
//        Summator.toTwosComplement(intRegist);
//        System.out.println(intRegist.toDecString());

    }
}
