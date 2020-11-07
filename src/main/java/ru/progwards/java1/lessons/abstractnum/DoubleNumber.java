package ru.progwards.java1.lessons.abstractnum;

public class DoubleNumber extends Number {
    double num;

    public DoubleNumber(double num){
        this.num = num;
    }

    @Override
    public Number mul(Number num){
        DoubleNumber numi =(DoubleNumber)num;
        return new DoubleNumber(this.num * numi.num);
    }

    @Override
    public Number div(Number num){
//        DoubleNumber numi =(DoubleNumber)num;
//        return new DoubleNumber(this.num / numi.num);
        String str = num.toString();
        DoubleNumber numi = (DoubleNumber)this.newNumber(str);
        return new DoubleNumber(this.num / numi.num);
    }
    @Override
    public Number newNumber(String strNum){
        return new DoubleNumber(Double.parseDouble(strNum));
    }

    @Override
    public String toString(){
        return String.valueOf(num);
    }


}
