package ru.progwards.java1.lessons.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;

public class test11 {
    //11-1T
    public static void doExceptions(int n) throws Throwable {
        Throwable suppressed = null;
        try {
            System.out.println("doExceptions 1");
            if (n == 0)
                throw new Exception("Exception in try");
            System.out.println("doExceptions 2");
        } catch (Throwable t) {
            suppressed = t;
            throw t;
        } finally {
            try {
                throw new Exception("Exception in finally");
            } catch (Throwable t) {
                if (suppressed != null)
                    t.addSuppressed(suppressed);
                throw t;                }
        }
    }

    public void doSomething(int n) throws IOException {

    }
/*
Существует метод public void doSomething(int n) throws IOException, создайте
свой метод, с сигнатурой public void test(int n), который должен содержать блоки try-catch-finally
1) В блоке try вызовите метод doSomething(n)
2) В блоке catch выведите на консоль строку, полученную из исключения через  getMessage()
3) Пробросите исключение дальше
4) В блоке finally выведите на консоль фиксированный текст "finally executed"
*/
    public void test(int n) throws IOException {

        try {
            doSomething(n);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new IOException();
        } finally {
            System.out.println("finally executed");
        }
    }
    //T11-2
    /*Создайте метод с сигнатурой public void scanLines(), который реализует следующий алгоритм:
- вводить с клавиатуры строки, до тех пор, пока во входной строке не встретится "/stop"
- если во входной строке содержится "Привет" вывести на консоль "Здравствуйте!"
- если во входной строке содержится "как дела" вывести на консоль "Хорошо"
- если во входной строке содержится "/stop" - закончить выполнение метода
- во всех остальных случая вывести введенную строку на консоль

Примечание: для проверки, содержит ли строка, содержимое другой строки
используйте метод класса String boolean contains(String str)*/
    public static void scanLines(){
        try (Scanner scanner = new Scanner(System. in)){
            String printSnr;
            while (scanner.hasNextLine()) {
                printSnr = scanner.nextLine();
                if (printSnr.contains("/stop") == true){
                    break;
                } else if (printSnr.contains("Привет") == true) {
                    System.out.println("Здравствуйте!");
                } else if (printSnr.contains("как дела") == true) {
                    System.out.println("Хорошо");
                } else {
                    System.out.println(printSnr);
                }
            }

        }
    }
//T11-3
    /*Реализуйте метод с сигнатурой public String invertWords(String sentence),
    который переставляет слова, в полученной фразе. В качестве исходного
    разделителя использовать пробел. В качестве соединительного точку.

Например,
invertWords("Буря мглою небо кроет") должен вернуть
"кроет.небо.мглою.Буря"*/
    public static String invertWords(String sentence){
        String[] sentenceArr = sentence.split(" ");
        String sentenceRep = "";
        for (int i=0;i<sentenceArr.length;i++){
            sentenceRep = sentenceArr[i]+ "." + sentenceRep;
        }
        sentenceRep = sentenceRep.substring(0,sentenceRep.length()-1);
        return sentenceRep;
    }

  //T11-4
  /*Реализовать метод с сигнатурой public String setStars(String filename) который читает файл filename
  и меняет в нем каждый 10-йбайт на символ *, при этом конкатенируя оригинальный символ в строку ответа.
В случае ошибки выбросить исключение IOException со строкой сообщения:равной имени класса оригинального сообщения
Например,при содержимом файла:
0123456789012345678A012345678B01
новое содержимое должно быть
012345678*012345678*012345678*01
012345678*012345678*012345678*01
и метод должен вернуть "9AB"*/
  public static String setStars(String filename){
      String strZ ="";
      Byte but;
      int n = 0;
      try (RandomAccessFile files = new RandomAccessFile(filename, "rw")){


          for (int i=0; i<files.length()/10;i++){
              if(i==0)
                  n=9;
              files.seek(n);
              String str = files.readLine();
              strZ = strZ + str.substring(0,1);
              files.seek(n);

              files.writeBytes("*");
              n = n + 10;
          }


          files.close();

      } catch (FileNotFoundException e) {
          //e.printStackTrace();
      } catch (IOException e) {

          e.printStackTrace();
      }

      return strZ;
  }
    public static void main(String[] args) throws Exception {
      //setStars("file1.txt");
      System.out.println(setStars("file1.txt"));

        //scanLines();
//        String sentence = "Буря мглою небо кроет";
//        System.out.println(invertWords(sentence));





//        try {
//            doExceptions(0);
//        } catch (Throwable e) {
//            System.out.println(e.getMessage());
//            for (Throwable t: e.getSuppressed())
//                System.out.println(t.getMessage() + " (suppressed)");
//        }
    }
}
