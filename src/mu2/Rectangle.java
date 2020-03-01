package mu2;

import java.math.BigDecimal;

public class Rectangle {

    Rectangle(BigDecimal a, BigDecimal b) {
        this.a = a;
        this.b = b;
    }
    public BigDecimal a;
    public BigDecimal b;

   public static boolean rectCompare(Rectangle r1, Rectangle r2){
       if( r1.a.multiply(r1.b).compareTo(r2.a.multiply(r2.b)) == 0) {
           return true;
       } else {
           return false;
       }
    }


    public static void main(String[] args) {
//       Rectangle r1 = new Rectangle( 2, 3);
//        Rectangle r2 = new Rectangle( 3, 2);
//        System.out.println(rectCompare(r1,r2));

    }
}
