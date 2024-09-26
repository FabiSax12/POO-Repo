package models;

public class Triangle implements IShape{
    private int l1;
    private int l2;
    private int l3;

    private int base;
    private int height;

    private boolean validateSidesLength() {
        return (l1 + l2 > l3) && (l1 + l3 > l2) && (l2 + l3 > l1);
    }

    @Override
    public float getArea() {
        return (float) (base * height) / 2;
    }

    @Override
    public float getPerimeter() {
        return l1 + l2 + l3;
    }
}
