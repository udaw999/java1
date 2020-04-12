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
    //         0|.|.|.|.|.|.|.|X|.|.|
    //         1|.|.|.|.|.|X|.|.|.|.|
    //         2|X|X|.|.|.|.|.|.|.|.|
    //         3|.|.|.|.|.|.|.|X|X|X|
    //         4|.|.|.|.|X|.|.|.|.|.|
    //         5|.|.|.|.|X|.|.|Х|.|.|
    //         6|.|.|.|.|.|.|.|Х|.|X|
    //         7|X|.|X|.|.|.|.|Х|.|X|
    //         8|X|.|.|.|.|.|.|X|.|.|
    //         9|X|.|.|.|X|.|.|.|.|.|
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
    //счетчик успешных выстрелов
    void hitsPlusPlus(){

        hits++;
    }
    //метка убил или мимо
    void markFire(int x, int y, FireResult result){
        if (result != FireResult.MISS){
            field[x][y] = 'X';
           // hitsPlusPlus();//счетчик попаданий
            hits++;
            hits1++;
        } else {
            field[x][y] = '*';
        }
    }
    //алгоритм вывода
    public void battleAlgorithm(SeaBattle seaBattle) {
        // пример алгоритма:
        // стрельба по всем квадратам поля полным перебором
        init(seaBattle);//заполняемый массив результатами прострела


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

                if (field[x][y] == ' ') {//усли ячейка в моем поле пуста то стреляем


                    FireResult fireResult = seaBattle.fire(x, y);//выстрел
                    //System.out.println("DESTROYED - " + FireResult.DESTROYED);
                    markFire(x, y, fireResult);//визуализация-- заполняет мое поле результатами стрельбы
                    if (fireResult != FireResult.MISS) {//если не промахнулись

                        //noMiss(x, y, fireResult);// обрабатываем обводку, поиск палубы еще
                        // если ранен ++ считаем выстрелы попаданий

                    }
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



    }

    // функция для отладки
    public static void main(String[] args) {
        System.out.println("Sea battle");
        SeaBattle seaBattle = new SeaBattle(true);
        new SeaBattleAlg().battleAlgorithm(seaBattle);
        System.out.println(seaBattle.getResult());
    }
}

