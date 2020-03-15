package ru.progwards.java1.lessons.io1;

import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class CharFilter {
    public static void filterFile(String inFileName, String outFileName, String filter){
        try {
            //прочитать файл inFileName
            //читаем файл
            FileReader reader = new FileReader(inFileName);
            Scanner scanner = new Scanner(reader);
            try {
                int ch;// символ из файла
                String filtrSim = "—";//неучтенный символ в фильтре
                char filtrSimChar = filtrSim.charAt(0);//неучтенный символ в фильтре(char)
                int symbolCode; //символ для записи в файл
                while (scanner.hasNextLine()) {//читаем файл построчно
                    String strInFileName = scanner.nextLine();//строка



                    //делаем замену символов в строке, содержащиеся в String filter
                    for (int i=0; i<filter.length();i++){//проверяем на совпадение с символами фильтра
                        char c = filter.charAt(i);//извлекаем символов по индексу из строки filter

                      strInFileName = strInFileName.replace(""+(char) c,"");//замена символа в строке
                    }

                    // результат записать в выходной файл
                    //запись в файл
                    FileWriter fileWriter = new FileWriter(outFileName,true);
                    try {

                        fileWriter.write(strInFileName);//записываем посимвольно

                    } finally {
                        fileWriter.close();//закрываем файл
                    }

                }//конец цыкла перекодировки и записи в файл



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
