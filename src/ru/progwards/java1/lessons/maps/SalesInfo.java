package ru.progwards.java1.lessons.maps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SalesInfo {

    public  List<String> lines = new ArrayList<>();
    public  String line;
    public  int loadOrders(String fileName) {
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
    private  boolean isNumber(String str) {//проверяет является ли строка числом
        for (char c: str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    public  Map<String, Double> getGoods(){
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

    public  Map<String, AbstractMap.SimpleEntry<Double, Integer>> getCustomers(){
        Map<String, AbstractMap.SimpleEntry<Double, Integer>> map = new TreeMap<>();
        for (int i = 0; i<lines.size(); i++){
            String[] arrayStr = lines.get(i).trim().split(",");
            double gKey = 0;
            int gValue = 0;
            if (map.containsKey(arrayStr[0].trim())){

                gKey = map.get(arrayStr[0].trim()).getKey() + Double.valueOf(arrayStr[3].trim());
                gValue = map.get(arrayStr[0].trim()).getValue() + Integer.parseInt(arrayStr[2].trim());

                AbstractMap.SimpleEntry<Double, Integer> abstracSimpl
                        = new AbstractMap.SimpleEntry<Double, Integer>(gKey,gValue);

                map.put(arrayStr[0].trim(), abstracSimpl);

            }  else {
                gKey = Double.valueOf(arrayStr[3].trim());
                gValue = Integer.parseInt(arrayStr[2].trim());

                AbstractMap.SimpleEntry<Double, Integer> abstracSimpl
                        = new AbstractMap.SimpleEntry<Double, Integer>(gKey,gValue);

                map.put(arrayStr[0].trim(), abstracSimpl);
            }



        }

        return map;
    }

    public static void main(String[] args) {
//        System.out.println(loadOrders("file.csv"));
//        System.out.println(getGoods());
//        System.out.println();
//        System.out.println(getCustomers());
    }
}
