package com.example.fastkafoodappandroid.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Recommended implements Serializable {
 private String id,title,picture,description,timeDelivery;
 private Double price;
 private Float rating;

    public Recommended(String id, String title, Double price, String picture, String description, String timeDelivery, Float rating) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.picture = picture;
        this.description = description;
        this.timeDelivery = timeDelivery;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeDelivery() {
        return timeDelivery;
    }

    public void setTimeDelivery(String timeDelivery) {
        this.timeDelivery = timeDelivery;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
