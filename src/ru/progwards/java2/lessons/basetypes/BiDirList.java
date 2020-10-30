package ru.progwards.java2.lessons.basetypes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BiDirList<T> {


    class ListItem<T> {

        private T item;
        private ListItem<T> next;
        private ListItem<T> previous;

        ListItem(T item) {
            this.item = item;
        }


        T getItem() {
            return item;
        }


        public void setNext(ListItem<T> next) {
            this.next = next;
        }

        public ListItem<T> getNext() {
            return next;
        }

        public void setPrevious(ListItem<T> previous) {
            this.previous = previous;
        }

        public ListItem<T> getPrevious() {
            return previous;
        }
    }

    private ListItem<T> head;
    private ListItem<T> tail;
    private int count = 0;

    ListItem<T> getHead() {
        return head;
    }

    //- добавить в конец списка
    public void addLast(T item) {
        ListItem<T> li = new ListItem<T>(item);
        if (head == null) {
            head = li;
            tail = li;
        } else {
            //tail.setNext(li);
            tail.next = li;
            li.previous = tail;
        }
        tail = li;
        count++;
    }
    //- добавить в начало списка
    public void addFirst(T item) {
        ListItem<T> li = new ListItem<T>(item);
        ListItem<T> temp = head;

        if (head == null) {
            head = li;
            tail = li;
        } else {
//            head.setPrevious(li);
            head = li;
            head.next = temp;
            temp.previous = head;
        }
        count++;
    }
    // - удалить
    public void remove(T item) {
        ListItem<T> previous = null;
        ListItem<T> current = head;

        // 1: Пустой список: ничего не делать.
        // 2: Один элемент: установить Previous = null.
        // 3: Несколько элементов:
        //    a: Удаляемый элемент первый.
        //    b: Удаляемый элемент в середине или конце.
        if(current == null)
            System.out.println("Пустой список");
        while (current != null)
        {
            // Head -> 3 -> 5 -> 7 -> null
            // Head -> 3 ------> 7 -> null
            if (current.item.equals(item))
            {
                // Узел в середине или в конце.
                if (previous != null)
                {
                    // Случай 3b.
                    previous.next = current.next;

                    // Если в конце, то меняем _tail.
                    if (current.next == null)
                    {
                        tail = previous;
                    }
                    else
                    {
                        // До:    Head -> 3  5  7 -> null
                        // После: Head -> 3  7 -> null

                        // previous = 3
                        // current = 5
                        // current.Next = 7
                        // Значит... 7.Previous = 3
                        current.next.previous = previous;
                    }

                    count--;
                }
                else
                {
                    // Случай 2 или 3a.
                    if (count != 0)
                    {
                        // До:    Head -> 3  5
                        // После: Head -------> 5

                        // Head -> 3 -> null
                        // Head ------> null
                        head = head.next;

                        count--;

                        if (count == 0)
                        {
                            tail = null;
                        }
                        else
                        {
                            // 5.Previous было 3; теперь null.
                            head.previous = null;
                        }
                    }
                }


            }

            previous = current;
            current = current.next;
        }

    }
    //- получить элемент по индексу
    public void at(int i) {

        ListItem<T> previous = null;
        ListItem<T> current = head;
        //System.out.println(current.getItem());
        if(0 < i & i <= count ){
            for (int n = 0; n < i-1; n++ ){
                previous = current;
                current = current.next;
            }

            System.out.println(current.getItem());
        } else {
            System.out.println("Количество элементов в списке меньше чем " + i);
        }


    }
    //- получить количество элементов
    public int size() {
        return count;
    }

    // - конструктор из массива
    public static<T> BiDirList<T> from(T[] array) {
        BiDirList<T> biDirList = new BiDirList<T>();

        for (int i = 0; i < array.length; i++){
            biDirList.addLast(array[i]);
        }
        return (BiDirList<T>) biDirList;
    }
    // -  конструктор из массива
    public static<T> BiDirList<T> of(T...array){
        BiDirList<T> biDirList = new BiDirList<T>();
        for (int i = 0; i < array.length; i++){
            biDirList.addLast(array[i]);
        }
        return (BiDirList<T>) biDirList;
    }


    //- скопировать в массив

    public  void toArray(T[] array)  {
        ListItem<T> previous = null;
        ListItem<T> current = head;
    T[] nevArray = (T[])(new Object[array.length + count]);
        System.arraycopy(array, 0, nevArray, 0, array.length);
        for (int i = array.length;i < nevArray.length; i++){
            //System.out.println(current.getItem());
            nevArray[i] =  current.getItem();
            previous = current;
            current = current.next;

        }

        for (int g = 0; g < nevArray.length; g++){
            System.out.println(nevArray[g]);
        }
    }

    public Iterator<BiDirList<T>> getIterator(){
        BiDirList<T> biDirList = new BiDirList<>();
        ListItem<T> previous = null;
        ListItem<T> current = head;
        List<T> listItem = new ArrayList<>();
        while (current != null){
            listItem.add(current.item);
            System.out.println(current.item);
            //addLast(current.item);
            previous = current;
            current = current.next;
        }

        return (Iterator<BiDirList<T>>) biDirList;
    }

    public static void main(String[] args) {
       BiDirList<Integer> biDirList = new BiDirList<>();
        biDirList.addLast(5);
        biDirList.addLast(8);
        biDirList.addLast(10);
        biDirList.addFirst(4);
        biDirList.addFirst(3);
        biDirList.addFirst(2);

        /*     biDirList.remove(2);
        biDirList.at(2);
        System.out.println(biDirList.size());*/

//BiDirList<Integer>.ListItem<Integer> curent = biDirList.getHead();
   /* Integer[] array = {4,5,6,8};


        biDirList.toArray(array);
       // BiDirList<Integer> biDirList = new BiDirList<>();
        //BiDirList<Integer>.ListItem<Integer> curent = from(array).getHead();

        BiDirList<Integer>.ListItem<Integer> curent = of(3,6,8,10,22,25).getHead();
        while (curent != null) {
            if (curent.previous == null){
                System.out.print(curent.previous);
            } else {
                System.out.print(curent.previous.item);
            }

            System.out.print(" - " + curent.getItem() + " - ");
            if (curent.next == null){
                System.out.println(curent.next);
            } else {
                System.out.println(curent.next.item);
            }
            //System.out.println(curent.getNext());
            curent = curent.getNext();
        }

*/


    }


}
