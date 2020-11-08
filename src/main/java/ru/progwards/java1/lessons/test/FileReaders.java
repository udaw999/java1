package ru.progwards.java1.lessons.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileReaders {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("text.txt"));//открываем файл
        List<String> lines = new ArrayList<>();
        String line;
        while ((line =  reader.readLine()) != null) {
            lines.add(line);//читаем построчно

        }
        reader.close();//закрываем

        String[] array = lines.toArray(new String[lines.size()]);//переводим строку в массив
        Arrays.sort(array);//сортируем
        for (String s : array) {
            System.out.println(s);
        }


    }


}
