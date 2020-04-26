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
        TreeSet<Path> fileTreeSet = new TreeSet<>();
        try {
            //получаем весь список файлов в 1 экземпляре

            /**/   PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**");
            Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {

                    if (pathMatcher.matches(path))
                        fileTreeSet.add(path.getFileName());
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
            Iterator<Path> iterator = fileTreeSet.iterator();
            Path file;
            while (iterator.hasNext()) {

                file =  iterator.next();
                List<String> listFile = new LinkedList<>();

                PathMatcher pathMatcher2 = FileSystems.getDefault().getPathMatcher("glob:**/*" + file);
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

                attributeEqualityChecking(listFile);//проверка равенства атрибутов
                //если нашли совпадающие файлы добавляем в список
                if (listFile.size()>1){
                    lists.add(listFile);
                }

            }
        } catch (Exception e){

        }



        for (List<String> section : lists) {
            System.out.println(section);
        }

        return lists;
    }
//проверка равенства атрибутов(размер и время изменения) и содержания файла
    private void attributeEqualityChecking(List<String> listFile) {
        try {

            for (int i=0; i<listFile.size(); i++){

                Path pathI = Paths.get(listFile.get(i).trim());//было исключение пришлось убрать пробелы из ссылки с помощью .trim()
                byte[] allBytesI = Files.readAllBytes(pathI);
                Files.lines(pathI, Charset.forName("windows-1251"));
                String fileAsStringI = Files.readString(pathI);
                //System.out.println(allBytesI.length);
                File fileHechI = new File(listFile.get(i));
                int count = 0;
                for (int j=i; j<listFile.size(); j++){
                    Path pathJ = Paths.get(listFile.get(j).trim());
                    byte[] allBytesJ = Files.readAllBytes(pathJ);
                    Files.lines(pathJ, Charset.forName("windows-1251"));
                    String fileAsStringJ = Files.readString(pathJ);
                    File fileHechJ = new File(listFile.get(j));
                    if (
                            Files.getAttribute(pathI,"size").equals(Files.getAttribute(pathJ,"size")) &&
                            Files.getAttribute(pathI,"lastModifiedTime").equals(Files.getAttribute(pathJ,"lastModifiedTime"))
                            && fileHechI.hashCode() == fileHechJ.hashCode()
//                                    allBytesI.length == allBytesJ.length &&
//                                            fileAsStringI.equals(fileAsStringJ)
                   ){
                        count = 1;
                    } else {
                        count = 0;
                    }
// &&
//                            listFile.get(i).length() == listFile.get(j).length()
                }
                if (count == 0){
                    listFile.remove(i);
                }

                count = 0;
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
