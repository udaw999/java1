package ru.progwards.java1.lessons.register1;

public class Summator {
    public static boolean add(ByteRegister value1, ByteRegister value2){
//        System.out.println("value1- " + value1);
//        System.out.println("value2- " + value2);
        boolean bol = true;
        int numDop =0;
        int numSum =0;
        for (int i = 0; i < 8; i++) {

            numSum = numDop + value1.value[value1.value.length-i-1] + value2.value[value2.value.length-i-1];
            value1.value[value1.value.length - i - 1] = (byte) numSum;
            if(numSum == 2) {
                value1.value[value1.value.length - i - 1] = 0;
                numDop = 1;
            } else  if (numSum == 3){
                value1.value[value1.value.length - i - 1] = 1;
                numDop = 1;
            } else {
                numDop = 0;
            }


        }
        if (numSum > 1) bol = false;
//        System.out.println(numSum);
//        for (int values : value1.value) {           System.out.print("," + values);       }
        return bol;
        }

    public static void main(String[] args) {

        ByteRegister val1 = new ByteRegister((byte)6);
        ByteRegister val2 = new ByteRegister((byte)7);

        System.out.println(add(val1,val2));
        System.out.println(val1.toDecString());

    }
}
