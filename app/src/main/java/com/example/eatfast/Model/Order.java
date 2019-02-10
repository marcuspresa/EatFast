package com.example.eatfast.Model;

/*
 A class to create orders.
 */
public class Order {
    private String ProductName;
    private String Price;
    private int id;



    public Order(){

    }

    public Order(String productName, String price) {
        ProductName = productName;
        Price = price;
    }

    public Order(String productName, String price, int id) {
        ProductName = productName;
        Price = price;
        this.id = id;
    }

    public int getId(){
        return this.id;
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
