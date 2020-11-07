package ru.progwards.java1.lessons.register2;



public class ByteRegister extends Register {
    byte[] value = new byte [8];
    public ByteRegister(){

    }
    public ByteRegister(byte value){

        for (int i = 0; i < 8; i++) {

            this.value[this.value.length - 1 - i] = (byte) (value & 1);
            value >>= 1;
        }

    }

    public String toString(){
        String valueStr = "";
        for (int i = 0; i < 8; i++) {

            valueStr += this.value[i];

        }
        return valueStr;
    }

    public String toDecString(){
        int valueDes = 0;
        for (int i = 0; i < 8; i++) {
           // System.out.println(valueDes + " = " +  this.value[i] + " умножить на 2 в степени " + (this.value.length - i - 1 ));
            valueDes += this.value[i] * Math.pow(2,this.value.length - i -1);

        }
        return String.valueOf(valueDes);
    }


}
