package com.example.eatfast.Model;

import java.io.Serializable;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class DoneOrder implements Serializable {

    private String orderID;
    private String user;
    private int amount;
    private String status;
    private ArrayList<Order> list = new ArrayList<>();
    private ArrayList<String> slist = new ArrayList<>();


    public DoneOrder(){

    }
    public DoneOrder(int amount, String status, String user, String orderID) {
        this.amount = amount;
        this.status = status;
        this.user = user;
        this.orderID = orderID;

    }

    public DoneOrder(int amount, String status, String user, String orderID, ArrayList<Order> list) {
        this.amount = amount;
        this.status = status;
        this.user = user;
        this.orderID = orderID;
        this.list = list;
    }

    public DoneOrder(ArrayList<String> list){this.slist = list;}

    public ArrayList<Order> getOrders(){return this.list;}

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
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

    public void addOrder(Order o) { list.add(o);}

    public void setOrders(ArrayList<Order> list) {
        this.list = list;
    }
}


