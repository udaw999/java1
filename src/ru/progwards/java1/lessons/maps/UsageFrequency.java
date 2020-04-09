package ru.progwards.java1.lessons.maps;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsageFrequency {
    public static List<String> lines = new ArrayList<>();
    public static String line;
    public static void processFile(String fileName){

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));//открываем файл
            try {
                while ((line =  reader.readLine()) != null) {
                    lines.add(line);//читаем построчно

                }
            } finally {
                reader.close();//закрываем
            }

        } catch (Exception e){
        }




    }
    public static Map<Character, Integer> getLetters(){
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i<lines.size(); i++){
            //System.out.println(lines.get(i));
            char[] arrayChar = lines.get(i).toCharArray();//получаем масив из символов
            for (char c : arrayChar){
                if (Character.isDigit(c) || Character.isLetter(c)){
                    if (map.containsKey(c)){
                        map.put(c,map.get(c)+1);
                    } else {
                        map.put(c,1);
                    }
                }

            }
        }
        return map;
    }

    public static Map<String, Integer> getWords(){
        Map<String, Integer> map2 = new HashMap<>();

        for (int i = 0; i<lines.size(); i++){
            String[] arrayStr = lines.get(i).trim().split("[^0-9a-zA-Z_]");

            for (String c : arrayStr){
                    if(c.trim().length() != 0){
                        if (map2.containsKey(c)){
                            map2.put(c,map2.get(c)+1);
                        } else {
                            map2.put(c,1);
                        }
                    }



            }
        }
        return map2;
    }

    public static void main(String[] args) {
        UsageFrequency.processFile("wiki.test.tokens");
        //UsageFrequency.processFile("file1.txt");
        System.out.println(getLetters());
        System.out.println(getWords());

    }
}
