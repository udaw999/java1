package comparator;

public class CompareTestPrint {

    public static void main(String[] args) {
        String fileName_1 = "C:\\Users\\admin\\IdeaProjects\\fileCompare\\file_com_1.txt";
        String fileName_2 = "C:\\Users\\admin\\IdeaProjects\\fileCompare\\file_com_2.txt";
        String fi = "C:\\Users\\admin\\Documents\\text1_cop.txt";
        String f2 =  "C:\\Users\\admin\\Documents\\text1.txt";
        String str1 = "D:\\content\\ссылки2.txt";
        String str2 = "D:\\content\\ссылки.txt";
//        ReadFile readFiles = new ReadFile(fi);
//        readFiles.readFile();
//        System.out.println(readFiles.getList());
        CompareTwoFiles compareTwoFiles = new CompareTwoFiles(str1,str2);
//        System.out.println(compareTwoFiles.file_1);
//        System.out.println(compareTwoFiles.file_2);
//        System.out.println(compareTwoFiles.getMoreSiseString());
        compareTwoFiles.compareTwoFiles();//сравнить файлы(без визуализации)
        System.out.println();
        for (StringTwoFiles line : compareTwoFiles.getArrayAllLines())
            System.out.println(line);
//        System.out.println();
        //compareTwoFiles.addBlockToFileOne(1);//добавить блок в первый - левый файл
        //compareTwoFiles.addBlockToFileTwo(1);//добавить блок во второй правый файл

//        comparator.ReadFile readFile_1 = new comparator.ReadFile(fileName_1);
//        readFile_1.readFile();
//
//        comparator.ReadFile readFile_2 = new comparator.ReadFile(fileName_2);
//        readFile_2.readFile();

        //System.out.println(readFile_1.getList());
//        for (String list : readFile_1.getList()){
//            System.out.println(list);
//        }
   /*     int count = 0;
        for (int i = 0; i < readFile_1.getList().size()-1; i++){

            boolean no_str = false;
            String str_1 = readFile_1.getList().get(i);
            for (int j = i-count; j < readFile_2.getList().size(); j++){
                String str_2 = readFile_2.getList().get(j);

                if (str_1.equals(str_2)){
                    System.out.print("1-file:  " + str_1 + "  |   ");
                    System.out.println("2-file:  " + str_2);
                    no_str = false;
                    break;

                } else {


                    no_str = true;

                }

            }
            System.out.println("count " + count + " i " + i + "  no_str - " + no_str);
            if (no_str){
                count++;
                System.out.print("1-file:  " + str_1 + "  |   ");
                System.out.println("-----------------");

            }

        }*/

    }
}

