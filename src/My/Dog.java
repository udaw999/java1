package My;
// T8.2 Тест блока 2(рабочий вариант)
/*Описаны интерфейсы

public interface Speaking {
    public String say();
 }

public interface Eating {
     public String eat();
 }

реализовать 2 класса, Dog и Goat.
У класса Dog метод say() должен вернуть "Гав"
У класса Dog метод eat() должен вернуть "Мясо"
У класса Goat метод say() должен вернуть "Мее"
У класса Goat метод eat() должен вернуть "Сено"   */
public class Dog implements Speaking, Eating {

    @Override
    public String say() {
        return "Гав";
    }

    @Override
    public  String eat() {
        return "Мясо";
    }

    public static void main(String[] args) {
        Dog dog = new Dog();
        System.out.println(((Speaking)dog).say());
        System.out.println(((Eating)dog).eat());
        //((Speaking)test.new Dog()).say();

        //можно вывести и таким способом
        System.out.println(((Eating)new Goat()).eat());
        System.out.println(((Speaking)new Dog()).say());
    }
}
