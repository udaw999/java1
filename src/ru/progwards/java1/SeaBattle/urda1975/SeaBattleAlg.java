package ru.progwards.java1.SeaBattle.urda1975;

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
    int hits2 = 0;//счетчик попаданий
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
    void markFire(int x, int y, SeaBattle.FireResult result){
        if (result != FireResult.MISS){
            field[x][y] = 'X';
            hits2++;
        } else {
            field[x][y] = '*';
        }
    }

    //метка убил или мимо
    void markFireTest(int x, int y, SeaBattle.FireResult result){
        if (result != FireResult.MISS){
            field[x][y] = 'Z';
            hits2++;
        } else {
            field[x][y] = '-';
        }
    }
    //усли не промахнулись то
    void noMiss(int x, int y, SeaBattle.FireResult result) {
        if (result == FireResult.DESTROYED){
            //hitsPlusPlus();//счетчик успешных выстрелов 8
        } else {
            //System.out.println("tut hits");
            hits(x, y, result);
        }

    }
    //FireResult.MISS -- мимо
    //FireResult.HIT -- попал - ранен
    //FireResult.DESTROYED -- убит
    //если ранил то добиваем
    boolean leftStartAtac = false;
    void hits(int x, int y, SeaBattle.FireResult result ) {


        rightAtac(x ,y , result);



            //System.out.println("справа" + field[x][y]);


    }
    void leftAtac(int x, int y, SeaBattle.FireResult result) {

    }
    void rightAtac(int x, int y, SeaBattle.FireResult result) {
        //секуляю в право
        if (y != 9 && field[x][y] == ' ') {//убиваем второй справа
            System.out.println("end y- " + y);
            x = x;
            y = y + 1;
            SeaBattle.FireResult rightResult = seaBattle.fire(x, y);
            //если попал но не убит
            markFireTest(x, y, rightResult);//визуализация-- заполняет массив результатами стрельбы
            if (rightResult == FireResult.HIT) {

                if (y != 9 && field[x][y + 1] == ' ') {//убиваем третий справа
                    x = x;
                    y = y + 1;
                    SeaBattle.FireResult rightResult3 = seaBattle.fire(x, y);
                    markFireTest(x, y, rightResult3);//визуализация-- заполняет массив результатами стрельбы

                    if (rightResult == FireResult.HIT) {
                        if (y != 9 && field[x][y + 2] == ' ') {//убиваем четвертый справа
                            x = x;
                            y = y + 1;
                            SeaBattle.FireResult rightResult4 = seaBattle.fire(x, y);
                            markFireTest(x, y, rightResult4);//визуализация-- заполняет массив результатами стрельбы

                        }
                        if (rightResult == FireResult.DESTROYED) {//если ПОПАЛ И УБИТ

                            //добавить счетчик
                            hitsPlusPlus();//счетчик успешных выстрелов
                        }
                        hitsPlusPlus();//счетчик успешных выстрелов -- 1
                    }
                    if (rightResult == FireResult.DESTROYED) {//если ПОПАЛ И УБИТ

                        //добавить счетчик
                        hitsPlusPlus();//счетчик успешных выстрелов
                    }
                    if (rightResult == FireResult.MISS) {//если промахнулся и не убит

                        //назначаем новые значения х и у, выходим и начинам левый проход
                        leftStartAtac = true; //команда на левый старт

                    }
                }
                if (rightResult == FireResult.DESTROYED) {//если ПОПАЛ И УБИТ
                    System.out.println("DESTROYED если ПОПАЛ И УБИТ x- " + x + ", y- " + y);
                    //добавить счетчик
                    hitsPlusPlus();//счетчик успешных выстрелов
                }

                if (rightResult == FireResult.MISS) {//если промахнулся и не убит
                    System.out.println("MISSпромахнулся x- " + x + ", y- " + y);
                    //назначаем новые значения х и у, выходим и начинам левый проход
                    leftStartAtac = true; //команда на левый старт

                }


                //markFireTest(x, y, result);//визуализация-- заполняет массив результатами стрельбы
                //hits(x, y, fireResult);//если ранен
                hitsPlusPlus();//счетчик успешных выстрелов  -- 2
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
        while ( y < seaBattle.getSizeY() && hits2 != 20) {
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
            while (x < seaBattle.getSizeX() && hits2 != 20) {

                if (field[x][y] == ' ') {//усли ячейка в моем поле пуста то стреляем


                    SeaBattle.FireResult fireResult = seaBattle.fire(x, y);//выстрел

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


            if(y == 9 && z == 1 ) {
                y = 0;
                n = 2;
                z++;
            } else  if (y == 9 && z == 2 ){
                y = 0;
                n = 3;
                z++;
            } else  if (y == 9 && z == 3 ){
                y = 0;
                n = 1;
                z++;
            } else  {
                y++;
            }


        }
        print ();//поле наглядно
        System.out.println("hits- " + hits2);


    }




    // функция для отладки
    public static void main(String[] args) {

        System.out.println("Sea battle");
        SeaBattle seaBattle = new SeaBattle(true);
        new SeaBattleAlg().battleAlgorithm(seaBattle);

        System.out.println(seaBattle.getResult());





    }




}


