package com.example.menmarket.data.model;

public class CartProduct  {
    public Product product;
    int numberOfProduct=0;

    public CartProduct(Product product, int numberOfProduct) {
        this.product = product;
        this.numberOfProduct = numberOfProduct;
    }

    public CartProduct() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setNumberOfProduct(int numberOfProduct) {
        this.numberOfProduct = numberOfProduct;
    }

    public int getNumberOfProduct() {
        return numberOfProduct;
    }
}
