package testPr;

import java.util.ArrayList;
import java.util.List;

public class Test3 {

    public static  <T> void swap(List<T> list, int a, int b){
        T aN = list.get(a);
        list.set(a,list.get(b));
        list.set(b,aN);
    }
    enum CompareResult {LESS, EQUAL, GREATER};

    private static <T extends Comparable<T>> CompareResult  compare(T a, T b){
        System.out.println(a.compareTo(b));
        if (a.compareTo(b) < 0)
            return CompareResult.LESS;
        else if (a.compareTo(b) == 0)
            return CompareResult.EQUAL;
        else
            return CompareResult.GREATER;
    }

    public static <T> ArrayList from(T[] aray){
        ArrayList<T> list = new ArrayList<>();
            for (int i = 0; i < aray.length ; i++)
                list.add(i,aray[i]);
        return list;
    }
    public static void main(String[] args) {
        System.out.println(compare("Петя", "Вася"));

    }
}
