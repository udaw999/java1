package ru.progwards.java1.lessons.test.test_13;

import java.util.Comparator;
import java.util.TreeSet;

public class User {
    public Integer id;
    public String name;

    User (Integer id, String name) {
        this.id = id;
        this.name = name;
    }

//test 3
    /*Создайте метод с сигнатурой public TreeSet<User> createSet() который создает и возвращает TreeSet так,
     что бы пользователи оказались упорядочены по убыванию id*/
    public static TreeSet<User> createSet(){

        TreeSet<User> treeSet = new TreeSet<>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(Math.abs(o2.id), Math.abs(o1.id));
            }
        });

        return treeSet;
    }
//-----------------
    @Override
    public String toString() {// переопределяется
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        User user = new User(1,"VASILIY");
        User user1 = new User(3,"KOLYA");
        User user2 = new User(4,"VASILIY2");
        User user3 = new User(6,"KOLYA2");

        TreeSet<User> treeSet = new TreeSet<>(createSet());//так создается и наполняется
        treeSet.add(user);
        treeSet.add(user1);
        treeSet.add(user2);
        treeSet.add(user3);

        System.out.println(treeSet);//отображается
    }
}
