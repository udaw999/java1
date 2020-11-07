package ru.progwards.java1.lessons.arrays;

import java.util.ArrayList;

class PIntArray {
    ArrayList<int[]> array;
    int size;
    int capacity;

    public PIntArray(int blockSize) {
        array = new ArrayList<>();
        array.add(new int[blockSize]);
        capacity = blockSize;
    }

    public void add(int item) {
        int[] page = array.get(array.size()-1);
        if (size >= page.length) {
            page = new int[page.length];
            array.add(page);
            size = 0;
        }
        page[size++] = item;
    }

    int get(int index) {
        int index1 = index / capacity;
        int index2 = index % capacity;
        return array.get(index1)[index2];
    }

    public int size() {
        return (array.size()-1)*capacity + size;
    }
}

