package com.example.fastkafoodappandroid.Model;

public class MyCart {
    private int mAmount;
    private int total;
    private String date;
    private String email;

    public MyCart(int mAmount, int total) {
        this.mAmount = mAmount;
        this.total = total;
    }

    public MyCart(int mAmount, int total, String date, String email) {
        this.mAmount = mAmount;
        this.total = total;
        this.date = date;
        this.email = email;
    }

    public int getmAmount() {
        return mAmount;
    }

    public void setmAmount(int mAmount) {
        this.mAmount = mAmount;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
