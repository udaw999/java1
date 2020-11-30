package ru.progwards.java2.lessons.gc_art;

public class InvalidPointerException extends Throwable {
    @Override
    public String toString() {
        return "InvalidPointerException. неверный указатель";
    }
}
