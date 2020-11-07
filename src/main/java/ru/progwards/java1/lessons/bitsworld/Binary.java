package ru.progwards.java1.lessons.bitsworld;

public class Binary {
    byte num;

    public Binary(byte num){
        this.num = num;
    }
    public String toString(){
        String result ="";
        if (num == 0) {
            result = "00000000";
        } else {
//

            for (int i = 0; i < 8; i++) {
                result = CheckBit.checkBit(num, i) + result;
            }

        }
        return result;
    }

    public static void main(String[] args) {
        byte num = 112;
        Binary binary = new Binary(num);

        System.out.println(binary);

    }
}
