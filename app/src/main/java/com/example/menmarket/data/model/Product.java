package com.example.menmarket.data.model;

public class Product {
    String image;
    String name;
    String description;
    String price;
    String category;

    public Product() {
    }

    public Product(String image, String name, String describtion, String price, String category) {
        this.image = image;
        this.name = name;
        this.description = describtion;
        this.price = price;
        this.category = category;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
