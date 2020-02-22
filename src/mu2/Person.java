package mu2;
//T8.3 Тест блока 3
/*Создайте класс Person, а внутри него 2 класса

вложенный класс Child1
внутренний класс Child2
У каждого класса, Child1 и Child2 создайте метод String hello()

У Child1 String hello() должен вернуть "привет"
У Child2 String hello() должен вернуть "servus"*/
public class Person {
    public static class Child1 {
        public String hello(){
            return "привет";
        }
    }

    public class Child2 {
        public String hello(){
            return "servus";
        }
    }

    public static void main(String[] args) {
        System.out.println(new Person.Child1().hello());
        System.out.println(new Person().new Child2().hello());
    }
}
