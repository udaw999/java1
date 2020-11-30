package ru.progwards.java2.lessons.gc;

import ru.progwards.java2.lessons.gc_art.InvalidPointerException;
import ru.progwards.java2.lessons.gc_art.MemoryIsFull;
import ru.progwards.java2.lessons.gc_art.OutOfMemoryException;
import ru.progwards.java2.lessons.gc_art.sizeNul;

import java.util.*;

public class HeapClon {
    private TreeMap<Integer, Integer> freeBlocks = new TreeMap<Integer, Integer>();//свободные
    private TreeMap<Integer, Integer> occupiedBlocks = new TreeMap<>();
    private byte[] bytes;
    private int maxHeapSize;



    HeapClon(int maxHeapSize){
        this.maxHeapSize = maxHeapSize;
        bytes = new byte[maxHeapSize];
        freeBlocks.put(0,maxHeapSize);
        occupiedBlocks.put(0,0);
        /*

        freeBlocks.put(11,5);
        freeBlocks.put(0,10);*/
    }
    // "размещает"
    public int malloc(int size) throws OutOfMemoryException, MemoryIsFull, sizeNul {
        //freeBlocks = sortMapByValue(freeBlocks);//сортировка по значению
        if (size == 0)
            throw new sizeNul();
        if (freeBlocks.isEmpty()){
            throw new MemoryIsFull();//память переполнена
        }

        int num = 0;
        boolean inserted = false;
        for (Map.Entry<Integer, Integer> mapFree : freeBlocks.entrySet()){

            if (mapFree.getValue() >= size) {
                num = mapFree.getKey();
                //добавляем байты в масив
                getBytes(num,bytes,size);
                 /*   for (int i = 0 ; i < size; i++){
                        bytes[num + i] = 1;
                    }
*/

                if (size != mapFree.getValue())//если размер вносимого блока не равен - добавим запись
                    freeBlocks.put(num + size,mapFree.getValue() - size);
                if (size != 0)//удалим предыдущую запись
                    freeBlocks.remove(num);
                inserted = true;
                break;
            }
        }
        if (!inserted){//если не добавилось то
            compact();//уплотняем
            //и пробуем добавить еще раз
            for (Map.Entry<Integer, Integer> mapFree : freeBlocks.entrySet()){

                if (mapFree.getValue() >= size) {
                    num = mapFree.getKey();
                    //добавляем байты в масив
                    getBytes(num,bytes,size);
//                    for (int i = 0 ; i < size; i++){
//                        bytes[num + i] = 1;
//                    }

                    if (size != mapFree.getValue())//если размер вносимого блока не равен - добавим запись
                        freeBlocks.put(num + size,mapFree.getValue() - size);
                    if (size != 0)//удалим предыдущую запись
                        freeBlocks.remove(num);
                    inserted = true;
                    break;
                }
            }
        }

        //если все пустые блоки меньше того что надо вставить выбрасываем исключение
        if (num == 0 & !inserted)
            throw new OutOfMemoryException();

        occupiedBlocks.put(num,size);
        return num;
    }
    // "удаляет"
    public void free(int ptr) throws InvalidPointerException {
        boolean noBlok = false;
        if (occupiedBlocks.containsKey(ptr)){
            //удаляем байты из масива
            for (int i = 0 ; i < occupiedBlocks.get(ptr); i++){
                bytes[ptr + i] = 0;
            }

/*
            if (freeBlocks.containsKey(ptr + occupiedBlocks.get(ptr))){//если блоки соприкоснутся со следующим
                //делаем блок общий с новым ключем и длиной суммой из двух
                freeBlocks.put(ptr,occupiedBlocks.get(ptr) + freeBlocks.get(ptr + occupiedBlocks.get(ptr)));
                //удаляем лишний блок
                freeBlocks.remove(ptr + occupiedBlocks.get(ptr));
            } else {*/
            freeBlocks.put(ptr,occupiedBlocks.get(ptr));//добавляем блок в список пустых блоков
            // }

            occupiedBlocks.remove(ptr);//удаляем запись блока из списка занятые
            noBlok = true;
        }

        if (!noBlok)
            throw new  InvalidPointerException();
    }
    //осуществляет дефрагментацию кучи
    public void defrag(){
        if (freeBlocks.isEmpty())
            return;

        int thisKey = freeBlocks.firstKey();

        cycleDefrag(thisKey);

    }

    private void cycleDefrag(int thisKey) {
        int keyNext;

        for (var mapFree : freeBlocks.tailMap(thisKey).entrySet()){
            thisKey = mapFree.getKey();
            keyNext = mapFree.getKey() + mapFree.getValue();
            if (freeBlocks.containsKey(keyNext)){
                freeBlocks.replace(mapFree.getKey(),freeBlocks.get(keyNext) + mapFree.getValue()); //замена значения на сумму двух
                freeBlocks.remove(keyNext);//удалякм соседний прилипший блок (запись)
                break;
            }
        }
        if (thisKey != freeBlocks.lastKey())//повторяем до тех пор пока не доберемся до последнего ключа
            cycleDefrag(thisKey);
    }

    // компактизация кучи
    public void compact(){
        if (freeBlocks.isEmpty() || freeBlocks.size() == 1 & freeBlocks.lastKey() > occupiedBlocks.lastKey() ){
            System.out.println("tyt");
            return;
        }
        int thisKey = occupiedBlocks.firstKey();
        int startNevBlock = 0;
        TreeMap<Integer,Integer> nevOccup = new TreeMap<>();
        for (var mapOccup : occupiedBlocks.tailMap(thisKey).entrySet()){
            thisKey = mapOccup.getKey();

            if (thisKey != 0){
                setBytes(startNevBlock,bytes,mapOccup.getValue());
  /*            for (int i = 0; i < mapOccup.getValue(); i++){
                 bytes[startNevBlock + i] = bytes[thisKey + i];//копирую данные
                 bytes[thisKey + i] = 0;//затираю перенесенные данные
              }
*/
                //перезапись в новый список занятых
                nevOccup.put(startNevBlock,mapOccup.getValue());
            } else {
                nevOccup.put(0,occupiedBlocks.get(0));
            }
            startNevBlock = startNevBlock + mapOccup.getValue();

        }
        occupiedBlocks = nevOccup;//заменяем старый список (занятых блоков) на новый
        freeBlocks.clear();//очищаем список о свободных блоках
        freeBlocks.put(startNevBlock,maxHeapSize - startNevBlock);// заносим запись о свободном блоке
    }

    public void getBytes(int ptr, byte[] bytes, int size) {
        System.arraycopy(this.bytes, ptr, bytes, 0, size);
    }

    public void setBytes(int ptr, byte[] bytes, int size) {
        System.arraycopy(bytes, 0, this.bytes, ptr, size);
    }

    //метод сортирует мап по значению
    private static <K, V extends Comparable<? super V>> Map<K, V> sortMapByValue(Map<K, V> initialMap) {

        List<Map.Entry<K, V>> list =
                new LinkedList<>(initialMap.entrySet());

        list.sort(Comparator.comparing(o -> (o.getValue())));

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;

    }

    public static void main(String[] args) throws OutOfMemoryException, MemoryIsFull, InvalidPointerException, sizeNul {
        HeapClon heap = new HeapClon(30);


        System.out.println(heap.malloc(3));
  /*      System.out.println(heap.malloc(2));
        System.out.println(heap.malloc(3));*/
        System.out.println(heap.malloc(4));
        System.out.println(heap.malloc(18));
        System.out.println(heap.malloc(5));

        //heap.free(0);
        heap.free(3);
        heap.free(25);
        //    heap.free(3);
        //    heap.free(5);
        //heap.defrag();
        //heap.compact();
        //heap.free(2);
        System.out.println(heap.malloc(8));
        //heap.compact();

        for (Map.Entry<Integer, Integer> mapFree : heap.freeBlocks.entrySet()){
            System.out.println("свободные ячейки от - " + mapFree.getKey() + " / длиной - " + mapFree.getValue());
        }
        System.out.println("--------------------");

        for (Map.Entry<Integer, Integer> mapOccu : heap.occupiedBlocks.entrySet()){
            System.out.println("занятые ячейки от - " + mapOccu.getKey() + " / длиной - " + mapOccu.getValue());
        }
        System.out.println("------------массив------------");
        int z = 0;
        for (byte b : heap.bytes){
            z++;
            if (z != 10){
                System.out.print(" | " + b + "");
            } else {
                System.out.println(" | " + b + " | ");
                z = 0;
            }


        }
    }



}

