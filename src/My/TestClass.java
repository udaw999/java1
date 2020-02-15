package My;

import java.util.Arrays;

public class TestClass {


    static double fractional(double num){
        //получаем остаток
        return num % 1;

    }

    // ниже еще один метод . этот тоже правильный
//    static int addAsStrings(int n1, int n2){
//        //склеиваем два int
//        int z = Integer.parseInt(String.valueOf(n1) + String.valueOf(n2));
//        return  z;
//    }
    //склеиваем два int
    static int addAsStrings(int n1, int n2){

        String str1 = Integer. toString(n1);

        String str2 = Integer. toString(n2);

        str1 += str2;

        int n3 = Integer. parseInt(str1);

        return n3;

    }

    public static void  daysStrings(){
        int x = 30;
        String daysString;
        String remainderInfo = "Число оканчивается на ";

        int remainder100 = x % 100;

        if (remainder100 >= 11 && remainder100 <= 20) {
            daysString = "дней";
            remainderInfo += remainder100;

        } else {

            int remainder10 = x % 10;

            if (remainder10 == 1)
                daysString = "день";
            else if (remainder10 >= 2 && remainder10 <= 4)
                daysString = "дня";
            else
                daysString = "дней";

            remainderInfo += remainder10;
        }

        System.out.println("Событие наступит через " + x + " " + daysString);
        System.out.println(remainderInfo);
    }

    static String textGrade(int grade){
        String text;
        if ( grade == 0)
            text = "не оценено";
        else if (grade >= 1 && grade <= 20)
            text = "очень плохо";
        else if (grade >= 21 && grade <= 40)
            text = "плохо";
        else if (grade >= 41 && grade <= 60)
            text = "удовлетворительно";
        else if (grade >= 61 && grade <= 80)
            text = "хорошо";
        else if (grade >= 81 && grade <= 100)
            text = "отлично";
        else
            text = "не определено";

    return text;

    }
//метод, вычисляющий факториал числа - n
    //факториал n это произведение всех чисел от 1 до n
    static long factorial(long n){
        long i = 1;
        long sum1 = 1;

        while (i < n) {

            i++;
            sum1 = sum1 * i;

        }
        return sum1;
    }

// ошибка у меня тут
   public static boolean containsDigitt(int number, int digit){
        int i = 0;
        while (0 < number ){
        if ( number % 10 == digit)
            return true;

        i++;

            number =  number / 10;
    }
//       for (int i = 0; 0 < number ; i++){
//           if ( number % 10 == digit)
//               return true;
//
//           number =  number / 10;
//       }
       return false;


    }
//в кансоль тест проверки кинуть без static Т6
    public static int sumArrayItems(int[] a){
        int sum = 0;

        for (int i=0 ; i < a.length ; i++){
            sum = sum + a[i];
        }
        return sum;
    }
    //в кансоль тест проверки кинуть без static
//определяем большее число в масиве Т6
    public static int arrayMax(int[] a){
        if (a.length>0){
         Arrays.sort(a);
        return a[a.length-1];
        } else {
            return 0;
        }
    }
    enum Grade{VERYBAD, BAD, SATISFACTORILY, GOOD, EXCELLENT, NOTDEFINED}
    static Grade intToGrade(int grade){
        switch (grade){
            case 1: return Grade.VERYBAD;
            case 2: return Grade.BAD;
            case 3: return Grade.SATISFACTORILY;
            case 4: return Grade.GOOD;
            case 5: return Grade.EXCELLENT;
        }
        return Grade.NOTDEFINED;
    }

    public static void main(String[] args){

        System.out.println(intToGrade(14));

        int[] a2 = {1,37,25,68,2,56};
        System.out.print("определяем большее число в масиве  ");
        System.out.println(arrayMax(a2));
        System.out.println();


        System.out.print("Сумма чисел в масиве(сложение) ");
        System.out.println(sumArrayItems(a2));
        System.out.println();

        System.out.print("Есть ли цифра в числе ");
        System.out.println(containsDigitt(365,3));
        System.out.println();

        int a1 = addAsStrings(1,2);
        System.out.print("Склеиваем 2 числа ");
        System.out.println(a1);
        System.out.println(addAsStrings(237, 5698));
        System.out.println();


        double d = 99999999999999999999.999999999999999999999999999999999;

        System.out.println((float)d);

        System.out.print("Получаем остаток ");
        System.out.println(fractional(1.53));
        System.out.println();


        System.out.print("день,дни,дня ");
        daysStrings();
        System.out.println();

        System.out.print("ОЦЕНКА");
        System.out.println(textGrade(101));
        System.out.println();


        System.out.print("произведение всех чисел от 1 до n  ---  ");
        System.out.println(factorial(10));
    }
}
