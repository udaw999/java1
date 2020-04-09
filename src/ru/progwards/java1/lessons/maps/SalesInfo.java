package ru.progwards.java1.lessons.maps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SalesInfo {

    public static List<String> lines = new ArrayList<>();
    public static String line;
    public static int loadOrders(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));//открываем файл
        int coynter = 0;


        while ((line =  reader.readLine()) != null) {
            String[] arrayStr = line.trim().split(",");

            if (arrayStr.length == 4 && isNumber(arrayStr[3].trim()) && isNumber(arrayStr[3].trim())){
                    lines.add(line.trim());//читаем построчно
                    coynter++;
            }

        }
        reader.close();//закрываем
        //System.out.println(lines);
        return coynter;
    }
    private static boolean isNumber(String str) {//проверяет является ли строка числом
        for (char c: str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    public Map<String, Double> getGoods(){
        return null;
    }

    public Map<String, AbstractMap.SimpleEntry<Double, Integer>> getCustomers(){
        return null;
    }

    public static void main(String[] args) throws IOException {
       // System.out.println(loadOrders("file.csv"));
    }
}
