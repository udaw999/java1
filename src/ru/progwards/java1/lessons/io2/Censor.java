package ru.progwards.java1.lessons.io2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Censor {

    public static void censorFile(String inoutFileName, String[] obscene){

        try (RandomAccessFile files = new RandomAccessFile(inoutFileName, "rw")){
                String str = files.readLine(); //читаем строку файла
            for (int i=0; i<obscene.length; i++){
                String replace = obscene[i]; //слово которое надо заменить в строке
                String nevReplace ="";
                for (int j = 0; j < replace.length() ; j++){//создаем строку из * длиной требуемого на замену слова
                    nevReplace = nevReplace + "*";
                }
                str = str.replace(replace,nevReplace);//делаем замену в строке которую достали из файла



            }
            files.seek(0);//поставим курсор в начало файла

            files.writeBytes(str);//записываем строку вместо той что была в файле

            files.close();//закрываем файл
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String[] obscene = {"Java", "Oracle", "Sun", "Microsystems"};
        censorFile("file1.txt",obscene);

    }

}
