package ru.progwards.java2.lessons.basetypes;


import java.util.ArrayList;
import java.util.Iterator;

public class DoubleHashTable<K,V>{



    class DHashItem<K,V> extends DoubleHashTable<K,V> implements HashValue {

        private V value;
        private K key;


        DHashItem(K key, V value) {
            this.key = key;
            this.value = value;
        }

        K getKey() {
            return key;
        }

        V getValue() {
            return value;
        }

        boolean equals(DHashItem<K,V> value) {
            return key == value.key;
        }

      /*  int RSHash (String str)
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
        }*/
        static final long UINT_MAX = 4294967295L;

        long RSHash (String str)
        {
            long b = 378551;
            long a = 63689;
            long hash = 0;
            for (int i = 0; i < str.length(); i++) {
                hash = unsignedInt(hash * a + str.charAt(i));
                a = unsignedInt(a * b);
            }
            return hash;
        }

        private long unsignedInt(long l) {
            return l % UINT_MAX;
        }

        @Override
        public int getHash() {
            int h = 0;
            try {
                h = (int) key;
            } catch (Exception e) {
                try {
                    h = (int) RSHash((String) key);
                }catch (Exception u) {
                    try {
                        h = (int)Math.round((Double) key);
                    } catch (Exception o) {
                        try {
                            h = (Character)key + 5;
                        } catch (Exception z) {
                            h = Math.round((Long) key);
                        }
                    }

                }


            }
            return h;
        }
    }

    static final double A = 0.6180339887;

    private int sizeTable = 101;

    DHashItem<K,V>[] table = new DHashItem[sizeTable];
    boolean[] deleted = new boolean[sizeTable];// для учета удаленных

    public int getSizeTable() {
        return sizeTable;
    }

    public int getHashEnd(int key) {

        return key % table.length;
    }

    public int getHashTwo(int key) {

        double d = A*key;
        return (int)(table.length*(d-Math.floor(d)));
        //return 1 + key % (table.length - 1);
    }

    //При количестве коллизий более 10% при одной серии пробирований - перестраивать таблицу, увеличивая размер

    private void resize() {

        System.out.println("resize()");
        DHashItem<K,V>[] tableClon = table.clone();

        sizeTable = nevSizeTable();
        table = new DHashItem[sizeTable];
        deleted = new boolean[sizeTable];
        for (int i = 0; i < tableClon.length; i++){
           if (tableClon[i] != null){
               add(tableClon[i].key,tableClon[i].value);
           }
        }

    }
    int nevSizeTable(){
        int a = sizeTable * 2 + 1;
        while (!isWhole(a)){
            a++;
        }
        return a;
    }
    //метод проверки - целое ли число
    public static boolean isWhole(int number) {
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }


    void add(K key, V value) {
        DHashItem<K,V> li = new DHashItem<K,V>(key, value);

        int endHash = getHashEnd(li.getHash());
        int twoHash = getHashTwo(li.getHash());
        int countColisii = 0;


        int index = endHash;
        for (int i = 0; i < table.length ; i++ ){
            try {
                if (table[index] == null || deleted[index]){
                    table[index] = li;
                    deleted[index] = false;
                    return;
                }
            } catch (ArrayIndexOutOfBoundsException a){
         //       resize();
          //      add(key,value);
                //table[index] = li;
//                System.out.print(index + " - index / ");
//                System.out.print(endHash + " - endHash / ");
//                System.out.println(twoHash + " - twoHash");
            }


            index = (index + i * twoHash) % table.length;
//            System.out.println(index + " - index");
//            System.out.println(sizeTable + " - sizeTable");
            countColisii++;
//если колизий больше допустимого
            if(countColisii >= sizeTable * 0.1){
                resize();//увеличиваем таблицу и перезаписываем итемы
//                countColisii = 0;//обнуляем счетчик колизий
//                index = endHash;//приводим в первоначальное состояние
//                i=0;//обнуляем количество циклов
                add(li.key,li.value);//добавляем итем при котором колизии стали максимальным числом
                return;
            }
        }
    }



    V get(K key) {

        DHashItem<K,V> li = new DHashItem<K,V>(key, null);

        int endHash = getHashEnd(li.getHash());
        int twoHash = getHashTwo(li.getHash());

        int index = endHash;

        //System.out.println(index + " index");
        for (int i = 0; i < table.length; i++) {
            DHashItem<K,V> current = table[index];
            if (table[index] != null) {
                if (current.key.equals(key) & !deleted[index]) {
                    return current.getValue();
                }
            } else {

                return null;
            }

            index = (index + twoHash) % table.length;
        }
        return null;
    }
    //- удалить элемент по ключу
    public void remove(K key) {
        DHashItem<K,V> li = new DHashItem<K,V>(key, null);
        System.out.println("deleted");
        int endHash = getHashEnd(li.getHash());
        int twoHash = getHashTwo(li.getHash());

        int index = endHash;
        for (int i = 0; i < table.length; i++) {
            DHashItem<K,V> current = table[index];
            if (table[index] != null) {
                if (current.key.equals(key)) {
                    deleted[index] = true;
                    table[index] = null;
                }
            } else {

                return;
            }

            index = (index + twoHash) % table.length;
        }

    }
    //- изменить значение ключа у элемента с key1 на key2
    public void change(K key1, K key2){
        V value = get(key1);
        remove(key1);
        add(key2,value);
    }
    //- получить количество элементов
    public int size() {
        int count = 0;
        for (int i = 0; i< table.length; i++){
            if (table[i] != null & !deleted[i]){
               count++;
            }
        }
        return count;
    }

    public Iterator<DoubleHashTable<K,V>> getIterator(){

        Iterator<DoubleHashTable<K,V>> it = new Iterator<DoubleHashTable<K, V>>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size() && table[currentIndex] != null;
            }

            @Override
            public DoubleHashTable<K,V> next() {
                return table[currentIndex++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

        };
        return it;
    }
    public static void main(String[] args) {
        DoubleHashTable<Integer, String> table = new DoubleHashTable<>();
        int z = 1;
        int k = 5;
         for (int i=0; i<50; i++){
             table.add(i, "i= "+i +"- " + i); }
//             table.add(k, "k= "+k +"- " + i);
//             table.add(z, "z= "+z +"- " + i);

//       table.remove(28);
//        table.remove(30);
//        for (int i=50; i<220; i++) {
//            table.add(i, "i= " + i + "- " + i);
//        }
//        table.add(28, "i= 28--");
//
 //       table.change(40,222);
         /*  table.add("саша", "i=саша");
            table.add("МАША", "i=МАША");
            table.add("ДАША", "i=ДАША");
            table.add("КОЛЯ", "i=КОЛЯ");*/
//        table.add('*', "i*");
//        table.add('-', "i-");
//        table.add('=', "i=");
//        System.out.println(table.table.length + " table.table.length");
//        for (int i=0; i<table.table.length; i++){
//            if(table.table[i] == null){
//                System.out.println("null - " + table.deleted[i]);
//            } else {
//                System.out.println(table.table[i].value + " - "  + table.deleted[i]);
//
//            }
//        }
        System.out.println("===========================================");
        System.out.println(table.size());
        System.out.println("===========================================");
 //       System.out.println(table.table[170] + "table.table[170]" );
//        for (int i=0; i<230; i++)
//           System.out.println(table.get(i));
        /**/
        //
       // System.out.println("КОЛЯ" + table.get("ДАША"));
       /* */

        Iterator iterator = table.getIterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());



    }

}
interface HashValue {
    int getHash();

}