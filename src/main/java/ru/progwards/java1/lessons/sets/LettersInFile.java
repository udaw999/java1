package ru.progwards.java1.lessons.sets;

import java.io.*;
import java.nio.channels.ReadableByteChannel;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.TreeSet;

public class LettersInFile {

    public static String process(String fileName) throws Exception {
        String rezult = "";
        try {
            FileReader file = new FileReader(fileName);
             Scanner scanner = new Scanner(file);
            try {

                String strBukva ="";
                TreeSet<String> treeSet = new TreeSet<>();//множество куда буду ложить букву
                while (scanner.hasNextLine()) {//читаем файл построчно
                    String str = scanner.nextLine(); //читаем строку файла
                    //System.out.println(str);


                    for (char c : str.toCharArray()) { //разбираем строку на символы
                        if (Character.isAlphabetic(c)) {//берем только буквы
                            strBukva = Character.toString(c);
                            treeSet.add(strBukva);//добавляем букву в множество treeSet
                        }

                    }

                }
               // System.out.println(treeSet);
                //далее из множества выводим содержимое в строку

                for (Iterator<String> ierator = treeSet.iterator(); ierator.hasNext(); ) {
                    rezult += ierator.next();//собираем строку из отсортированных букв
                    //System.out.println(ierator.next());
                }
            } catch (Exception ei) {
                throw new IOException();
            } finally {
                file.close();
            }



        } catch (Exception e){

            throw new Exception();
        }


        return rezult;
    }

    public static void main(String[] args) throws Exception {

        System.out.println(process("file1.txt"));
    }
}
