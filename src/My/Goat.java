package My;
// T8.2 Тест блока 2(рабочий вариант)
public class  Goat implements Speaking, Eating{

    @Override
    public String say() {
        return "Мее";
    }

    @Override
    public   String eat() {
        return "Сено";
    }

}
