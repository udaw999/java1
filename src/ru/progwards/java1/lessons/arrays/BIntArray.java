package ru.progwards.java1.lessons.arrays;

public class BIntArray {
    static final int MIN_SIZE = 2;
    int[] array;
    int size;
    int blocksize;

    public BIntArray() {
        array = new int[MIN_SIZE];
    }

    public BIntArray(int initCapacity, int blockSize) {
        array = new int[initCapacity];
        this.blocksize = blockSize;
    }

    public void add(int item) {
        if (size >= array.length) {
            int newSize;
            if (blocksize != 0)
                newSize = array.length * 2;
            else
                newSize = array.length + blocksize;

            int[] newArray = new int[newSize];
            if (array != null)
                copyData(array, newArray);
            array = newArray;
        }
        array[size++] = item;
    }

    void copyData(int[] src, int[] dst) {
//        for(int i=0; i<src.length; i++)
//            dst[i] = src[i];
        System.arraycopy(src, 0, dst, 0, size);
    }

    public int get(int index) {
        return array[index];
    }

    public int size() {
        return size;
    }
}

