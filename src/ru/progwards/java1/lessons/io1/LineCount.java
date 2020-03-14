package ru.progwards.java1.lessons.io1;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
//читаем файл построчнои определяем пустые строки
public class LineCount {
    public static int calcEmpty(String fileName){
        try {
            FileReader reader = new FileReader(fileName);
            Scanner scanner = new Scanner(reader);
            int i = 0;
            try {

            while (scanner.hasNextLine()){
                String strFail = scanner.nextLine();
               if(strFail.length() == 0){//если длина строки равна 0(пустая)
                   i++;//считаем пусты строки
                }

            }
            }
                finally {
                    reader.close();
            }


            return i;
        } catch (IOException e){

            return -1;//если ошибка выводим -1
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(calcEmpty("file1.txt"));
//        FileReader reader = new FileReader("file1.txt");
//        Scanner scanner = new Scanner(reader);
//        while (scanner.hasNextLine()) {
//            String strFromFile = scanner.nextLine();
//            System.out.println(strFromFile);
//        }
//        reader.close();
    }
}
