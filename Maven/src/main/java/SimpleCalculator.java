import java.util.Scanner;

public class SimpleCalculator  {

    public int sum(int val1, int val2) {
        long result = (long) val1 + val2;
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
            throw new ArithmeticException();
        return val1 + val2;
    }

    public int diff(int val1, int val2) {
        long result = (long) val1 - val2;
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
            throw new ArithmeticException();
        return val1 - val2;
    }

    public int mult(int val1, int val2) {
        long result = (long) val1 * val2;
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
            throw new ArithmeticException();
        return val1 * val2;
    }

    public int div(int val1, int val2) {
        long result = (long) val1 / val2;
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE || val2 == 0)
            throw new ArithmeticException();
        return val1 / val2;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SimpleCalculator simpleCalculator = new SimpleCalculator();

        System.out.println("Давайте проверим мой калькулятор");
        System.out.println("Попробуем сложить два числа");
        System.out.println("Наберите первое число и нажмите Eyter");
        int i = sc.nextInt();
        System.out.println("Наберите второе число и нажмите Eyter");
        int g = sc.nextInt();
        System.out.println("Получили результат сложения - " + simpleCalculator.sum(i,g));
        System.out.println();
        System.out.println("Проверим вычитание");
        System.out.println("Наберите первое число и нажмите Eyter");
        i = sc.nextInt();
        System.out.println("Наберите второе число и нажмите Eyter");
        g = sc.nextInt();
        System.out.println("Получили результат вычитания - " + simpleCalculator.diff(i,g));
        System.out.println();
        System.out.println("Проверим умножение");
        System.out.println("Наберите первое число и нажмите Eyter");
        i = sc.nextInt();
        System.out.println("Наберите второе число и нажмите Eyter");
        g = sc.nextInt();
        System.out.println("Получили результат умножения - " + simpleCalculator.mult(i,g));
        System.out.println();
        System.out.println("Проверим деление");
        System.out.println("Наберите первое число и нажмите Eyter");
        i = sc.nextInt();
        System.out.println("Наберите второе число и нажмите Eyter");
        g = sc.nextInt();
        System.out.println("Получили результат вычитания - " + simpleCalculator.div(i,g));
    }

}


