package com.example.springmongoexample.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("TVItem")
public class TVItem {

    @Id
    private String id; // if @Id not specified mongoDb will  add _id field.
    private String name;
    private Integer quantity;
    private String category;

    public TVItem(String id, String name, Integer quantity, String category) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.category = category;
    }

    @Override
    public String toString() {
        return "TVItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                '}';
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
