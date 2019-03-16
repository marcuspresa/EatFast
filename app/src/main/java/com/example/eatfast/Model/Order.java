package com.example.eatfast.Model;

import java.io.Serializable;

/*
 A class to create orders.
 */
public class Order implements Serializable {
    private String ProductName;
    private String Price;
    private int orderId;
    private String stringId;

public Order(){

}
    public Order(String productName, String price) {
        ProductName = productName;
        Price = price;
    }

    public Order(int id, String productName, String price) {
        ProductName = productName;
        Price = price;
        orderId = id;
    }

    public Order(String price, String id, String productName){
        ProductName = productName;
        Price = price;
        stringId = id;
    }

    public int getId(){
        return orderId;
    }

    public String retrnStringId() {return stringId;}

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }


}
