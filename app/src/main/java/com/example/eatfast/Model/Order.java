package com.example.eatfast.Model;

import android.os.Parcel;
import android.os.Parcelable;


/*
 A class to create orders.
 */
public class Order  {
    private String ProductName;
    private String Price;
    private int id;
    public Order(String productName, String price, int ID) {
        ProductName = productName;
        Price = price;
        id = ID;
    }
    public Order(){

}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getProductName() {
        return ProductName;
    }

    public String getPrice() {
        return Price;
    }


}
