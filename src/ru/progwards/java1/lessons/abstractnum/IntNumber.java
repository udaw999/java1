package ru.progwards.java1.lessons.abstractnum;

public class IntNumber extends Number {
    int num;

    public IntNumber(int num){
        this.num = num;
    }
    @Override
    public Number mul(Number num){
        //для проверки что приходит сюда используем такой способ
        //System.out.println(num.getClass().getName());
        //видим что приходит IntNumber
        //второй номер переменной
        IntNumber numi =(IntNumber)num;
        return new IntNumber(this.num * numi.num);
    }

    @Override
    public Number div(Number num){
//        IntNumber numi =(IntNumber)num;
//        return new IntNumber(this.num / numi.num);
        String str = num.toString();
        IntNumber numi = (IntNumber)this.newNumber(str);
        return new IntNumber(this.num / numi.num);
    }
    @Override
    public Number newNumber(String strNum){
        return new IntNumber(Integer.parseInt(strNum));
    }

    @Override
    public String toString(){
        return String.valueOf(num);
    }
}
