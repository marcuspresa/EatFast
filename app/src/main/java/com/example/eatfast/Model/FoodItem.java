package com.example.eatfast.Model;

import java.io.Serializable;

/*
 A class to create orders.
 */
public class FoodItem implements Serializable {
    private String productName;
    private String price;
    private String stringId;
    private String foodInfo;
    private String calories;
    private int intId;

public FoodItem(){

}
    public FoodItem(String productName, String price) {
        this.productName = productName;
        this.price = price;
    }

    public FoodItem(String productName, String price, String foodInfo, String calories){
        this.productName = productName;
        this.price = price;
        this.foodInfo = foodInfo;
        this.calories = calories;
    }

    public FoodItem(int id, String productName, String price) {
        this.productName = productName;
        this.price = price;
        this.intId = id;
    }

    public FoodItem(String price, String id, String productName) {
        this.productName = productName;
        this.price = price;
        this.stringId = id;
    }



    public String getId(){
        return this.stringId;
    }

    public String getFoodInfo(){return this.foodInfo;}

    public String getCalories(){return this.calories;}

    public int getIntId(){ return this.intId;}

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
