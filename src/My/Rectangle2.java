package My;
//T8.1 Тест блока 1
import java.util.Objects;

public class Rectangle2 {
    private double a;
    private double b;

    public Rectangle2(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double area() {

        return a*b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle2 that = (Rectangle2) o;
        return Double.compare(that.a*that.b, a*b) == 0 &&
                Double.compare(that.a*that.b, a*b) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    public static void main(String[] args) {

        System.out.println(new Rectangle2(2,2).equals(new Rectangle2(2, 2)));
    }
}
