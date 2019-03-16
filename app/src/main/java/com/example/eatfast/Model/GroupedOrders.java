package com.example.eatfast.Model;

import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
import java.security.acl.Group;
import java.util.ArrayList;

public class GroupedOrders extends AppCompatActivity implements Serializable {

    String id;
    String status;

    ArrayList<Order> list = new ArrayList<>();
    ArrayList<DoneOrder> list2 = new ArrayList<>();

    public GroupedOrders(){
    }

    public GroupedOrders(String id, ArrayList<Order> list){
        this.id = id;
        this.list = list;
    }

    public GroupedOrders(ArrayList<DoneOrder> list, String id){
        this.id = id;
        this.list2 = list;
    }

    public GroupedOrders(String userId, ArrayList<DoneOrder> list, String status){
        list2 = list;
        this.status = status;
        id = userId;
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

    public void setStatus(String status){this.status = status;}

    public void setId(String id){this.id = id;}

    public void setOrderList(ArrayList<Order> list){this.list = list;}

    public int getTotalPrice(){

        int total = 0;
        for(int i = 0; i<list.size(); i++){
            Order o = list.get(i);
            total += Integer.parseInt(o.getPrice());
        }
        return total;
    }

}
