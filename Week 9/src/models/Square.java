package models;

public class Square implements IShape{
    private int side;
    @Override
    public float getArea() {
        return side * side;
    }

    @Override
    public float getPerimeter() {
        return side * 4;
    }
}
