package com.example.eatfast.Model;

/*
 A class to create orders.
 */
public class Order {
    private String ProductName;
    private String Price;
    private Integer Quantity;

    public Order(String productName, String price, Integer quantity) {
        ProductName = productName;
        Price = price;
        Quantity = quantity;

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

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public Integer getQuantity() {
        return Quantity;
    }

}
