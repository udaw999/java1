package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.*;

/*прочитать файл inFileName и перекодировать его посимвольно в соответствии с заданным шифром,
 результат записать в outFileName. Шифр задается маcсивом char[] code, где каждому символу symbol
 оригинального файла соответствует символ code[(int)symbol] выходного файла. В случае ошибок, в файл
 с именем logName вывести название ошибки через метод класса Exception - getMessage()*/
public class Coder {
    public static void codeFile(String inFileName, String outFileName, char[] code, String logName) {

        try {

            //читаем файл
            FileReader reader = new FileReader(inFileName);
            try {


                int ch;

                int symbolCode; //символ для записи в файл
                while ((ch = reader.read()) != -1) {//читаем файл посимвольно

                    symbolCode = code[(int) (char)ch];//берем из масива кода соответствующий code[(int)symbol]

                    //запись перекодированного в файл
                    FileWriter fileWriter = new FileWriter(outFileName,true);
                    try {

                        fileWriter.write(symbolCode);//записываем посимвольно перекодированый символ

                    } finally {
                        fileWriter.close();//закрываем файл
                    }

                }//конец цыкла перекодировки и записи в файл
            } finally {
                reader.close();//ЗАКРЫВАЕМ файл inFileName
            }

        }
        catch (Exception e){ //если ошибка то записываем ошибку в файл
            String strError = e.getMessage();//текст ошибки

            FileWriter logFile = null;//записать лог в файл
            try {
                logFile = new FileWriter(logName, true);
                logFile.write(strError + "\n"); //сам лог строка
            } catch (IOException e1) {
                e1.printStackTrace();
            }

             finally {
                try {
                    logFile.close();//закрыть файл после записи
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                return;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        char[] code ={95,36,45,5,45,8,55,2,66,95,9,5,5,55,8,22,55,52,88,85,5,5,5,55,8,95,36,45,5,45,
                8,55,2,66,95,9,5,5,55,8,22,55,52,88,85,5,5,5,55,5,8,95,36,45,5,45,8,55,2,66,95,
                9,5,5,55,8,22,55,52,88,85,5,5,5,55,8,95,36,45,5,45,};

        codeFile("file1.txt","outFileName.txt",code,"logName.txt");
    }
}
