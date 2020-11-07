package ru.progwards.java1.lessons.abstractnum;

public class Cube extends Figure3D {

    public Cube(Number segment){
        super(segment);
    }
    @Override
    public Number volume() {
        return segment.mul(segment).mul(segment);
    }
}
