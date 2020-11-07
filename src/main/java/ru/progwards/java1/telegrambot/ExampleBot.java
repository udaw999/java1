package ru.progwards.java1.telegrambot;


import java.util.Scanner;

import org.telegram.telegrambots.ApiContextInitializer;

import ru.progwards.java1.telegrambot.ProgwardsTelegramBot;
import ru.progwards.java1.telegrambot.ProgwardsTelegramBot.FoundTags;

public class ExampleBot extends ProgwardsTelegramBot {

    private final String menu = "У нас есть пицца, картошка, напитки и десерт";
    private final String menuSous = "Я прелагаю:\n Соус сырный\n Соус томатный,\n Соус с огурцом";

    private static final String orderKey = "order";
    private static final String addressKey = "address";

    private boolean stop = false;
    private boolean sous = false;

    String[] numDelete;//массив позиций для отмена

    // Пердложить группы блюд
    // спросить адрес доставки
    String finishCheck(Integer userid) {
        // проверить что все 4 группы блюд в заказе
        // если какой-то группы нет && бот не предлагал
        // то предложить и учтановить ключ, что бы не предлагать 2 раза

        // спросить адрес доставки
        // проверить что заказ не пуст
        if (getUserData(userid, addressKey) == null ) {
            setUserData(userid, addressKey, "*");

            return "Укажите, пожалуйста адрес доставки";
        }


        stop = true;
        // если заказ пуст выдать другое сообщение

        return "Спасибо за заказ.";
    }

    // сохранить в заказ
    void saveOrderItem(Integer userid, FoundTags tags) {
        // считать количество
        Integer count = 0;
        String str = getUserData(userid, orderKey);
        if (str != null)
            count = Integer.parseInt(str);
        // инкрементировать количество
        count++;
        // сохранить количество
        setUserData(userid, orderKey, count.toString());
        // сохраняю данные о заказе
        // сохранить позицию в заказе как orderKey + count
        // ключи - order1, order2
        // данные - getLastFound(tags)
        setUserData(userid, orderKey + count, getLastFound(tags));


        // дополнительно 1
        // проверить связанные покупки
        // если он заказывает картошку, то предложить соус, если не предлагали
    }

    String getOrder(Integer userid) {
        // считать количество
        Integer count = 0;
        String str = getUserData(userid, orderKey);
        if (str != null)
            count = Integer.parseInt(str);
        // инкрементировать количество
        count++;

        //выводим заказ на панель
        // в цикле по каждому элементу вывести содержимое
        // ключ orderKey + номер
        String getZacaz ="";
       for (int i=1; i<count; i++) {

           //System.out.println(i + ". " + getUserData(userid, orderKey + i));
           getZacaz = getZacaz  + i + ". "+ getUserData(userid, orderKey + i) + "\n";
       }
        //System.out.println("Адрес: " + getUserData(userid, addressKey));
        return "Ваш заказ: \n" + getZacaz + "\nВ заказе позиций - " + getUserData(userid,orderKey) +
                "\n Ваш адрес: " + getUserData(userid, addressKey);
    }

    /**
     * Метод, который возвращает ответ бота
     * @return ответ
     */
    @Override
    public String processMessage(Integer userid, String text) {
        // проверяем, спрашивали ли адрес доставки
        if (getUserData(userid, addressKey) != null && getUserData(userid, addressKey).equals("*")) {
            setUserData(userid, addressKey, text.toLowerCase());//добавляем введенный с клавы адресс
            return "Добавил в заказ ваш адрес: " + getUserData(userid, addressKey);
        }
        // сброс всех данных для пользователя - нужно для тестирования
        if (text.equals("/reset"))
            cleartUserData(userid);

        // ищем подходящие тэги рлд запрос из заданных через addTags
        FoundTags tags = checkTags(text);

        //----- если пришел тагс с вопросом об адресе. проверяем не заполняли ли раньше
        if (checkLastFound(tags, "ваш адрес")) {
            if (getUserData(userid, addressKey) == null ) {
                setUserData(userid, addressKey, "*");

                return "Укажите, пожалуйста адрес доставки"; // если не то просим заполнить
                //и оправляемся в блок выше чтоб добавить введенный с клавы адресс
            }
            //если есть то выводим его
            return "ваш адрес -" + getUserData(userid, addressKey);

        }
        //--------------надо разобраться с ошибкой
        // если найдено всего один вариант

        if (foundCount(tags) == 1) {
            if (checkLastFound(tags, "привет"))
                return "Приветствую тебя "+ userid +", мой повелитель!\nЧто желаешь? " + menu;
            if (checkLastFound(tags, "конец"))
                return finishCheck(userid);
            if (checkLastFound(tags, "дурак"))
                return "Не надо ругаться. Я не волшебник, я только учусь";
            if (checkLastFound(tags, "нет"))
                return "Ну нет, так нет";



            if (checkLastFound(tags, "да"))
                return "Добавь что желаешь еще? " + menu;
            if (checkLastFound(tags, "заказ"))
                return getOrder(userid);
//            if (getLastFound(tags).equals("картошка"))
//               return "Не желаете ли соус? " + menuSous;


            // дополнительно 2
            // реализовать отмену позиции заказа


            boolean numD = false;
            for (String num : numDelete) {//проверяем совпадает ли введенная позиция с данными в массиве
                if (num.equals(getLastFound(tags)))
                    numD = true;
            }
            if (numD == true){// если одно значение совпало значит будем его удалять и все смещать
                String tagsDelete = getUserData(userid,orderKey + getLastFound(tags)); // то что удалим
                int intTags = Integer.parseInt(getLastFound(tags));//получаем цифру удаляемой позиции
                int countUserData = Integer.parseInt(getUserData(userid,orderKey)); //количество позиций в заказе
                //цыкл замены на место удаляемой позиции и перенос остальных позиций
                for (int i = intTags; i<countUserData;i++){
                    setUserData(userid,orderKey + (i),getUserData(userid,orderKey + (i + 1)));
                }
                setUserData(userid, orderKey, String.valueOf(countUserData - 1));//уменьшаем количество позиций в заказе
                //getOrder(userid);//вывод списка измененного заказа
                return getOrder(userid) + "\nудалил позицию - "+ getLastFound(tags) + " \n" + tagsDelete +
                        "\n желаете еще чтото изменить?";}
            //---конец блока отмены товара

            saveOrderItem(userid, tags);

            if (getLastFound(tags).equals("Картошка фри") & sous == false){
                // Добавить связанные предложения, например если он заказывает картошку, то предложить соус,
                sous = true;// если взял - сохранить флажок, что бы бесконечно не предлагать
                return "Отлично, добавляю в заказ " + getLastFound(tags) + " " +sous+" \n" +
                        " Желаешь соус к картошке? \n" + menuSous;
            } else {

                return "Отлично, добавляю в заказ " + getLastFound(tags) + " Желаешь что-то еще?";

            }

        }
        if (foundCount(tags) > 1)
            return "Под твой запрос подходит: \n" + extract(tags) + "Выбери что-то одно, и я добавлю это в заказ.";
        return "Я не понял, возможно у нас этго нет, попробуй сказать по другому. " + menu;
    }

    public static void main(String[] args) {
        System.out.println("Hello bot!");
        ApiContextInitializer.init();

        // инициализируем бота
        ExampleBot bot = new ExampleBot();
        bot.username = "audi101_bot";
        bot.token = "1026615567:AAGJAlPmCXD2yZLFHgz5DjW9cOPbnjmk9Ac";

        // наполняем тэгами
        bot.addTags("привет", "привет, здасьте, здравствуйте, добр, день, вечер, утро, hi, hello");
        bot.addTags("конец", "конец, все, стоп");
        bot.addTags("дурак", "дурак, идиот, тупой");

        //создаем теги для удаления позиций
        bot.numDelete = new String[]{"1","2","3","4","5","6","7","8","9","10"};//массив позиций для отмены
        for (int i=0;i<bot.numDelete.length;i++){
            bot.addTags(bot.numDelete[i], bot.numDelete[i]);
        }

        // добавлено
        bot.addTags("заказ", "заказ");
        bot.addTags("нет", "нет");
        bot.addTags("да", "да, желаю");
        bot.addTags("ваш адрес", "адрес");

        bot.addTags("Картошка фри", "картошк, фри");

        bot.addTags("Соус сырный", "сырный, соус");
        bot.addTags("Соус томатный", "томатный, соус");
        bot.addTags("Соус с огурцом", "с огурцом, соус");

        bot.addTags("Пицца гавайская", "гавайск, пицц, ананасы, курица");
        bot.addTags("Пицца маргарита", "маргарит, пицц, моцарелла, сыр, кетчуп, помидор");
        bot.addTags("Пицца пеперони", "пеперони, пицц, салями, колюас, сыр, кетчуп, помидор");

        bot.addTags("Торт тирамису", "десерт, кофе, маскарпоне, бисквит");
        bot.addTags("Торт медовик", "десерт, мед, бисквит");
        bot.addTags("Эклеры", "десерт, заварной, крем, тесто");

        bot.addTags("Кола", "напит, пить, кола");
        bot.addTags("Холодный чай", "напит, пить, чай, липтон, лимон");
        bot.addTags("Сок", "напит, пить, сок, апельсиноый, яблочный, вишневый");

        bot.start();
        //bot.test();
    }

//    void test() {
//        Scanner in = new Scanner(System.in);
//        String input;
//        do {
//            input = in.nextLine();
//
//            System.out.println(processMessage(123, input));
//        } while (!stop);
//        in.close();
//    }
}

