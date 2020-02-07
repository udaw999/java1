package My;

public class TestClass {


    static double fractional(double num){
        //получаем остаток
        return num % 1;

    }
    static int addAsStrings(int n1, int n2){
        //склеиваем два int
        int z = Integer.parseInt(String.valueOf(n1) + String.valueOf(n2));
        return  z;
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
        long sum = 1;

        while (i < n) {

            i++;
            sum = sum * i;

        }
        return sum;
    }


//   public static boolean containsDigitt(int number, int digit){
//        while (0 < number ){
//        if ( number % 10 == digit){
//            return true;
//        } else {
//            return false;
//
//        }
//
//            number =  number / 10;
//    }
//
//
//    }

    public static void main(String[] args){

        //System.out.println(containsDigitt(365,5));


        double d = 99999999999999999999.999999999999999999999999999999999;

        System.out.println((float)d);

        System.out.println(addAsStrings(237, 5698));
        System.out.println(fractional(1.53));
        daysStrings();
        System.out.println(textGrade(101));
        System.out.println(factorial(0));
    }
}
