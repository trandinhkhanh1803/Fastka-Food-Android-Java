package com.example.fastkafoodappandroid.Model;

public class CartData {
    private String title,imageUrl;
    private int amount;
    private Double price;

    public CartData(String title, String imageUrl, int amount, Double price) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.amount = amount;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
