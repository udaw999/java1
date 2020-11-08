package ru.progwards.java1.lessons.queues;

public class Calculate{
    //2.2*(3+12.1), используя класс StackCalc


    public static double calculation1(){
        StackCalc.arrayDeque.push(12.1);
        StackCalc.arrayDeque.push((double) 3);
        StackCalc.arrayDeque.push(2.2);
        StackCalc.add();
        StackCalc.mul();
        return StackCalc.arrayDeque.pop();
    }// возвращающую результат вычисления следующей формулы:


    public static double calculation2(){
        StackCalc.arrayDeque.push(3.33);
        StackCalc.arrayDeque.push((double) 19);
        StackCalc.sub();//(19-3.33)
        double num = StackCalc.arrayDeque.pop();//15.67

         StackCalc.arrayDeque.push(9.2);
        StackCalc.arrayDeque.push(13.001);
        StackCalc.sub();//13.001-9.2
        StackCalc.arrayDeque.push((double) 2);
        StackCalc.mul();//2*(13.001-9.2)
        StackCalc.arrayDeque.push((double) 87);
        StackCalc.add();//(87+2*(13.001-9.2))
        //94.602
        StackCalc.arrayDeque.push(num);
        StackCalc.mul();//(55.6-12.1)+(19-3.33)*(87+2*(13.001-9.2)) == 1482.41334

        num = StackCalc.arrayDeque.pop();//1482.41334




        StackCalc.arrayDeque.push(737.22);
        StackCalc.arrayDeque.push((double) 24);
        StackCalc.add();//(737.22+24) ==761.22

        double num0 = StackCalc.arrayDeque.pop();

        StackCalc.arrayDeque.push(12.1);
        StackCalc.arrayDeque.push(55.6);
        StackCalc.sub();//(55.6-12.1) == 43.5

        StackCalc.arrayDeque.push(num0);

        StackCalc.div();//(737.22+24)/(55.6-12.1)== 17.499310344827588

        StackCalc.arrayDeque.push(num);

        StackCalc.add(); //(737.22+24)/(55.6-12.1)+(19-3.33)*(87+2*(13.001-9.2)) == 1499.9126503448276

        return StackCalc.arrayDeque.pop();
        //return num;
    }
    public static void main(String[] args) {

        System.out.println(calculation2());
        System.out.println(StackCalc.arrayDeque);
    }
}
