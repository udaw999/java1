package comparator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class  CompareTwoFiles {
    private Path fileNane_1;
    private Path fileNane_2;
    private List<String> file_1;
    private List<String> file_2;
    private ArrayList<StringTwoFiles> arrayAllLines = new ArrayList<>();//тут все строки будут

    CompareTwoFiles(String fileNane_1, String fileNane_2){
        this.fileNane_1 = Paths.get(fileNane_1);
        this.fileNane_2 = Paths.get(fileNane_2);

        ReadFile readFile_1 = new ReadFile(fileNane_1);
        readFile_1.readFile();
        file_1 = readFile_1.getList();

        ReadFile readFile_2 = new ReadFile(fileNane_2);
        readFile_2.readFile();
        file_2 = readFile_2.getList();
    }


    //сравниваем строки двух файлов
    private boolean file_1End = false;
    private boolean file_2End = false;
    private int numBlok = 0;
    private int file = 0;
    private int pop =0; //смещение строк в файлах относительно друг друга
    private int i = 0;



    public List<String> getFile_1() {
        return file_1;
    }

    public List<String> getFile_2() {
        return file_2;
    }

    void compareTwoFiles(){

        boolean doesNotMatch_1 = false;//строка 1ф не совпадает
        boolean doesNotMatch_2 = false; //строка 2ф не совпадает
        //если прошли по всем строкам - конец
        while (!file_2End || !file_1End) {
            if (!file_2End && !file_1End) {//если еще есть  строкии в файлах
                //если стр 1 == стр 2 тогда печатаем их
                if(file_1.size() == 0 || file_2.size() ==0){
                    System.out.println("pust file");
                    break;
                }

                if (file_1.get(i).equals(file_2.get(i + pop))) {
                    file = 0;//это для вычисления номера блока
                    arrayAllLines.add(new StringTwoFiles(file_1.get(i),
                            true, true, 0));
                    file_2End();//проверяем не последняя ли строка файла 2
                    file_1End();//проверяем не последняя ли строка файла 1
                    //System.out.println("i- " + i);
                    System.out.print("1-file:  " + file_1.get(i) + "  |   ");
                    System.out.println("2-file:  " + file_2.get(i + pop));

                    //если стр 1 != стр 2  сравниваем в цыкле всех стр файла 2 - поочередно
                } else {
                    int countMismatchFile_1 = 0;
                    int countMismatchFile_2 = 0;

                    for (int j = i + pop; j < file_2.size(); j++) {
                        //если не нашли совпадений во всем файле отмечаем true и распечатаем строку за циклом ниже
                        if (!file_1.get(i).equals(file_2.get(j))) {
                            doesNotMatch_1 = true;
                        } else {//иначе выходим из цыкла
                            countMismatchFile_1 = j - i - pop;//несовпадение строк
                            doesNotMatch_1 = false;
                            break;
                        }
                    }
                    //если не нашли совпадений во всем файле отмеченное  true выше
                    //печатаем эту строку  и начинаем рекурсию этого метода со следующей строки
                    doesNotMatch_1(doesNotMatch_1);
                    //если стр 1файла есть в файле 2 то проверяем а есть ли строка второго файла в 1 файле
                    for (int z = i; z < file_1.size(); z++) {
                        //если не нашли совпадений во всем файле отмечаем true и распечатаем строку за циклом ниже
                        if (!file_2.get(i + pop).equals(file_1.get(z))) {
                            doesNotMatch_2 = true;
                        } else {//иначе выходим из цыкла
                            countMismatchFile_2 = z - i;//несовпадение строк
                            doesNotMatch_2 = false;
                            break;
                        }
                    }
                    //если не нашли совпадений во всем файле отмеченное  true выше
                    //печатаем эту строку  и начинаем рекурсию этого метода со следующей строки
                    doesNotMatch_2(doesNotMatch_2);
                    //если есть совпадения строки в противоположных файлах
                    if (!doesNotMatch_1 && !doesNotMatch_2) {
                        //смотрим какая из них глубже и которая ближе ту и берем
                        countMismatchFile(countMismatchFile_1, countMismatchFile_2);
                    }
                }
            } else { //если еще в одном из файлов прошли не по всем строка а в другом по всем
                file_1_End_2_No();
                file_2_End_1_No();
            }

            i++;//увеличиваем счетчик
        }
    }

    private void file_1End() {
        if (i == file_1.size() - 1)//проверяем не последняя ли строка файла 1
            file_1End = true;//если последняя
    }

    private void file_2End() {
        if (i + pop == file_2.size() - 1)//проверяем не последняя ли строка файла 2
            file_2End = true;//если последняя
    }
    private void numBlok(int i) {
        if (file != i)//это для вычисления номера блока
            numBlok++;//это для вычисления номера блока
        file = i;//это для вычисления номера блока
    }
    //если не нашли совпадений во всем первом файле отмеченное  true выше
    private void doesNotMatch_2(boolean doesNotMatch_2) {
        if (doesNotMatch_2) {
            //System.out.println("i- " + i);
            System.out.print("1-file:  -------------------   |   ");
            System.out.println("2-file:  " + file_2.get(i + pop) );
            numBlok(2);//номера блока
            file_2End();//проверяем не последняя ли строка файла 2
            arrayAllLines.add(new StringTwoFiles(file_2.get(i + pop),
                    false,true,numBlok));

            pop++;//разница в совпадении строк
            compareTwoFiles();//повторяем еще раз
            //break;
        }
    }

    //если не нашли совпадений во всем втором файле отмеченное  true выше
    private void doesNotMatch_1(boolean doesNotMatch_1) {
        if (doesNotMatch_1) {
            //System.out.println("i- " + i);
            System.out.print("1-file:  " + file_1.get(i) + "  |   ");
            System.out.println("2-file:  -------------------");
            numBlok(1);//номера блока
            file_1End();//проверяем не последняя ли строка файла 1
            arrayAllLines.add(new StringTwoFiles(file_1.get(i),
                    true,false, numBlok));
            i++;
            pop--;//разница в совпадении строк
            compareTwoFiles();//повторяем еще раз
            //break;
        }
    }


    //смотрим какая строка глубже и которая ближе ту и берем
    private void countMismatchFile(int countMismatchFile_1, int countMismatchFile_2) {
        if (countMismatchFile_1>=countMismatchFile_2){
//                            System.out.println("tyt");
            // System.out.println("i- " + i);
            System.out.print("1-file:  " + file_1.get(i) + "  |   ");
            System.out.println("2-file:  -------------------");
            numBlok(1);//номера блока
            file_1End();//проверяем не последняя ли строка файла 1
            arrayAllLines.add(new StringTwoFiles(file_1.get(i),
                    true,false, numBlok));
            pop --;
        } else {
            //System.out.println("i- " + i);
            System.out.print("1-file:  -------------------   |   ");
            System.out.println("2-file:  " + file_2.get(i + pop) );
            numBlok(2);//номера блока
            file_2End();//проверяем не последняя ли строка файла 2
            arrayAllLines.add(new StringTwoFiles(file_2.get(i + pop),
                    false,true,numBlok));
            i--;
            pop++;
        }
    }
    //если в файле 2 последняя строка обработана а в файле 1 еще есть строки
    private void file_2_End_1_No() {
        if (file_2End && !file_1End){
            // System.out.println("i- " + i);
            System.out.print("1-file:  " + file_1.get(i) + "  |   ");
            System.out.println("2-file:  -------------------");
            numBlok(1);//номера блока
            file_1End();//проверяем не последняя ли строка файла 1
            arrayAllLines.add(new StringTwoFiles(file_1.get(i),
                    true,false, numBlok));
        }
    }
    //если в файле 1 последняя строка обработана а в файле 2 еще есть строки
    private void file_1_End_2_No() {
        if (file_1End && !file_2End){
            //System.out.println("i- " + i);
            System.out.print("1-file:  -------------------   |   ");
            System.out.println("2-file:  " + file_2.get(i + pop) );
            numBlok(2);//номера блока
            file_2End();//проверяем не последняя ли строка файла 2
            arrayAllLines.add(new StringTwoFiles(file_2.get(i + pop),
                    false,true,numBlok));
        }
    }




    //строки обоих файлов с параметрами для дальнейшей обработки
    ArrayList<StringTwoFiles> getArrayAllLines() {

        return arrayAllLines;
    }
    void  clearArrayAllLines() {

        arrayAllLines.clear();
    }
    //реализуем далее добавление нужного блока в нужный файл
    //добавить блок в первый(левый) файл
    public void addBlockToFileOne(int numBlok){
        boolean blockPresent = false;
        for (StringTwoFiles arrayAllLine : arrayAllLines) {
            if (arrayAllLine.getNumBlok() == numBlok && arrayAllLine.isBelongsFile_1()) {
                System.out.println("Этот блок уже присутствует в файле, выберите другой");
                blockPresent = true;
                break;
            }
        }
        if (!blockPresent){
            file_1.clear();
            for (StringTwoFiles arrayAllLine : arrayAllLines) {
                if (arrayAllLine.isBelongsFile_1() || arrayAllLine.getNumBlok() == numBlok) {
                    file_1.add(arrayAllLine.getStr());
                }
            }
            try {
                Files.write(fileNane_1,file_1);
            } catch (Exception e){
                System.out.println(e);
            }
        }
    }

    //добавить блок во второй(правый) файл
    public void addBlockToFileTwo(int numBlok){
        boolean blockPresent = false;
        for (StringTwoFiles arrayAllLine : arrayAllLines) {
            if (arrayAllLine.getNumBlok() == numBlok && arrayAllLine.isBelongsFile_2()) {
                System.out.println("Этот блок уже присутствует в файле, выберите другой");
                blockPresent = true;
                break;
            }
        }
        if (!blockPresent){
            file_2.clear();
            for (StringTwoFiles arrayAllLine : arrayAllLines) {
                if (arrayAllLine.isBelongsFile_2() || arrayAllLine.getNumBlok() == numBlok) {
                    file_2.add(arrayAllLine.getStr());
                }
            }
            try {
                Files.write(fileNane_2,file_2);
            } catch (Exception e){
                System.out.println(e);
            }
        }
    }

    private void writeToFile() {

    }



}
