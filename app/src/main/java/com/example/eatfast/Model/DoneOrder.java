package com.example.eatfast.Model;

import java.util.ArrayList;

public class DoneOrder {

    private String orderID;
    private String amount;
    private ArrayList<String> foods;


    public DoneOrder(){

    }
    public DoneOrder(String orderID, String amount, ArrayList<String> foods) {
        this.orderID = orderID;
        this.amount = amount;
        this.foods = foods;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public ArrayList<String> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<String> foods) {
        this.foods = foods;
    }
}


