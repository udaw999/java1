package ru.progwards.java2.lessons.gc_art;

public class MemoryIsFull extends Throwable {
    @Override
    public String toString() {
        return "MemoryIsFull. память переполнена";
    }
}
