package testPr;

public class Test10 {
    static int N = 13;
    static final double A = 0.6180339887;

    static int hash(int k) {
        double d = A*k;
        return (int)(N*(d-Math.floor(d)));
    }

    static int RSHash (String str)
    {
        int b = 37;
        int a = 63;
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = hash * a + str.charAt(i);
            a = a * b;
        }
        if(hash < 0)
            hash = hash * (-1);
        return hash;
    }

    public static void main(String[] args) {
//        System.out.println(hash(25));
//        System.out.println(hash(26));
//        System.out.println(hash(27));
        System.out.println(RSHash("Маша"));
        System.out.println(RSHash("Петя"));
        System.out.println(RSHash("Вася"));
    }
}