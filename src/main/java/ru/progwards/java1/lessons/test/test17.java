package ru.progwards.java1.lessons.test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class test17 {

    /*Создайте метод с сигнатурой String createFolder(String name), который создает каталог name
    (один уровень) в текущей папке и возвращает полный родителя текущего каталога*/
    public  static String createFolder(String name){
         new File(name).mkdir();
        Path path = Paths.get(name);
        //System.out.println(path.toAbsolutePath().getParent());
        String str = "" + path.toAbsolutePath().getParent().getParent();
        return str;
    }

    /*Реализовать метод с сигнатурой boolean replaceF(String name) который заменяет в файле все F на f,
     в случае ошибки вернуть false. Для реализации пользоваться методами java.nio.file.Files. */
    boolean replaceF(String name){
        try {
            Path path = Paths.get(name);
            String fileString = Files.readString(path);
            String fileStringNev = fileString.replace('F','f');
            Files.writeString(path,fileStringNev);
        } catch (Exception e){
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(createFolder("vit"));

    }
}
