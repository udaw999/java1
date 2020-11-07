package ru.progwards.java1.lessons.test.test_13;
/*Напишите метод с сигнатурой String figDetect(Figure fig), который
 - для квадрата возвращает "Сторона квадрата "+.side
- для круга возвращает "Диаметр круга "+.diameter
- для всех остальных классов "Неизвестная фигура"*/
public class Figure {

  public static  String figDetect(Figure fig){

    if ( fig instanceof Square){
      return "Сторона квадрата " + ((Square) fig).getSide();
    } else if (fig instanceof Round) {
      return "Диаметр круга " + ((Round) fig).getDiameter();
    } else {
      return "Неизвестная фигура";
    }

  }
    public static void main(String[] args) {
      Figure round = new Round(23.0);
      Figure square = new Square(50.5);

      System.out.println(figDetect(round));
      System.out.println(figDetect(square));
    }
}
