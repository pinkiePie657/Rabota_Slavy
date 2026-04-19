package org.example.model;
import java.time.LocalDateTime;
public class Order {
    private int id;
    private Product product;
    private User customer;
    private LocalDateTime dateTime;
    public Order(Product product, User customer) {
        this.id = (int) (Math.random() * 10000);
        this.product = product;
        this.customer = customer;
        this.dateTime = LocalDateTime.now();
    }
    public Order(Product selected) {
    }


}