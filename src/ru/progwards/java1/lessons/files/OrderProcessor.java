package ru.progwards.java1.lessons.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class OrderProcessor {
    private String startPath;
    private int countError = 0;
    private List<Order> process;
    private Map<String, Double> statisticsByShop;
    private Order order;

//- инициализирует класс, с указанием начальной папки для хранения файлов
    public OrderProcessor(String startPath){
        this.startPath = startPath;
    }

    public int loadOrders(LocalDate start, LocalDate finish, String shopId){
        String shopIdGlob;

        try {
            if (shopId == null){
                shopIdGlob = "???";
            } else {
                shopIdGlob = shopId;
            }
            PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/"+ shopIdGlob + "-??????-????.csv");
            Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {

                    if (pathMatcher.matches(path)){
                        //приводим к нужному формату Files.getLastModifiedTime(path) время мод файла
                        LocalDate dateFile = LocalDate.ofInstant(Files.getLastModifiedTime(path).toInstant(), ZoneId.systemDefault());
                        //сравниваем дату файла относительно дат запроса
                        int startDateComparison;
                        int finishDateComparison;
                        if (start == null){
                            startDateComparison = 1;
                        } else {
                            startDateComparison = dateFile.compareTo(start);//должго быть больше 0
                        }
                        if (finish == null){
                            finishDateComparison = -1;
                        } else {
                            finishDateComparison = dateFile.compareTo(finish);//должно быть меньше 0
                        }

//                        System.out.println("start date- " + start);
//                        System.out.println("finish date- " + finish);
                        //выбираем только файлы попадающие в интервал даты
                        if (startDateComparison >= 0 && finishDateComparison <= 0){
                            //System.out.println(Files.getLastModifiedTime(path));
                            LocalDateTime dateFileTime = LocalDateTime.ofInstant(Files.getLastModifiedTime(path).toInstant(), ZoneId.systemDefault());
                            //полуаем массив из названия файла в котором идентификаторы
                            String[] nameFile = String.valueOf(path.getFileName()).replace(".csv","").split("-");
//                            System.out.println(nameFile[0]);
//                            System.out.println(nameFile[1]);
//                            System.out.println(nameFile[2]);
                            workWithFile(path,dateFileTime,nameFile);//работа с файлом
                        }

                    }

                    return FileVisitResult.CONTINUE;
                }
                @Override
                public FileVisitResult visitFileFailed(Path file, IOException e) {
                    countError++;
                    return FileVisitResult.CONTINUE;
                }
            });

        } catch (Exception e){
            System.out.println(e);
            countError++;
        }
        return countError;
    }
    //работа с отфильтрованным файлом
    private void workWithFile(Path path, LocalDateTime dateFileTime, String[] nameFile) {

        order = new Order();
        order.shopId = nameFile[0];
        order.orderId = nameFile[1];
        order.customerId = nameFile[2];
        order.datetime = dateFileTime;
        order.items = new ArrayList<OrderItem>();

        order.sum = 0;
        try {
            List<String> allLines = Files.readAllLines(path);
            for (String line : allLines){
       //         System.out.println(line);
                String[] tovar = line.split(",");
//                System.out.println(tovar[0].trim());
//                System.out.println(tovar[1].trim());
//                System.out.println(tovar[2].trim());
                OrderItem orderItem = new OrderItem();
                orderItem.googsName = tovar[0].trim();
                orderItem.count = Integer.parseInt(tovar[1].trim());
                orderItem.price = Double.valueOf(tovar[2].trim());
                order.items.add(orderItem);
                double sumTovar = orderItem.count * orderItem.price;
                order.sum += sumTovar;

            }

            //System.out.println(order.sum);
//            for (OrderItem lines : order.items){
//                System.out.println(lines);
 //           }
          // System.out.println(order);
           // System.out.println(process.isEmpty());
            if (process != null) {
                process.add(order);
            }

        } catch (ArrayIndexOutOfBoundsException y){
            //System.out.println("y- " + y);
            countError++;
        }
        catch (Exception e){
            System.out.println(e);
        }

        Collections.sort(order.items, new Comparator<OrderItem>() {
            public int compare(OrderItem o1, OrderItem o2) {
                return o1.googsName.compareTo(o2.googsName);
            }
        });

    }

    public List<Order> process(String shopId){

        process = new ArrayList<Order>();
        loadOrders(null,null,shopId);
        Collections.sort(process, new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                return o1.datetime.compareTo(o2.datetime);
            }
        });
        return process;
    }

    public Map<String, Double> statisticsByShop(){
        statisticsByShop = new TreeMap<>();
        process(null);
        for (int i=0;i<process.size();i++){
//            System.out.println(process.get(i).sum);
//            System.out.println(process.get(i).shopId);
            if (statisticsByShop.containsKey(process.get(i).shopId)){
                double sumSop = statisticsByShop.get(process.get(i).shopId) + process.get(i).sum;
                //System.out.println("sumSop" + sumSop);
                statisticsByShop.put(process.get(i).shopId, sumSop);
            } else {
                statisticsByShop.put(process.get(i).shopId, process.get(i).sum);
            }

        }
        return statisticsByShop;
    }

    public Map<String, Double> statisticsByGoods(){
        return null;
    }

    public Map<LocalDate, Double> statisticsByDay(){
        return null;
    }

    public static void main(String[] args) throws ParseException {
        String startPath = "C:\\Users\\admin\\IdeaProjects\\MyTest\\proect_zadanie\\OrderProcessor";
        LocalDate start = LocalDate.of(2020,03,03);
        LocalDate finish = LocalDate.of(2020,04,02);
        String shopId = "S02";

        OrderProcessor orderProcessor = new OrderProcessor(startPath);
        System.out.println(orderProcessor.loadOrders(null,null,null));

        //System.out.println("process - " + orderProcessor.process(null));
        System.out.println("\nprocess\n");
        for (int i=0;i<orderProcessor.process(null).size();i++ ){
            System.out.println(orderProcessor.process(null).get(i));
        }
        System.out.println("\nstatisticsByShop\n");
        System.out.println(orderProcessor.statisticsByShop());
        System.out.println("\nstatisticsByGoods\n");
        System.out.println(orderProcessor.statisticsByGoods());
/*
       //меняем дату последнего изменения файла
        File fileInfo1 = new File("C:\\Users\\admin\\IdeaProjects\\MyTest\\proect_zadanie\\OrderProcessor\\1\\S02-P01X12-0012.csv");
        System.out.println("lastModified: " + new Date(fileInfo1.lastModified()));
        File fileInfo2 = new File("C:\\Users\\admin\\IdeaProjects\\MyTest\\proect_zadanie\\OrderProcessor\\2\\S03-P01X12-0013.csv");
        System.out.println("lastModified: " + new Date(fileInfo2.lastModified()));
        File fileInfo3 = new File("C:\\Users\\admin\\IdeaProjects\\MyTest\\proect_zadanie\\OrderProcessor\\3\\S02-P01X13-0010.csv");
        System.out.println("lastModified: " + new Date(fileInfo3.lastModified()));

        //print the original last modified date
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        System.out.println("Original Last Modified Date : "
                + sdf.format(fileInfo1.lastModified()));

        //set this date
        String newLastModified = "01/03/2020";

        //need convert the above date to milliseconds in long value
        Date newDate = sdf.parse(newLastModified);
        fileInfo1.setLastModified(newDate.getTime());

        //print the latest last modified date
        System.out.println("Lastest Last Modified Date : "
                + sdf.format(fileInfo1.lastModified()));

       // fileInfo1.setLastModified(modDate);
        lastModified: Mon May 18 11:46:55 MSK 2020
        lastModified: Sat Apr 18 11:55:23 MSK 2020
        lastModified: Tue Apr 28 05:01:38 MSK 2020
*/
    }
}
