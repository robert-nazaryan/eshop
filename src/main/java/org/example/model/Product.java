package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private int qty;

    public Product(String name, String description, double price, int qty) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.qty = qty;
    }
}
