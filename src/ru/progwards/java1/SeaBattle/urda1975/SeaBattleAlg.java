package ru.progwards.java1.SeaBattle.urda1975;

import ru.progwards.java1.SeaBattle.SeaBattle;
import ru.progwards.java1.SeaBattle.SeaBattle.FireResult;

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

    /*    public void battleAlgorithm(SeaBattle seaBattle) {
            // пример алгоритма:
            // стрельба по всем квадратам поля полным перебором
            int hits = 0;
            for (int y = 0; y < seaBattle.getSizeX(); y++) {
                for (int x = 0; x < seaBattle.getSizeY(); x++) {
                    SeaBattle.FireResult fireResult = seaBattle.fire(x, y);
                    if (fireResult != FireResult.MISS)
                        hits++;
                    if (hits >= 20)
                        return;
                }
            }
        }

    ====================*/
    int hits;
    public void battleAlgorithm(SeaBattle seaBattle) {
        hits = 0;
        // пример алгоритма:
        // стрельба по всем квадратам поля полным перебором
        //init(seaBattle);//заполняемый массив результатами прострела


        prostrel(3,seaBattle, 0);
        prostrel(1,seaBattle, 0);
        prostrel(0,seaBattle, 0);
        prostrel(2,seaBattle, 0);

    }

    public  void prostrel( int ofset, SeaBattle seaBattle, int dop){
        for (int y = 0; y < seaBattle.getSizeX(); y++) {
            for (int x = ofset - y + dop; x < seaBattle.getSizeY(); x += 4) {
                if (x == 0) {dop += 4; }

 /*             if (coynt==10)
                    return;
                if (field[x][y] == ' ') {//усли ячейка в моем поле пуста то стреляем
                    SeaBattle.FireResult fireResult = seaBattle.fire(x, y);
                    markFire(x, y, fireResult);//визуализация-- заполняет мое поле результатами стрельбы

                    if (fireResult == FireResult.DESTROYED ) {//если ПОПАЛ И УБИТ
                        //обработать обводку убитого
                        markDestroyed();//точки которые нет смысла стрелять
                        //nz++;//считаю для теста сколько раз пришло УБИЛ

                        coynt++;

                    }
                    if (fireResult != FireResult.MISS) {//если не промахнулись

                        noMiss(x, y, fireResult);// обрабатываем обводку, поиск палубы еще
                        // если ранен ++ считаем выстрелы попаданий

                    }
        /*          }*/


               // print();//поле наглядно
                SeaBattle.FireResult fireResult = seaBattle.fire(x, y);
                if (fireResult != FireResult.MISS)
                    hits++;
                if (hits >= 20)
                    return;
            }
        }
    }

/**/
    // функция для отладки
    public static void main(String[] args) {
        System.out.println("Sea battle");
        SeaBattle seaBattle = new SeaBattle(true);
        new SeaBattleAlg().battleAlgorithm(seaBattle);
        System.out.println(seaBattle.getResult());
    }
}



