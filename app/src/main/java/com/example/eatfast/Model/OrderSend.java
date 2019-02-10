package com.example.eatfast.Model;

import java.util.List;

public class OrderSend {
    private int Total;
    private List<String> Foods;



    public OrderSend(int total, List<String> foods) {
        Total = total;
        Foods = foods;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public List<String> getFoods() {
        return Foods;
    }

    public void setFoods(List<String> foods) {
        Foods = foods;
    }
}
