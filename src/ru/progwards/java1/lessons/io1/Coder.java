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
/*                Scanner scanner = new Scanner(reader);//читаем сканером
                while (scanner.hasNextLine()) {//если есть строка то ее выводим
                    String strFromFile = scanner.nextLine();
                    System.out.println(strFromFile);
                    //перекодируем файл
                    if(strFromFile.length() != 0){//если строка не пустая то делаем перекодировку
                        for (int i=0; i<strFromFile.length();i++){//перебираем каждый символ в строке
                            strFromFile = strFromFile.replace(strFromFile.charAt(i), code[i]);
                            //берем символ по номеру цыкла и меняем на символ из масива кодера по номеру цыкла
                            out.println(strFromFile);
                        }
                        out.println(strFromFile);
                    }
*/

                int ch;
                int i = 0;
                int symbolCode;
                while ((ch = reader.read()) != -1) {//читаем файл посимвольно

                    System.out.print((char)ch);

                    symbolCode = code[i];//берем из масива кода соответствующий
                                        // символ числу цыклов(номеру буквы в файле)

                    i++;
                    //запись перекодированного в файл
                    FileWriter fileWriter = new FileWriter(outFileName,true);
                    try {
                        //fileWriter.write(strFromFile + "\n");
                        fileWriter.write(symbolCode);//записываем посимвольно цифру из кода

                    } finally {
                        fileWriter.close();
                    }

                }//конец цыкла
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
        char[] code ={5,8,95,36,45,5,45,8,55,2,66,95,9,5,5,55,8,22,55,52,88,85,5,5,5,55,8,95,36,45,5,45,
                8,55,2,66,95,9,5,5,55,8,22,55,52,88,85,5,5,5,55,5,8,95,36,45,5,45,8,55,2,66,95,
                9,5,5,55,8,22,55,52,88,85,5,5,5,55,8,95,36,45,5,45,};

        codeFile("file1.txt","outFileName.txt",code,"logName.txt");
    }
}
