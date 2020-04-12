package ru.progwards.java1.SeaBattle.urda1975;

import ru.progwards.java1.SeaBattle.SeaBattle;
import ru.progwards.java1.SeaBattle.SeaBattle.FireResult;

import java.util.Arrays;

import static ru.progwards.java1.SeaBattle.SeaBattle.*;
import static ru.progwards.java1.SeaBattle.urda1975.SeaBattleAlg.DirectionHits.*;

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
    int hitses = 0;//счетчик попаданий
    int hits1 = 0;//счетчик попаданий(test delet
    int hits2 = 0;//счетчик попаданий(test delet
    int coynt=0;

    public static int nz = 0;

    int mimo = 0; //*
    int popal = 0; //X
    int nascel = 0; //Z
    public static int sravn = 0; // считаю сколько не соответствий Х И Z
    int minus = 0; //-

    void statistic() {//

        for (int y = 0; y < seaBattle.getSizeX(); y++){
            for (int x = 0; x < seaBattle.getSizeY(); x++){
                if (field[x][y] == 'Z'){
                    nascel++;
                }
                if (field[x][y] == 'X'){
                    popal++;
                }
                if (field[x][y] == '*'){
                    mimo++;
                }
                if (field[x][y] == '-'){
                    minus++;
                }
            }
        }
    }

    void init(SeaBattle seaBattle){
        this.seaBattle = seaBattle;
        field = new char[seaBattle.getSizeX()][seaBattle.getSizeY()];
        for(int i=0; i<seaBattle.getSizeX(); i++){

            Arrays.fill(field[i], ' ');


        }
    }
    //ячейки поля
    void print (){
        System.out.println(" _0_1_2_3_4_5_6_7_8_9");

        for (int x=0; x < seaBattle.getSizeY(); x++){
            String str = x + "|";

            for (int y=0; y < seaBattle.getSizeX(); y++){
                str += field[x][y] + "|";
            }
            System.out.println(str);
        }
    }
    //счетчик успешных выстрелов
    void hitsPlusPlus(){
        hitses++;
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

    //пометить корабли по кругу
    void markHit(int x, int y) {
        markDot(x-1, y-1);
        markDot(x-1, y);
        markDot(x-1, y+1);
        markDot(x+1, y-1);
        markDot(x+1, y);
        markDot(x+1, y+1);
        markDot(x, y-1);
        markDot(x, y+1);
    }
    //метим ячейки не имеющие смысл для стрельбы
    void markDot(int x, int y) {
        if (x<0 || y<0 || x>9 || y>9){
            return;
        }
        if (field[x][y] == ' '){
            field[x][y] = '.';
        }
    }
    //цикл обводки убитого корабля
    void markDestroyed(){
        for (int y = 0; y < seaBattle.getSizeX(); y++){
            for (int x = 0; x < seaBattle.getSizeY(); x++){
                if (field[x][y] == 'X' || field[x][y] == 'Z'){
                    markHit(x,y);
                    //field[x][y] = '.';
                }

            }
        }
    }

    Enum direction = RIGHT;
    enum DirectionHits {RIGHT,LEFT,TOP, DirectionHits, ShipInfo, BOTTOM}
    //усли не промахнулись то
    void noMiss(int x, int y, FireResult result) {
        if (result != FireResult.DESTROYED){

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

        if (direction == RIGHT && yY == 9 ){ hits(xX, yY, result, LEFT);}
        if (direction == LEFT && yY == 0 ){hits(xX, yY, result, TOP);}
        if (direction == TOP && xX == 0 ){hits(xX, yY, result, BOTTOM);}

        if (direction == RIGHT){x = x; y = y + 1; edge = y; edgeZ = 9; }//edge < edgeZ --- y < 10
        if (direction == LEFT){x = x; y = y - 1; edge = 0; edgeZ = y; }//edge < edgeZ --- 0 < y
        if (direction == TOP){x = x - 1; y = y; edge = 0; edgeZ = x; }//edge < edgeZ --- 0 < x
        if (direction == BOTTOM){x = x + 1; y = y; edge = x; edgeZ = 9; }//edge < edgeZ --- x < 10

        //стреляю сперва в право
        if (edge <= edgeZ && field[x][y] == ' ') {//стреляю по второй палубе

            result = seaBattle.fire(x, y);
            //если попал но не убит
            markFireTest(x, y, result);//визуализация-- заполняет массив результатами стрельбы

            if (result == FireResult.HIT) {//на 3 палубу если н попал но не убил

                if (direction == RIGHT){x = x; y = y + 1; edge = y; edgeZ = 9; }//edge < edgeZ --- y < 10
                if (direction == LEFT){x = x; y = y - 1; edge = 0; edgeZ = y; }//edge < edgeZ --- 0 < y
                if (direction == TOP){x = x - 1; y = y; edge = 0; edgeZ = x; }//edge < edgeZ --- 0 < x
                if (direction == BOTTOM){x = x + 1; y = y; edge = x; edgeZ = 9; }//edge < edgeZ --- x < 10

                if (edge <= edgeZ && field[x][y] == ' ') {//стреляю по третьей палубе

                    result = seaBattle.fire(x, y);
                    markFireTest(x, y, result);//визуализация-- заполняет массив результатами стрельбы

                    if (result == FireResult.HIT) {//на 4 палубу если н попал но не убил

                        if (direction == RIGHT){x = x; y = y + 1; edge = y; edgeZ = 9; }//edge < edgeZ --- y < 10
                        if (direction == LEFT){x = x; y = y - 1; edge = 0; edgeZ = y; }//edge < edgeZ --- 0 < y
                        if (direction == TOP){x = x - 1; y = y; edge = 0; edgeZ = x; }//edge < edgeZ --- 0 < x
                        if (direction == BOTTOM){x = x + 1; y = y; edge = x; edgeZ = 9; }//edge < edgeZ --- x < 10

                        if (edge <= edgeZ && field[x][y] == ' ') {//стреляю по четвертой палубе

                            result = seaBattle.fire(x, y);
                            markFireTest(x, y, result);//визуализация-- заполняет массив результатами стрельбы

                        }//палуба 4

                    }//на 4 палубу если н попал но не убил  */

                }//стреляю по третьей палубе

            }//на 3 палубу если н попал но не убил
        }
        if ((edge <= edgeZ && result == FireResult.HIT && field[x][y] != ' ') ||
                (edge > edgeZ && result == FireResult.HIT) || (result == FireResult.MISS)) {
            //если не промахнулся но занято или уже граница поля//если промахнулся и не убит

            if (direction == RIGHT){ hits(xX, yY, result, LEFT);}
            if (direction == LEFT){ hits(xX, yY, result, TOP);}
            if (direction == TOP){ hits(xX, yY, result, BOTTOM);}
        }

        if (result == FireResult.DESTROYED ) {//если ПОПАЛ И УБИТ
            //обработать обводку убитого
            markDestroyed();//точки которые нет смысла стрелять
            nz++;//считаю для теста сколько раз пришло УБИЛ
            coynt++;
        }
    }
    //алгоритм вывода
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
        while ( y < seaBattle.getSizeY()) {
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
            while (x < seaBattle.getSizeX()) {


                //System.out.println("hits - " + hits);
                if (field[x][y] == ' ') {//усли ячейка в моем поле пуста то стреляем

                    System.out.println("coynt - "+coynt);
                    FireResult fireResult = seaBattle.fire(x, y);//выстрел

                    if (fireResult == FireResult.DESTROYED ){
                        coynt++;

                    }

                    //System.out.println("DESTROYED - " + FireResult.DESTROYED);
                    markFire(x, y, fireResult);//визуализация-- заполняет мое поле результатами стрельбы

                    if (fireResult == FireResult.DESTROYED ) {//если ПОПАЛ И УБИТ
                        //обработать обводку убитого
                        markDestroyed();//точки которые нет смысла стрелять
                        //nz++;//считаю для теста сколько раз пришло УБИЛ

                    }
                    if (fireResult != FireResult.MISS) {//если не промахнулись

                        noMiss(x, y, fireResult);// обрабатываем обводку, поиск палубы еще
                        // если ранен ++ считаем выстрелы попаданий

                    }
                    print ();//поле наглядно
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

        statistic();
//        System.out.println("hits- попал сразу - " + hits1);
//        System.out.println("hits2- попал при дальнейшем поиске - " + hits2);
        System.out.println("попаданий - " + hitses);
        System.out.println("X - " + popal);
        System.out.println("Z - " + nascel);
        System.out.println("* - " + mimo);
        System.out.println("- - " + minus);
        System.out.println("ИТОГО ВЫСТРЕЛОВ - " + (popal + mimo + minus + nascel));
//        int mimo = 0; //*
//        int popal = 0; //X
//        int nascel = 0; //Z
//        int minus = 0; //-
//


        if(hitses > 20){
            nz++;
        }

        if (nascel == popal){
            sravn++;
        }


    }




    // функция для отладки
    public static void main(String[] args) {

        System.out.println("Sea battle");
        SeaBattle seaBattle = new SeaBattle(true);
        new SeaBattleAlg().battleAlgorithm(seaBattle);

        System.out.println(seaBattle.getResult());
        System.out.println(" сколько раз пришло убит " + nz);

 /*       int nzz = 0;
        double res = 0;
        for (int i=0;  i<1000; i++){
            SeaBattle seaBattle = new SeaBattle();
            new SeaBattleAlg().battleAlgorithm(seaBattle);
            res += seaBattle.getResult();
            if (seaBattle.getResult() == 0){
                nzz++;
            }
            System.out.println("результат - " + seaBattle.getResult());
        }


        System.out.println(res/1000);
        System.out.println("меньше 20 - " + nzz);
        System.out.println("сколько равных x = z - " + sravn);
*/
    }




}



