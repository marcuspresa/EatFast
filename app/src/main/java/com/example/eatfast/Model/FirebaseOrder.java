package com.example.eatfast.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class FirebaseOrder implements Serializable {

    private String orderID;
    private String user;
    private String amount;
    private String status;
    private ArrayList<FoodItem> list = new ArrayList<>();
    private ArrayList<String> slist = new ArrayList<>();


    public FirebaseOrder(){

    }

    public FirebaseOrder(String amount, String status, String user, String orderID) {
        this.amount = amount;
        this.status = status;
        this.user = user;
        this.orderID = orderID;

    }

    public FirebaseOrder(String amount, String status, String user, String orderID, ArrayList<FoodItem> list) {
        this.amount = amount;
        this.status = status;
        this.user = user;
        this.orderID = orderID;
        this.list = list;
    }

    public FirebaseOrder(ArrayList<String> list){this.slist = list;}

    public ArrayList<FoodItem> getOrders(){return this.list;}

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void addOrder(FoodItem o) { list.add(o);}

    public void setOrders(ArrayList<FoodItem> list) {
        this.list = list;
    }
}


