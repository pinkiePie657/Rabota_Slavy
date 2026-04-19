package org.example.model;

public class Product {
    private String name;
    private double price;
    private String description;

    public Product(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    // Исправленный конструктор (раньше был пустой)
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.description = "";
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return name + " (" + price + " руб.)";
    }
}