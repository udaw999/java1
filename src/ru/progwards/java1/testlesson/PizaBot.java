package ru.progwards.java1.testlesson;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.Constants;

import java.util.List;
import java.util.Scanner;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
public class PizaBot extends ProgwardsTelegramBot{

    private final String menu = "У нас ты можешь попробовать: автопробег, прыжок с парашутом, подводное погружение";





    @Override
    public String processMessage(String text) {
        checkTags(text);

        //----------------
        if (foundCount() == 1) {
            if (checkLastFound("привет"))
                return "Приветствую тебя, любитель экстрима!\n Что желаешь? " + menu;
            if (checkLastFound("конец"))
                return "Спасибо за заказ.";
            if (checkLastFound("дурак"))
                return "Не надоругаться. Я не волшебник, я только учусь";

            return "Отлично, добавляю в заказ " + getLastFound() + " Желаешь что-то еще?";

        }
            if (foundCount() > 1)
                return "Под твой запрос подходит: \n" + extract() + "Выбери что-то одно, и я реализую твое желание.";
            return "Я не понял, возможно у нас этого нет, попробуй сказать по другому. " + menu;


    }

    public static void main(String[] args) {
        System.out.println("Hello Bot");
        ApiContextInitializer.init();

        PizaBot bot = new PizaBot();
        bot.username = "audi101_bot";
        bot.token = "1026615567:AAGJAlPmCXD2yZLFHgz5DjW9cOPbnjmk9Ac";

        bot.addTags("привет", "привет, здрасьте, здравствуй, добр, день, вечер, утро, hi, hello");

        bot.addTags("конец", "конец, все, стоп, нет");

        bot.addTags("дурак", "дурак, придурок, идиот, тупой");

        bot.addTags("Подводное погружение к кораблю", "подводное, погружение, корабл");
        bot.addTags("Подводное погружение с охотой", "подводное, погружение, охот");

        bot.addTags("Прыжок с парашутом с самолета", "прыжок, параш, самолет");
        bot.addTags("Прыжок с парашутом с горы", "прыжок, параш, гор");
        bot.addTags("Прыжок с парашутом с высотки", "прыжок, параш, высотк");

        bot.addTags("Автопробег в пустыне", "пес, авто, пробег, пустын");
        bot.addTags("Автопробег в горах", "авто, пробег, гор");
        bot.addTags("Автопробег в по берегу моря", "авто, пробег, мор, берег");

        //bot.start();
        bot.test();
    }

    void test() {
    	Scanner in = new Scanner(System.in);
    	String input;
    	//TUser user = new TUser();
    	do {
    		input = in.nextLine();

    		System.out.println(processMessage( input));
    	} while (!input.equals("стоп"));
    }




}
