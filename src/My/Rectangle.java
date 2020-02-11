package My;
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

    }
}
