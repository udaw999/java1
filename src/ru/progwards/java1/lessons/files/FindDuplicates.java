package ru.progwards.java1.lessons.files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class FindDuplicates {
    final static String HOME_DIR = "C:\\Users\\admin\\IdeaProjects\\MyTest\\proect_zadanie";


    public List<List<String>> findDuplicates(String startPath)  {
        List<List<String>> lists = new ArrayList<>();
        Set<Path> fileHashSet = new HashSet<>();
//        TreeSet<Path> fileTreeSetDes = new TreeSet<>();
//        fileTreeSetDes = (TreeSet)fileTreeSet.descendingSet();
        try {
            //получаем весь список файлов в 1 экземпляре

            /**/   PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**");
            Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {

                    if (pathMatcher.matches(path))
                        fileHashSet.add(path.getFileName());
                    //System. out.println(path.getFileName());
                    // System. out.println(path);
                    return FileVisitResult.CONTINUE;
                }
                @Override
                public FileVisitResult visitFileFailed(Path file, IOException e) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (Exception e){

        }

        try {
            //проходимся по списку и ищем совпадения
            Iterator<Path> iterator = fileHashSet.iterator();
            while (iterator.hasNext()) {

                Path file =  iterator.next();
                List<String> listFile = new LinkedList<>();

                PathMatcher pathMatcher2 = FileSystems.getDefault().getPathMatcher("glob:**" + file);
                Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<>() {
                    @Override
                    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {

                        if (pathMatcher2.matches(path))
                            listFile.add("" + path);
                        return FileVisitResult.CONTINUE;
                    }
                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException e) {
                        return FileVisitResult.CONTINUE;
                    }
                });


                if (listFile.size()>1){
                    attributeEqualityChecking(listFile);//проверка равенства атрибутов
                    //если нашли совпадающие файлы добавляем в список
                    if (listFile.size()>1) {
                        lists.add(listFile);
                    }
                }

            }
        } catch (Exception e){

        }



        for (List<String> section : lists) {
            System.out.println(section);
        }
//        Collections.sort(lists,Collections.reverseOrder());
        return lists;
    }
//проверка равенства атрибутов(размер и время изменения) и содержания файла
    private void attributeEqualityChecking(List<String> listFile) {

//        for (String sections : listFile) {
//            System.out.println(sections);
//        }
//        System.out.println("все");
        try {

            for (int i = listFile.size()-1; i>0; i--){
                int count = 0;
                Path pathI = Paths.get(listFile.get(i).trim());//было исключение пришлось убрать пробелы из ссылки с помощью .trim()
                byte[] allBytesI = Files.readAllBytes(pathI);
//                System.out.println("pathI 1 цыкл - "+pathI);
//                System.out.println();
             /**/   for (int j=i-1; j>=0; j--){

                    Path pathJ = Paths.get(listFile.get(j).trim());
                    byte[] allBytesJ = Files.readAllBytes(pathJ);
                    //System.out.println("2z- " + pathJ);


                    //сравним два массива


                    if (
                            Files.getAttribute(pathI,"size").equals(Files.getAttribute(pathJ,"size"))
                                    &&
                            Files.getLastModifiedTime(pathI).equals(Files.getLastModifiedTime(pathJ))
                                    &&
                            Arrays.equals(allBytesI,allBytesJ)
                    ){
                        count = 1;
                    }
                }
                //System.out.println("end count - " + count);
                if (count == 0){
                    listFile.remove(i);
                }
            }

        } catch (Exception e){
            System.out.println(e);
        }
    }


    public static void main(String[] args) throws IOException {
        FindDuplicates findDuplicates = new FindDuplicates();
        String startPath = "C:\\Users\\admin\\IdeaProjects\\MyTest\\proect_zadanie";
        findDuplicates.findDuplicates(startPath);



  /*      Path dir1 = Paths.get(HOME_DIR);
        try {
            Path srcFile = dir1.resolve("vas.txt");
         //   Path dstFile = dir1.resolve("skala/vas.txt");
           Path dst2File = dir1.resolve("lenta/vas.txt");
            Files.copy(srcFile, dst2File);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
