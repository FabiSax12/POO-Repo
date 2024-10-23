package models;

public class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name) {
        this.name = name;
    }

    public void greet(String name) {
        System.out.println("Hello " + name + "!!");
    }
}
