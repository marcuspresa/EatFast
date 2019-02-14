package com.example.eatfast.Model;

/*
 A class to create orders.
 */
public class Order {
    private String ProductName;
    private String Price;
    private int Id;

public Order(){

}
    public Order(String productName, String price) {
        ProductName = productName;
        Price = price;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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
