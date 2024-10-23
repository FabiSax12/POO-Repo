package models;

public class Car {
    private final String brand;
    private final String model;
    private final int year;

    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public Car(String brand, int year) {
        this.brand = brand;
        this.model = "";
        this.year = year;
    }

    public void start() {
        System.out.println("Car " + brand + " " + model  + " is running.");
    }

    public void stop() {
        System.out.println("Car " + brand + " " + model  + " is stopped.");
    }
}
