package ru.progwards.java1.lessons.abstractnum;

public class Test {
    public static void main(String[] args) {
        Figure3D ftg = new Cube(new IntNumber(3));
        System.out.println(ftg.volume());

        Figure3D figDob = new Cube(new DoubleNumber(3));
        System.out.println(figDob.volume());



        Figure3D ftgPir = new Pyramid(new IntNumber(3));
        System.out.println(ftgPir.volume());

        Figure3D ftgPirDob = new Pyramid(new DoubleNumber(3));
        System.out.println(ftgPirDob.volume());

    }
}
