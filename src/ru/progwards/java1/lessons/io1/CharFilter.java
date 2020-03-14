package ru.progwards.java1.lessons.io1;

import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class CharFilter {
    public static void filterFile(String inFileName, String outFileName, String filter){
        try {
            //прочитать файл inFileName
            //читаем файл
            FileReader reader = new FileReader(inFileName);
            try {
                int ch;// символ из файла

                int symbolCode; //символ для записи в файл
                while ((ch = reader.read()) != -1) {//читаем файл посимвольно


                    symbolCode = (char)ch; //символ для записи
                    for (int i=0; i<filter.length();i++){//проверяем на совпадение с символами фильтра
                        char c = filter.charAt(i);//извлекаем символов по индексу из строки filter
                       // System.out.println((char)ch);
                        if ((char)ch == c){

                            symbolCode = 0;//если совпали то символ 0
                        }
                    }

                    //запись в файл
                    FileWriter fileWriter = new FileWriter(outFileName,true);
                    try {

                        fileWriter.write(symbolCode);//записываем посимвольно

                    } finally {
                        fileWriter.close();//закрываем файл
                    }

                }//конец цыкла перекодировки и записи в файл

                // удалить символы, содержащиеся в String filter
                // результат записать в выходной файл
            } finally {
                reader.close();//ЗАКРЫВАЕМ файл inFileName
            }


        } catch (Exception e){
            try {
                throw new IOException();//В случае возникновения ошибки, пробросить стандартное исключение выше
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        String obscene = " -,.()";

        filterFile("file1.txt","outFileName.txt",obscene);
    }
}
