package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class FindDuplicates {
    final static String HOME_DIR = "C:\\Users\\admin\\IdeaProjects\\MyTest\\proect_zadanie";


    public List<List<String>> findDuplicates(String startPath)  {
        List<List<String>> lists = new ArrayList<>();
        TreeSet<Path> fileHashSet = new TreeSet<>();
        TreeSet<Path> fileTreeSetDes = new TreeSet<>();
        fileTreeSetDes = (TreeSet<Path>) fileHashSet.descendingSet();

        try {
            //получаем весь список файлов в 1 экземпляре

            /**/   PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**");
            Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {

                    if (pathMatcher.matches(path))
                        fileHashSet.add(path.getFileName());
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
            Iterator<Path> iterator = fileTreeSetDes.iterator();
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

        return lists;
    }
//проверка равенства атрибутов(размер и время изменения) и содержания файла
    private void attributeEqualityChecking(List<String> listFile) {

        try {

            for (int i = listFile.size()-1; i>=0; i--){
                int count = 0;
                Path pathI = Paths.get(listFile.get(i));//ссылка пути файла
                byte[] allBytesI = Files.readAllBytes(pathI);//содержимое файла в байтах
             /**/   for (int j=listFile.size()-1; j>=0; j--){

                    Path pathJ = Paths.get(listFile.get(j));
                    byte[] allBytesJ = Files.readAllBytes(pathJ);
                    /* далее сравниваем файлы между собой 1.это если файл не сам с собой(проверяю по индексу их)
                     * 2. по размеру 3. по дате изменения 4. по содержимому(в байтах) */
                    if (listFile.indexOf(listFile.get(i)) != listFile.indexOf(listFile.get(j)) &&
                            Files.getAttribute(pathI,"size").equals(Files.getAttribute(pathJ,"size"))
                                    &&
                            Files.getLastModifiedTime(pathI).compareTo(Files.getLastModifiedTime(pathJ)) == 0
                                    &&
                            Arrays.equals(allBytesI,allBytesJ)//сравним два массива
                    ){
                        count = 1;
                    }
                }
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
