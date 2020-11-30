package ru.progwards.java2.lessons.gc;

import ru.progwards.java2.lessons.gc_art.*;

import java.util.*;

public class Heap{
    private List<Block> freeBlocks = new ArrayList<>();//свободные
    private TreeMap<Integer, Integer> occupiedBlocks = new TreeMap<>();
    private byte[] bytes;
    private int maxHeapSize;

    static class Block {
        int ptr;
        public int size;

        public Block(int ptr, int size) {
            this.ptr = ptr;
            this.size = size;
        }
    }

    Heap(int maxHeapSize){
        this.maxHeapSize = maxHeapSize;
        bytes = new byte[maxHeapSize];
        freeBlocks.add(new Block(0,maxHeapSize));
        occupiedBlocks.put(0,0);

    }
// "размещает"
    public int malloc(int size) throws OutOfMemoryException, MemoryIsFull, sizeNul {
        if (size == 0)
            throw new sizeNul();
        if (freeBlocks.isEmpty()){
            throw new MemoryIsFull();//память переполнена
        }
        int num = 0;
        boolean inserted = false;
        for (ListIterator<Block> itFre = freeBlocks.listIterator(); itFre.hasNext();){
            Block nextFree = itFre.next();

            if (nextFree.size >= size){
                num = nextFree.ptr;
                //добавляем байты в масив
               getBytes(num,bytes,size);
//                    for (int i = 0 ; i < size; i++){
//                        bytes[num + i] = 1;
//                    }
                if (nextFree.size == size){
                    itFre.remove();
                    inserted = true;
                    break;
                } else {
                    itFre.set(new Block(nextFree.ptr + size, nextFree.size - size));
                    inserted = true;
                    break;
                }
            }
        }

        if (!inserted) {//если не добавилось то
            compact();//уплотняем
            //и пробуем добавить еще раз
            for (ListIterator<Block> itFre = freeBlocks.listIterator(); itFre.hasNext();){
                Block nextFree = itFre.next();

                if (nextFree.size >= size){
                    num = nextFree.ptr;
                    //добавляем байты в масив
                   getBytes(num,bytes,size);
//                    for (int i = 0 ; i < size; i++){
//                        bytes[num + i] = 1;
//                    }
                    if (nextFree.size == size){
                        itFre.remove();
                        inserted = true;
                        break;
                    } else {
                        itFre.set(new Block(nextFree.ptr + size, nextFree.size - size));
                        inserted = true;
                        break;
                    }
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
        if (occupiedBlocks.containsKey(ptr)) {
            //удаляем байты из масива

            for (int i = 0; i < occupiedBlocks.get(ptr); i++) {
                bytes[ptr + i] = 0;
            }

            freeBlocks.add(new Block(ptr,occupiedBlocks.get(ptr)));//добавляем блок в список пустых блоков

            occupiedBlocks.remove(ptr);//удаляем запись блока из списка занятые
            noBlok = true;
        }

        if (!noBlok) {
            System.out.println(" нет такого " + ptr);
            throw new InvalidPointerException();
        }
    }
//осуществляет дефрагментацию кучи
  public void defrag(){
        if (freeBlocks.isEmpty())
            return;
      Collections.sort(freeBlocks,  (a, b) -> Integer.compare(a.ptr, b.ptr));

      int sumPtrSize = 0;
      int sumSize = 0;
      for (ListIterator<Block> itFre = freeBlocks.listIterator(); itFre.hasNext();){
          Block nextFree = itFre.next();//возвращаемся в предыдущий
          sumSize = nextFree.size;
          if(sumPtrSize != 0 & sumPtrSize == nextFree.ptr){
              itFre.remove();//удаляем блок который соприкасался
              Block prevFree = itFre.previous();
              sumSize = sumSize + prevFree.size;
              itFre.set(new Block(prevFree.ptr,  sumSize));//меняем запись в предыдущем блоке
          } else {
              sumPtrSize = nextFree.size + nextFree.ptr;
          }

      }

  }


    // компактизация кучи
    public void compact(){
        if (freeBlocks.isEmpty() || freeBlocks.size() == 1){
            return;
        }
        int thisKey = occupiedBlocks.firstKey();
        int startNevBlock = 0;
        TreeMap<Integer,Integer> nevOccup = new TreeMap<>();
        for (var mapOccup : occupiedBlocks.tailMap(thisKey).entrySet()){
            thisKey = mapOccup.getKey();

          if (thisKey != 0){
          setBytes(startNevBlock,bytes,mapOccup.getValue());
//              for (int i = 0; i < mapOccup.getValue(); i++){
//                 bytes[startNevBlock + i] = bytes[thisKey + i];//копирую данные
//                 bytes[thisKey + i] = 0;//затираю перенесенные данные
//              }

              //перезапись в новый список занятых
            nevOccup.put(startNevBlock,mapOccup.getValue());
          } else {
              nevOccup.put(0,occupiedBlocks.get(0));
          }
            startNevBlock = startNevBlock + mapOccup.getValue();

        }
        occupiedBlocks.clear();
        occupiedBlocks = nevOccup;//заменяем старый список (занятых блоков) на новый
        freeBlocks.clear();//очищаем список о свободных блоках
        freeBlocks.add(new Block(startNevBlock,maxHeapSize - startNevBlock));// заносим запись о свободном блоке
    }

    public void getBytes(int ptr, byte[] bytes, int size) {
        System.arraycopy(this.bytes, ptr, bytes, 0, size);
    }

    public void setBytes(int ptr, byte[] bytes, int size) {
        System.arraycopy(bytes, 0, this.bytes, ptr, size);
    }
/*
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
*/
    public static void main(String[] args) throws OutOfMemoryException, MemoryIsFull, InvalidPointerException, sizeNul {
        Heap heap = new Heap(30);


        System.out.println(heap.malloc(6));
        System.out.println(heap.malloc(2));
        System.out.println(heap.malloc(3));
        System.out.println(heap.malloc(4));
        System.out.println(heap.malloc(15));

      //System.out.println(heap.malloc(5));
 /* */
        heap.free(6);
        heap.free(11);
        heap.free(8);
        heap.defrag();
//        System.out.println(heap.malloc(5));
//        heap.free(21);
        heap.free(15);
       // heap.compact();
        for (Iterator<Block> mapFree = heap.freeBlocks.iterator(); mapFree.hasNext(); ) {
        //for (Map.Entry<Integer, Integer> mapFree : heap.freeBlocks.entrySet()){
            Block  blI = mapFree.next();
            System.out.println("свободные ячейки от - " + blI.ptr + " / длиной - " + blI.size);
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
