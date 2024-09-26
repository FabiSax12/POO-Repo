package models;

public class Circle implements IShape{
    private int radius;

    @Override
    public float getArea() {
        return (float) (Math.PI * Math.sqrt(radius));
    }

    @Override
    public float getPerimeter() {
        return (float) (2 * Math.PI * radius);
    }
}
