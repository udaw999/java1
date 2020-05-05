package ru.progwards.java1.lessons.egts;

public class EgtsDirectionAndSpeed {

    public static int getSpeed(short speedAndDir){
        int speedAndDirI = speedAndDir & 0b01111111_11111111;

        return speedAndDirI;
    }
    public static int getDirection(byte dirLow, short speedAndDir){
        int dirLowI;
        int st = (speedAndDir >> 15) == 0 ? 0:1;
        if (st==0){
            dirLowI = dirLow & 0b00000000_11111111;
        } else {
            dirLowI = dirLow & 0b00000001_11111111;
        }

        System.out.println(st);
        return dirLowI;
    }
    public static void main(String[] args) {
        //в 15 младших битах speedAndDir (short) хранится скорость в км/час
        /* В старшем бите speedAndDir хранится старший бит направления движения ТС.
        Таким образом получается, что для хранения направления движения ТС используется 9 бит,
        а для хранения скорости - 15 бит.*/
        short speedAndDir = (short) 0b00000001_01101000;
        byte dirLow = (byte) 0b1010_0011;// В байте dirLow хранятся младшие 8 бит направления движения ТС в градусах 163
        //System.out.println(getSpeed(speedAndDir));
        System.out.println(getDirection(dirLow,speedAndDir));

    }
}
