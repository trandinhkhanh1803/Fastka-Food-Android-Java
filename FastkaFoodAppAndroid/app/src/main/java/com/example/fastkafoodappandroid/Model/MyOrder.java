package com.example.fastkafoodappandroid.Model;


import java.io.Serializable;

public class MyOrder implements Serializable {

    private  String email,numberOrder,date,total,address;


    public MyOrder(String email, String numberOrder, String date, String total, String address) {
        this.email = email;
        this.numberOrder = numberOrder;
        this.date = date;
        this.total = total;
        this.address = address;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(String numberOrder) {
        this.numberOrder = numberOrder;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
