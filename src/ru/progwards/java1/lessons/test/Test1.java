package ru.progwards.java1.lessons.test;

import java.io.IOException;
//урок 10
public class Test1 {
    Integer n = 0;
//тест 1
    public static Integer sqr(Integer n){
        try {
            return n*n;

        } catch (NullPointerException e){
            return -1;
        }
    }
//тест 2
    public static String test(String filename) throws IOException {
        if (filename == null) {
            throw new IOException("File not found");
        } else {
                return "File processing";
            }


    }

    public static void main(String[] args) {

       // System.out.println(test(null));


            System.out.println(sqr(null));

//        try {
//            System. out.println("try");
//            int e = 1 / 0;
//            ((Object) null).toString();
//            System. out.println((new int[5])[5]);
//        } catch(ArithmeticException e) {
//            System. out.println("Произошло деление на 0");
//        } catch(NullPointerException e) {
//            System. out.println("Обращение к объекту по ссылке null");
//        } catch (Exception e) {
//            System. out.println("Какое-то другое исключение");
//        }


//        try {
//            FileWriter writer = new FileWriter("aaaa", true);
//        }
//        catch (NullPointerException e) {
//
//        }
    }
}
