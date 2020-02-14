package My;

import java.util.Arrays;

/* реализуйте метод compareTo с сигнатурой
public int compareTo(Rectangle anRectangle) который должен сравнивать прямоугольники по величине их площади.
При сравнении a.compareTo(b) метод должен возвращать
a   > b : 1
a == b : 0
a   < b : -1

Например

прямоугольник 2x2  >  прямоугольника 1x1 - результат 1
прямоугольник 2x3 == прямоугольнику 3x2 - результат 0
прямоугольник 2x2  <  прямоугольника 3x3 - результат -1 */
public class Rectangle {
    private double a;
    private double b;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double area() {

        return a*b;
    }

    public int compareTo(Rectangle anRectangle){

        if(this.area() > anRectangle.area()){ return 1;}
        else if (this.area() < anRectangle.area()){ return -1;}
        else {return 0;}
    }

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(2,2);
        Rectangle rectangle12 = new Rectangle(3,3);
        System.out.println(rectangle.area());
        System.out.println(rectangle12.area());
        System.out.println(rectangle.compareTo(rectangle12));

//        int[] a1 = {12, 5, 0, 58, 36};
//        int[] a2 = Arrays.copyOf(a1, a1.length);
  //      a2[2] = 0;
  //      System.out.println(Arrays.equals(a1, a2));

//        int[] a1 = {1, 1, 1, 1, 3};
//        int[] a2 = new int[5];
//        Arrays.fill(a2, 1);
//        a2[4] = 3;
//        System.out.println(Arrays.equals(a1, a2));

        int[] a1 = {12, 5, 0, 58, 36};
        int[] a2 = Arrays.copyOf(a1, a1.length);
        Arrays.sort(a2);
        System.out.println(Arrays.equals(a1, a2));


    }
}
