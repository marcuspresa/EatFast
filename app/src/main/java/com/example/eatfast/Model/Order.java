package com.example.eatfast.Model;

import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class Order extends AppCompatActivity implements Serializable {

    String id;
    String status;

    ArrayList<FoodItem> list = new ArrayList<>();
    ArrayList<FirebaseOrder> firebaseList = new ArrayList<>();

    public Order() {
    }

    public Order(String id, ArrayList<FoodItem> list) {
        this.id = id;
        this.list = list;
    }

    public Order(ArrayList<FirebaseOrder> list, String id) {
        this.id = id;
        this.firebaseList = list;
    }

    public Order(String userId, ArrayList<FirebaseOrder> list, String status) {
        this.firebaseList = list;
        this.status = status;
        this.id = userId;
    }

    public Order(ArrayList<FoodItem> list) {
        this.list = list;
    }

    public ArrayList<FoodItem> getOrderList() {
        return list;
    }

    public ArrayList<FirebaseOrder> getFireBaseList() {
        return firebaseList;
    }

    public String getId() {
        return id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOrderList(ArrayList<FoodItem> list) {
        this.list = list;
    }

    public String getTotalPrice() {

        int intTotal = 0;
        for (int i = 0; i < list.size(); i++) {
            FoodItem o = list.get(i);
            intTotal += Integer.parseInt(o.getPrice());
        }
        String price = Integer.toString(intTotal);
        return price;
    }

}
