package com.example.eatfast.Model;

import java.io.Serializable;

/*
 A class to create orders.
 */
public class Order implements Serializable {
    private String ProductName;
    private String Price;
    private int orderId;

    public Order(){

    }

    public Order(String productName, String price) {
        ProductName = productName;
        Price = price;
    }

    public Order(String productName, String price, int id) {
        ProductName = productName;
        Price = price;
        orderId = id;
    }

    public int getId(){
        return orderId;
    }

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
