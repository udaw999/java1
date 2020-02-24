package ru.progwards.java1.lessons.interfaces;
//Еда
public class Food implements CompareWeight{
    //вес
    private int weight;

    public  Food (int weight){
        this.weight = weight;
    }
    //значение веса
    public int getWeight(){
        return weight;
    }

    @Override
    public CompareResult compareWeight(CompareWeight smthHasWeigt) {
        Food another = (Food) smthHasWeigt;//создаем переменную со
        // вторым весом( не понял способа, ТО ЧТО ПОСЛЕ = )

        if (this.getWeight() < another.getWeight()) {
            return CompareResult.LESS;
        }
        if (this.getWeight()> another.getWeight()) {
            return CompareResult.GREATER;
        }
        return CompareResult.EQUAL;
    }


//    public static void sort(int[] a){
//
//        for (int i=0; i<a.length ; i++){
//            for (int j=i+1 ; j<a.length; j++){
//                if(a[i] > a[j]){
//                    int aj = a[j];
//                    int ai = a[i];
//                    a[i] = aj;
//                    a[j] = ai;
//                }
//            }
//            //for (int value : a) {           System.out.println("Элемент " + value);       }
//        }
//
//    }

    public static void sort(CompareWeight[] a){
        for (int i=0; i<a.length ; i++){
            for (int j=i+1 ; j<a.length; j++){
                if(a[i].compareWeight(a[j]) == CompareResult.GREATER){
                    CompareWeight aj = a[j];
                    CompareWeight ai = a[i];
                    a[i] = aj;
                    a[j] = ai;
                }
            }
            //for (int value : a) {           System.out.println("Элемент " + value);       }
        }
    }

    public static void main(String[] args) {
        Food food = new Food(500);
        Food food1 = new Food(500);


        System.out.println(food.compareWeight(food1));
    }
}
