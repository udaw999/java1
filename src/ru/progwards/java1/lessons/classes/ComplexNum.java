package ru.progwards.java1.lessons.classes;

public class ComplexNum {
    int a;
    int b;

    public ComplexNum(int a, int b){
        this.a = a;
        this.b = b;
    }



    public String  add(ComplexNum num){
        this.a = a + num.a;
        this.b = b + num.b;
        return a + "+" + b + "i";
    }
    public String  sub(ComplexNum num){
        this.a = a - num.a;
        this.b = b - num.b;
        return a + "+" + b + "i";
    }
    public String  mul(ComplexNum num){
        this.a = a * num.a - b * num.b;
        this.b = b * num.a + a * num.b ;
        return a + "+" + b + "i";
    }
    public String  div(ComplexNum num){
        this.a = (a * num.a + b * num.b) / (num.a * num.a + num.b * num.b);
        this.b = ((b * num.a - a * num.b) / (num.a * num.a + num.b * num.b)) ;
        return a + "+" + b + "i";
    }

    public String toString(){
        return a + "+" + b + "i";
    }
    public static void main(String[] args) {
        ComplexNum complexNum = new ComplexNum(1, 56);
        ComplexNum complexNum2 = new ComplexNum(4, 58);
        System.out.println(complexNum.toString());
        System.out.println(complexNum.add(complexNum2));
        System.out.println(complexNum.sub(complexNum2));
        System.out.println(complexNum.mul(complexNum2));
        System.out.println(complexNum.div(complexNum2));
    }
}
