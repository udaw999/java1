package ru.progwards.java1.lessons.register2;

public class IntRegister extends Register {
    byte[] value = new byte [32];

    public IntRegister(){

    }
    public IntRegister(int value){
        for (int i = 0; i < 32; i++) {

            this.value[this.value.length - 1 - i] = (byte) (value & 1);
            value >>= 1;
        }
    }

    public String toString(){
        String valueStr = "";
        for (int i = 0; i < 32; i++) {

            valueStr += this.value[i];

        }
        return valueStr;
    }

    public String toDecString(){
        int valueDes = 0;
        if(value[0] == 0){
            for (int i = 0; i < 32; i++) {
                // System.out.println(valueDes + " = " +  this.value[i] + " умножить на 2 в степени " + (this.value.length - i - 1 ));
                valueDes += this.value[i] * Math.pow(2,this.value.length - i -1);

            }
        } else {
            for (int i = 0; i < 32; i++) {
                // System.out.println(valueDes + " = " +  this.value[i] + " умножить на 2 в степени " + (this.value.length - i - 1 ));
                valueDes += (this.value[i] ^ 1) * Math.pow(2, this.value.length - i - 1);
            }
            valueDes += 1;
            valueDes *= -1;
        }

        return String.valueOf(valueDes);
    }

    public static void main(String[] args) {
        int value = -451502843;
        IntRegister intRegister = new IntRegister(value);

        System.out.println(intRegister);
        System.out.println(intRegister.toString());
        System.out.println(intRegister.toDecString());//значением параметра = -451502843 ожидался: -451502843

        for (byte values : intRegister.value) {           System.out.print(", " + values);       }
    }
}
/*
00011010111010010110001011111011  451502843
11100101000101101001110100000101  -451502843 */