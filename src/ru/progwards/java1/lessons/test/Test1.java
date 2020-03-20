package ru.progwards.java1.lessons.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

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
/*Реализовать метод с сигнатурой private int lineCount(String filename) который возвращает
количество строк в файле filename. В случае ошибки пробросить исключение
IOException со строкой сообщения "файл не найден" */
    private int lineCount(String filename) throws IOException{
        try {


            FileReader reader = new FileReader(filename);
            Scanner scanner = new Scanner(reader);

            int i = 0;

                while (scanner.hasNextLine()){
                    String strFail = scanner.nextLine();
                    i++;
                }
                reader.close();
                return i;
        } catch (IOException e){

            throw new IOException("файл не найден");
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
        String str = null;
        try {
            System.out.println(str.toString());
        }
        catch (Exception e) {
            System.out.println("catch");
        }
    }
}
