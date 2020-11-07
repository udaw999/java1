package ru.progwards.java2.lessons.recursion;
import java.time.Instant;
import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GoodsWithLambda {
    private List<Goods> list;

    void setGoods(List<Goods> list){
       this.list = list;
    }

    public List<Goods> sortByName(){
        list.sort(Comparator.comparing(a -> a.name));
        return list;
    }
    //вернуть список, отсортированный по артикулу, без учета регистра
    public List<Goods> sortByNumber(){
        list.sort((a,b) -> a.number.compareToIgnoreCase(b.number));
        return list;
    }
    //- вернуть список, отсортированный по первым 3-м символам артикула, без учета регистра
    public List<Goods> sortByPartNumber(){
        list.sort((a,b) -> a.number.substring(0,3).compareToIgnoreCase(b.number.substring(0,3)));
        return list;
    }
    //- вернуть список, отсортированный по количеству, а для одинакового количества, по артикулу, без учета регистра
    public List<Goods> sortByAvailabilityAndNumber(){

        return list.stream().sorted(
                Comparator.comparing(Goods::getAvailable).thenComparing((a,b) -> a.number.compareToIgnoreCase(b.number))
        ).collect(Collectors.toList());


    }
    //- вернуть список, с товаром, который будет просрочен после указанной даты, отсортированный по дате годности
    List<Goods> expiredAfter(Instant date){
        return  list.stream()
                .filter(x -> x.expired.getEpochSecond() < date.getEpochSecond())
                .sorted(Comparator.comparing(Goods::getExpired))
                .collect(Collectors.toList());
    }
    //- вернуть список, с товаром, количество на складе которого меньше указанного
    List<Goods> сountLess(int count){
        return list.stream().filter(x -> x.available < count).collect(Collectors.toList());
    }
    //- вернуть список, с товаром, количество на складе которого больше count1 и меньше count2
    List<Goods> сountBetween(int count1, int count2){
        return list.stream().filter(x -> x.available > count1)
                .filter(x -> x.available < count2).collect(Collectors.toList());
    }
    public static void main(String[] args) {
        Instant date = Instant.parse("2020-10-14T10:44:55.716647500Z");
        int count1 = 11;
        int count2 = 33;
        List<Goods> list = new ArrayList<>(List.of(
                new Goods("Капитанская дочка", "Пушкин",25, 545,"2020-06-14T10:44:55.716647500Z"),
                new Goods("Игрок", "Достоевский",30, 571,"2020-08-14T10:44:55.716647500Z"),
                new Goods("Кавказский пленник", "лермонтов", 10,597,"2020-06-14T10:44:55.716647500Z"),
                new Goods("Мёртвые души", "Гоголь",25, 842,"2020-07-14T10:44:55.716647500Z"),
                new Goods("Облако в штанах", "Маяковский",100, 495,"2020-11-14T10:44:55.716647500Z")
        ));

        GoodsWithLambda goodsWithLambda = new GoodsWithLambda();
        goodsWithLambda.setGoods(list);

        list.forEach(System.out::println);
        System.out.println();
        //System.out.println(goodsWithLambda.sortByAvailabilityAndNumber());
        goodsWithLambda.сountBetween(count1, count2).forEach(System.out::println);
    }
}
