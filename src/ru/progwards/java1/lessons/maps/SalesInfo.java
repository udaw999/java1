package ru.progwards.java1.lessons.maps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SalesInfo {

    public static List<String> lines = new ArrayList<>();
    public static String line;
    public static int loadOrders(String fileName) {
        int coynter = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));//открываем файл
            try {
                while ((line =  reader.readLine()) != null) {
                    String[] arrayStr = line.trim().split(",");

                    if (arrayStr.length == 4 && isNumber(arrayStr[3].trim()) && isNumber(arrayStr[2].trim())){
                        lines.add(line.trim());//читаем построчно
                        coynter++;
                    }

                }
            } finally {
                reader.close();//закрываем
            }
        } catch (Exception e){

        }

        System.out.println(lines);
        return coynter;
    }
    private static boolean isNumber(String str) {//проверяет является ли строка числом
        for (char c: str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    public static Map<String, Double> getGoods(){
        Map<String, Double> map = new TreeMap<>();
        for (int i = 0; i<lines.size(); i++){
            String[] arrayStr = lines.get(i).trim().split(",");
                if (map.containsKey(arrayStr[1].trim())){
                    map.put(arrayStr[1].trim(),map.get(arrayStr[1].trim()) + Double.valueOf(arrayStr[3].trim()));
                }  else {
                    map.put(arrayStr[1].trim(),Double.valueOf(arrayStr[3].trim()));
                }


            }

        return map;
    }

    public Map<String, AbstractMap.SimpleEntry<Double, Integer>> getCustomers(){
        return null;
    }

    public static void main(String[] args) {
        System.out.println(loadOrders("file.csv"));
        System.out.println(getGoods());
    }
}
