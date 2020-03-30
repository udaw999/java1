package ru.progwards.java1.SeaBattle.urda1975.copy.cop2;

import ru.progwards.java1.SeaBattle.SeaBattle;
import ru.progwards.java1.SeaBattle.SeaBattle.FireResult;

import java.util.Arrays;

public class SeaBattleAlg {

    // Тестовое поле создаётся конструктором
    //     SeaBattle seaBattle = new SeaBattle(true);
    //
    // Обычное поле создаётся конструктором по умолчанию:
    //     SeaBattle seaBattle = new SeaBattle();
    //     SeaBattle seaBattle = new SeaBattle(false);
    //
    // Посомтреть результаты стрельбы можно в любой момент,
    // выведя объект класса SeaBattle на консоль. Например так:
    //     System.out.println(seaBattle);
    //
    //
    // Вид тестового поля:
    //
    //           0 1 2 3 4 5 6 7 8 9    координата x
    //         0|.|.|X|.|.|.|.|X|X|X|
    //         1|.|.|X|.|.|.|.|.|.|.|
    //         2|.|.|.|.|.|.|.|X|.|.|
    //         3|.|.|.|.|.|.|.|.|.|.|
    //         4|.|.|.|.|X|X|.|.|.|X|
    //         5|.|X|.|.|.|.|.|.|.|.|
    //         6|.|.|.|.|.|.|.|.|.|.|
    //         7|X|.|.|X|.|X|X|Х|X|.|
    //         8|.|.|.|X|.|.|.|.|.|.|
    //         9|.|.|.|X|.|.|X|X|.|.|

    char[][] field;//создаем поле для видимости работы алгоритма
    SeaBattle seaBattle;
    int hits = 0;//счетчик попаданий
    int hits1 = 0;//счетчик попаданий(test delet
    int hits2 = 0;//счетчик попаданий(test delet


    void init(SeaBattle seaBattle){
        this.seaBattle = seaBattle;
        field = new char[seaBattle.getSizeX()][seaBattle.getSizeY()];
        for(int i=0; i<seaBattle.getSizeX(); i++){
            
            Arrays.fill(field[i], ' ');

           
        }
    }
//ячейки поля
    void print (){
        for (int x=0; x < seaBattle.getSizeY(); x++){
            String str = "|";
            for (int y=0; y < seaBattle.getSizeX(); y++){
                str += field[x][y] + "|";
            }
            System.out.println(str);
        }
    }
    //счетчик успешных выстрелов
    void hitsPlusPlus(){
        hits++;
    }
    //метка убил или мимо
    void markFire(int x, int y, FireResult result){
        if (result != FireResult.MISS){
            field[x][y] = 'X';
            hitsPlusPlus();//счетчик попаданий

            hits1++;
        } else {
            field[x][y] = '*';
        }
    }

    //метка убил или мимо при поиске (test переопределить на основной потом
    void markFireTest(int x, int y, FireResult result){
        if (result != FireResult.MISS){
            field[x][y] = 'Z';
            hitsPlusPlus();//счетчик попаданий

            hits2++;
        } else {
            field[x][y] = '-';
        }
    }
    Enum direction = SeaBattleAlg.DirectionHits.RIGHT;
    enum DirectionHits {RIGHT,LEFT,TOP, DirectionHits, BOTTOM}
    //усли не промахнулись то
    void noMiss(int x, int y, FireResult result) {
        if (result == FireResult.DESTROYED){

        } else {

            hits(x, y, result, direction);

        }

    }

    //FireResult.MISS -- мимо
    //FireResult.HIT -- попал - ранен
    //FireResult.DESTROYED -- убит
    //если ранил то добиваем


    void hits(int x, int y, FireResult result, Enum direction) {

            int xX = x;//оригинал координат
            int yY = y;//оригинал координат

            int edge = 0;//x / y -координата границы поля
            int edgeZ = 0;//0 / 9 -координата границы поля

            FireResult threePalubResult = null;

            if (direction == SeaBattleAlg.DirectionHits.RIGHT){ edge = y; edgeZ = 9; }
            if (direction == SeaBattleAlg.DirectionHits.LEFT){ edge = y; edgeZ = 0; }
            if (direction == SeaBattleAlg.DirectionHits.TOP){ edge = x; edgeZ = 0; }
            if (direction == SeaBattleAlg.DirectionHits.BOTTOM){ edge = x; edgeZ = 9; }

        System.out.println("direction по каккой стороне прострел - " + direction);
        if (direction == SeaBattleAlg.DirectionHits.RIGHT){x = x; y = y + 1;}
        if (direction == SeaBattleAlg.DirectionHits.LEFT){x = x; y = y - 1;}
        if (direction == SeaBattleAlg.DirectionHits.TOP){x = x - 1; y = y;}
        if (direction == SeaBattleAlg.DirectionHits.BOTTOM){x = x + 1; y = y;}
        //стреляю сперва в право
        if (edge != edgeZ && field[x][y] == ' ') {//стреляю по второй палубе


            FireResult tvoPalubResult = seaBattle.fire(x, y);
            //если попал но не убит
            markFireTest(x, y, tvoPalubResult);//визуализация-- заполняет массив результатами стрельбы
            if (tvoPalubResult == FireResult.HIT) {

                if (direction == SeaBattleAlg.DirectionHits.RIGHT){x = x; y = y + 1;}
                if (direction == SeaBattleAlg.DirectionHits.LEFT){x = x; y = y - 1;}
                if (direction == SeaBattleAlg.DirectionHits.TOP){x = x - 1; y = y;}
                if (direction == SeaBattleAlg.DirectionHits.BOTTOM){x = x + 1; y = y;}
                if (edge != edgeZ && field[x][y] == ' ') {//стреляю по третьей палубе


                    threePalubResult = seaBattle.fire(x, y);
                    markFireTest(x, y, threePalubResult);//визуализация-- заполняет массив результатами стрельбы

                    if (threePalubResult == FireResult.HIT) {

                        if (direction == SeaBattleAlg.DirectionHits.RIGHT){x = x; y = y + 1;}
                        if (direction == SeaBattleAlg.DirectionHits.LEFT){x = x; y = y - 1;}
                        if (direction == SeaBattleAlg.DirectionHits.TOP){x = x - 1; y = y;}
                        if (direction == SeaBattleAlg.DirectionHits.BOTTOM){x = x + 1; y = y;}
                        if (edge != edgeZ && field[x][y] == ' ') {//стреляю по четвертой палубе

                            FireResult fourPalubResult = seaBattle.fire(x, y);
                            markFireTest(x, y, fourPalubResult);//визуализация-- заполняет массив результатами стрельбы

                            if (fourPalubResult == FireResult.DESTROYED) {//если ПОПАЛ И УБИТ

                                System.out.println("DESTROYED - если ПОПАЛ И УБИТ на палубе 4 x- " + x + ", y- " + y);
                                //обработать обводку убитого
                            }
                        } else {
                            if (edge == edgeZ){
                                System.out.println("не убит но край поля на палубе 3 x- " + x + ", y- " + y);
                                //выходим и начинам левый проход
                                //команда на левый старт
//                                if (direction == RIGHT){direction = LEFT; hits(xX, yY, result, direction);}
//                                if (direction == LEFT){direction = TOP; hits(xX, yY, result, direction);}
//                                if (direction == TOP){direction = BOTTOM; hits(xX, yY, result, direction);}
                            }
                        }

                    }
                    if (threePalubResult == FireResult.DESTROYED) {//если ПОПАЛ И УБИТ

                            System.out.println("DESTROYED - если ПОПАЛ И УБИТ на палубе 3 x- " + x + ", y- " + y);
                            //обработать обводку убитого
                    } else {
                            if (threePalubResult == FireResult.MISS) {//если промахнулся и не убит

                                System.out.println("MISS - промахнулся выход на палубе 3 x- " + x + ", y- " + y);
                                //выходим и начинам левый проход
                                //команда на левый старт
//                                if (direction == RIGHT){direction = LEFT; hits(xX, yY, result, direction);}
//                                if (direction == LEFT){direction = TOP; hits(xX, yY, result, direction);}
//                                if (direction == TOP){direction = BOTTOM; hits(xX, yY, result, direction);}
                            }
                    }


                } else {
                    if (edge == edgeZ){
                        System.out.println("не убит но край поля на палубе 2 x- " + x + ", y- " + y);
                        //выходим и начинам левый проход
                        //команда на левый старт
//                        if (direction == RIGHT){direction = LEFT; hits(xX, yY, result, direction);}
//                        if (direction == LEFT){direction = TOP; hits(xX, yY, result, direction);}
//                        if (direction == TOP){direction = BOTTOM; hits(xX, yY, result, direction);}
                    }
                }
            }
            if (tvoPalubResult == FireResult.DESTROYED) {//если ПОПАЛ И УБИТ
                    System.out.println("DESTROYED - если ПОПАЛ И УБИТ на палубе 2 x- " + x + ", y- " + y);
                    //обработать обводку убитого

            } else {
                    if (tvoPalubResult == FireResult.MISS) {//если промахнулся и не убит
                        System.out.println("MISS - промахнулся выход на палубе 2 x- " + x + ", y- " + y);
                        //выходим и начинам левый проход
                        //команда на левый старт
                        //System.out.println(direction + " x - " + x + " y - " + y + " xX - " + xX + " yY - " + yY);
                        if (direction == SeaBattleAlg.DirectionHits.RIGHT && threePalubResult != FireResult.DESTROYED){direction = SeaBattleAlg.DirectionHits.LEFT; hits(xX, yY, result, direction);}
                        if (direction == SeaBattleAlg.DirectionHits.LEFT && threePalubResult != FireResult.DESTROYED){direction = SeaBattleAlg.DirectionHits.TOP; hits(xX, yY, result, direction);}
//                        if (direction == TOP){direction = BOTTOM; hits(xX, yY, result, direction);}

                    }
            }

        } else {
            if (edge == edgeZ){
                System.out.println("не убит но край поля на палубе 1 x- " + x + ", y- " + y);
                //выходим и начинам левый проход
                //команда на левый старт
//                if (direction == RIGHT){direction = LEFT; hits(xX, yY, result, direction);}
//                if (direction == LEFT){direction = TOP; hits(xX, yY, result, direction);}
//                if (direction == TOP){direction = BOTTOM; hits(xX, yY, result, direction);}

            }
        }

    }
    public void battleAlgorithm(SeaBattle seaBattle) {
        // пример алгоритма:
        // стрельба по всем квадратам поля полным перебором
        init(seaBattle);//заполняемый массив результатами прострела

//        field[3][3] = 'X';
//        field[3][4] = 'X';
//        field[3][5] = 'X';
//        field[3][6] = 'X';
//        field[3][7] = 'X';
        int n = 0;//для перебора
        int z = 1;//для перебора
        int y = 0;
        int x = 0;
        while ( y < seaBattle.getSizeY() && hits != 20) {
            x = 3 - y - n;
            if( x < 0 && x >= -4){
                x = x + 4;
            }
            if(x < -4 && x >= -8){
                x = x + 8;
            }
            if(x < -8 && x >= -12){
                x = x + 12;
            }
            while (x < seaBattle.getSizeX() && hits != 20) {

                if (field[x][y] == ' ') {//усли ячейка в моем поле пуста то стреляем


                    FireResult fireResult = seaBattle.fire(x, y);//выстрел

                    if (fireResult != FireResult.MISS) {//если не промахнулись

                        noMiss(x, y, fireResult);// обрабатываем обводку, поиск палубы еще
                                                    // если ранен ++ считаем выстрелы попаданий
                        //markFire(x, y, fireResult);//визуализация-- заполняет мое поле результатами стрельбы
                        //field[x][y] = 'O';
                    }

                    markFire(x, y, fireResult);//визуализация-- заполняет мое поле результатами стрельбы
                 }

                x = x + 4;//для перебора
            }


//            if(y == 9 && z == 1 ) {
//                y = 0;
//                n = 2;
//                z++;
//            } else  if (y == 9 && z == 2 ){
//                y = 0;
//                n = 3;
//                z++;
//            } else  if (y == 9 && z == 3 ){
//                y = 0;
//                n = 1;
//                z++;
//            } else  {
                y++;
//            }


        }
        print ();//поле наглядно
        System.out.println("hits- попал сразу - " + hits1);
        System.out.println("hits2- попал при дальнейшем поиске - " + hits2);



    }




    // функция для отладки
    public static void main(String[] args) {

        System.out.println("Sea battle");
        SeaBattle seaBattle = new SeaBattle(true);
        new SeaBattleAlg().battleAlgorithm(seaBattle);

        System.out.println(seaBattle.getResult());





    }




}


