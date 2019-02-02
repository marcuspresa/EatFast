package com.example.eatfast.Model;


/*
 A class to create orders.
 */
public class Order {
    private String ProductName;
    private String Price;
    private String Comment;


    public Order(){

    }

    public Order(String productName, String price, String comment) {
        ProductName = productName;
        Price = price;
        Comment = comment;
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

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
}
