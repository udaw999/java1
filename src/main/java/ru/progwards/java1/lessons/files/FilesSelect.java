package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FilesSelect {

    public void selectFiles(String inFolder, String outFolder, List<String> keys){

        //List<Path> filesPath = new ArrayList<>();

        try {
            //получаем весь список файлов txt с их местом нахождения

            /**/   PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**.txt");
            Files.walkFileTree(Paths.get(inFolder), new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {

                    if (pathMatcher.matches(path))
                       // filesPath.add(path);

                    workWithFile(outFolder,keys,path);//работа с файлом

                    return FileVisitResult.CONTINUE;
                }
                @Override
                public FileVisitResult visitFileFailed(Path file, IOException e) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (Exception e){

        }

//        for (Path section : filesPath) {
//            System.out.println(section);
//        }

    }
    //работа с файлом
    private void workWithFile(String outFolder, List<String> keys, Path path){
        try {
            String file = Files.readString(path);
            //System.out.println("patc- "+path);
            boolean isContain1;
            for (int i=0; i<keys.size(); i++){

                isContain1 = file.contains(keys.get(i));
                //System.out.println(keys.get(i));
                if (isContain1){//если слово в файле найдено то реализуем задание задачи
//                    System.out.println(path);
                    System.out.println("слово- " + keys.get(i));
//                    System.out.println(String.valueOf(path.getFileName()).replace(".txt",""));
//                    System.out.println(path.getParent());
                    //получаем название папки по слову которое искали в файлах
                    String nameFolder = keys.get(i);
                    Path paths = Paths.get(outFolder);//путь к папке где надо создать каталог
                    Path newDir = paths.resolve(nameFolder);// путь с новым каталогом
                    if (!Files.exists(newDir))//проверяем существует ли каталог
                        Files.createDirectory(newDir);//если не существует создаем каталог

                    Path srcFile = path.getParent().resolve(path.getFileName());//путь к копируемому файлу
                    Path dstFile = newDir.resolve(path.getFileName());//ппуть где будет копия
                    Files.copy(srcFile, dstFile, StandardCopyOption.REPLACE_EXISTING); // копируем файл

                }
            }


        } catch (Exception e){
            System.out.println(e);
        }

    }

    public static void main(String[] args) {
        FilesSelect filesSelect = new FilesSelect();
        String inFolder = "C:\\Users\\admin\\IdeaProjects\\MyTest\\proect_zadanie\\inFolder";
        String outFolder = "C:\\Users\\admin\\IdeaProjects\\MyTest\\proect_zadanie\\outFolder";
        List<String> keys =  Arrays.asList("check", "files", "okno");

        filesSelect.selectFiles(inFolder,outFolder,keys);
    }
}
