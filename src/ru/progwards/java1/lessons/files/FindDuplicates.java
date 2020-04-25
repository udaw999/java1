package ru.progwards.java1.lessons.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class FindDuplicates {
    final static String HOME_DIR = "C:\\Users\\admin\\IdeaProjects\\MyTest\\proect_zadanie";


    public List<List<String>> findDuplicates(String startPath)  {
        List<List<String>> lists = new ArrayList<>();
        HashSet<Path> fileHashSet = new HashSet<>();
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
            Path file;
            while (iterator.hasNext()) {

                file =  iterator.next();
                List<String> listFile = new LinkedList<>();

                PathMatcher pathMatcher2 = FileSystems.getDefault().getPathMatcher("glob:**/*" + file);
                Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<>() {
                    @Override
                    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {

                        if (pathMatcher2.matches(path))
                            // fileHashSet.add(path.getFileName());
                            //System. out.println(path.getFileName());
                            listFile.add(path.getFileName() + " " + path);
                        //System. out.println(path);
                        return FileVisitResult.CONTINUE;
                    }
                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException e) {
                        return FileVisitResult.CONTINUE;
                    }
                });
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
