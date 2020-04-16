package ru.progwards.java1.SeaBattle.urda1975;

import ru.progwards.java1.SeaBattle.SeaBattle;

import java.util.Arrays;
import java.util.Random;

public class SeaBattleAlgGuru {
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

    private static final int MINUS = 0b01; // стреляем в уменьшение координаьы
    private static final int PLUS = 0b10; // стреляем в увеличение координаты

    public static boolean printField = false; // печатать поле на каждом шаге
    char[][] field; // поле боя. ' ' - пустая ячейка, '*' - выстрел, 'X' - попали в корабль, '.' - отметили, что нет смысла стрелять
    SeaBattle seaBattle; // seaBattle, что бы не таскать его везде параметром
    int hits; // общее количество попаданий
    int direction; // направление стрельбы - PLUS | MINUS

    // процедура инициализации, используется вместо конструктора
    void init(SeaBattle seaBattle) {
        hits = 0;
        this.seaBattle = seaBattle;
        field = new char[seaBattle.getSizeX()][seaBattle.getSizeY()];
        for (int x = 0; x < seaBattle.getSizeX(); x++)
            Arrays.fill(field[x], ' ');
    }

    // печать поля для отладки алгоритмов
    void print(boolean doPrint) {
        if (!doPrint)
            return;
        for (int y = 0; y < seaBattle.getSizeY(); y++) {
            String str = "|";
            for (int x = 0; x < seaBattle.getSizeX(); x++) {
                str += field[x][y] + "|";
            }
            System.out.println(str);
        }
        System.out.println("----------------------");
    }

    // добить корабль горизонтально; возвращает true если корабль убит
    boolean killHorisontal(int x, int y) {
        int i = 1;
        boolean destroyed = false;
        direction = PLUS | MINUS;
        do {
            if ((direction&MINUS) != 0)
                destroyed = checkHit(fire(x-i, y), MINUS);
            if ((direction&PLUS) != 0)
                destroyed = checkHit(fire(x+i, y), PLUS);
            i++;
        } while(direction != 0);
        return destroyed;
    }

    // добить корабль вертикально; возвращает true если корабль убит
    boolean killVertical(int x, int y) {
        int i = 1;
        boolean destroyed = false;
        direction = PLUS | MINUS;
        do {
            if ((direction&MINUS) != 0)
                destroyed = checkHit(fire(x, y-i), MINUS);
            if ((direction&PLUS) != 0)
                destroyed = checkHit(fire(x, y+i), PLUS);
            i++;
        } while(direction != 0);
        return destroyed;
    }

    // добить корабль, вызывается только после попадания
    void killShip(int x, int y) {
        boolean destroyed = killHorisontal(x, y);
        if (!destroyed)
            killVertical(x, y);
    }

    // проверить попадание при добивании; возвращает true если корабль убит
    boolean checkHit(SeaBattle.FireResult result, int fireDirection) {
        switch(result) {
            case DESTROYED :
                direction = 0;
                return true;
            case HIT:
                return false;
            case MISS:
                direction &= ~fireDirection;
                return false;
        }
        return false;
    }

    // пометить ячейку корабль или мимо
    void markFire(int x, int y, SeaBattle.FireResult result) {
        if (result != SeaBattle.FireResult.MISS)
            field[x][y] = 'X';
        else
            field[x][y] = '*';
    }

    // пометить ячейку как не не имеющую смысл для стрельбы
    void markDot(int x, int y) {
        if(x<0 || y<0 || x>=seaBattle.getSizeX() || y>=seaBattle.getSizeY())
            return;
        if (field[x][y] == ' ')
            field[x][y] = '.';
    }

    // пометить попадание в корабль "по кругу"
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

    // пометить вокруг убитых кораблей
    void markDestroyed() {
        for (int y = 0; y < seaBattle.getSizeY(); y++) {
            for (int x = 0; x < seaBattle.getSizeX(); x++) {
                if (field[x][y] == 'X')
                    markHit(x, y);
            }
        }
    }

    // увеличить счетчик попаданий
    void countHits() {
        hits++;
    }

    // "интеллектуальный" выстрел - проверяет попадание в границы поля и что имеет смысл стрелять в ячейку
    SeaBattle.FireResult fire(int x, int y) {
        if(x<0 || y<0 || x>=seaBattle.getSizeX() || y>=seaBattle.getSizeY() ||
                hits >= 20 || field[x][y] != ' ')
            return SeaBattle.FireResult.MISS;

        SeaBattle.FireResult result = seaBattle.fire(x, y);
        markFire(x, y, result);
        if (result != SeaBattle.FireResult.MISS)
            countHits();
        if (result == SeaBattle.FireResult.DESTROYED)
            markDestroyed();

        print(printField);
        return result;
    }

    // выстрел с добиванием корабля. Используется из основного алгоритма для избегания рекурсии
    SeaBattle.FireResult fireAndKill(int x, int y) {
        SeaBattle.FireResult result = fire(x, y);
        if (result == SeaBattle.FireResult.HIT) {
            killShip(x, y);
        }
        return result;
    }

    Random random = new Random();
    int getRandom() {
        double d = random.nextDouble();
        return (int)Math.floor(d*10);
    }

    // пример алгоритма:
    // стрельба по всем квадратам поля полным перебором
    void algorithm1() {
        for (int y = 0; y < seaBattle.getSizeY(); y++) {
            for (int x = 0; x < seaBattle.getSizeX(); x++) {
                fireAndKill(x, y);
            }
        }
    }

    void algorithm2() {
        while(hits < 20)
            fireAndKill(getRandom(), getRandom());
    }

    void stepFire(int offset) {
        for (int y = 0; y < seaBattle.getSizeY(); y++) {
            for (int x = y+offset; x < seaBattle.getSizeX(); x+=4)
                fireAndKill(x, y);

            for (int x = y-offset; x >= 0; x-=4)
                fireAndKill(x, y);
        }
    }

    void algorithm3() {
        stepFire(3);
        stepFire(1);
        stepFire(0);
        stepFire(2);
    }

    public void battleAlgorithm(SeaBattle seaBattle) {
        init(seaBattle);
        algorithm3();
    }

    static void fullTest() {
        SeaBattleAlgGuru.printField = false;
        double result = 0;
        SeaBattleAlgGuru alg = new SeaBattleAlgGuru();
        for(int i=0; i<1000; i++) {
            SeaBattle seaBattle = new SeaBattle();
            alg.battleAlgorithm(seaBattle);
            result += seaBattle.getResult();
        }
        System.out.println(result/1000);
    }

    static void oneTest() {
        SeaBattleAlgGuru.printField = true;
        SeaBattle seaBattle = new SeaBattle(true);
        new SeaBattleAlgGuru().battleAlgorithm(seaBattle);
        System.out.println(seaBattle.getResult());
    }

    // функция для отладки
    public static void main(String[] args) {
        System.out.println("Sea battle");
        oneTest();
    }
}


