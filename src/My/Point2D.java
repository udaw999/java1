package My;

public class Point2D {

    int x;
    int y;

    Point2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return x + "," + y;
    }

    public static void main(String[] args) {
        Point2D point2D = new Point2D(23,12);
        System.out.println(point2D.toString());
        Point3D point3D = new Point3D(23,12,37);
        System.out.println(point3D.toString());
    }

}
