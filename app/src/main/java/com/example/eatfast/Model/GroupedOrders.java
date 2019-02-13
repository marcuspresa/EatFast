package com.example.eatfast.Model;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class GroupedOrders extends AppCompatActivity {

    String id;
    ArrayList<Order> list = new ArrayList<>();

    public GroupedOrders(){
    }

    public GroupedOrders(String id, ArrayList<Order> list){
        this.id = id;
        this.list = list;
    }

    public ArrayList<Order> getOrderList(){
        return list;
    }

    public String getId(){
        return id;
    }

}
