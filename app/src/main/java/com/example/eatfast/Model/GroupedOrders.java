package com.example.eatfast.Model;

import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
import java.security.acl.Group;
import java.util.ArrayList;

public class GroupedOrders extends AppCompatActivity implements Serializable {

    String id;
    ArrayList<Order> list = new ArrayList<>();

    public GroupedOrders(){
    }

    public GroupedOrders(String id, ArrayList<Order> list){
        this.id = id;
        this.list = list;
    }

    public GroupedOrders(ArrayList<Order> list){
        this.list = list;
    }

    public ArrayList<Order> getOrderList(){
        return list;
    }

    public String getId(){
        return id;
    }

    public int getTotalPrice(){

        int total = 0;
        for(int i = 0; i<list.size(); i++){
            Order o = list.get(i);
            total += Integer.parseInt(o.getPrice());
        }
        return total;
    }

}
