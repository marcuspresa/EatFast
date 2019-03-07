package com.example.eatfast;
import com.example.eatfast.Database.Database;
import com.example.eatfast.Model.Order;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import com.example.eatfast.CustomAdapterTwoButtons;


import java.util.ArrayList;

public class CustomAdapterTwoButtons extends BaseAdapter implements ListAdapter {

    Database db;

    private ArrayList<Order> list = new ArrayList<Order>();
    private Context context;

    public CustomAdapterTwoButtons(ArrayList<Order> list, Context context){
        db = new Database(context);
        this.list = list;
        this.context = context;
    }

    public void add(Order p){

        int newId = list.get(list.size()-1).getId()+1;
        Order o = new Order(p.getProductName(), p.getPrice(), newId);
        db.insertData(o.getProductName(), o.getPrice());
        list.add(o);

        /*boolean isInserted =
        if(isInserted == true) {
            Toast.makeText(context, "Placed in cart", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
        */

        notifyDataSetChanged();
    }

    public void counter(ArrayList<Order> list) {

        CartActivity.mCartItemCount = 0;
        for (int i = 0; i < list.size(); i++) {
            CartActivity.mCartItemCount++;
        }
        if (CartActivity.mCartItemCount == 0) {
            if (CartActivity.textCartItemCount.getVisibility() != View.GONE) {
                CartActivity.textCartItemCount.setVisibility(View.GONE);
            }
        } else {
            if (CartActivity.textCartItemCount.getVisibility() != View.VISIBLE) {
                CartActivity.textCartItemCount.setVisibility(View.VISIBLE);
            }
            CartActivity.textCartItemCount.setText(String.valueOf(CartActivity.mCartItemCount));
        }
    }

    public void delete(Order p){
        db.deleteRow(p.getId());
        list.remove(p);
        notifyDataSetChanged();
    }

    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public Order getItem(int pos){
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos){
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_cart_layout, null);
        }

        TextView listItemText = (TextView)view.findViewById(R.id.orderItem);
        Order text = list.get(position);
        listItemText.setText(text.getProductName() + " "+  text.getPrice()+":-");

        final Button addBtn = (Button)view.findViewById(R.id.addBtn);
        Button deleteBtn = (Button)view.findViewById(R.id.deleteBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Order testOrder = list.get(position);
                System.out.println(testOrder.getId() + "TESTING");
                add(testOrder);
                counter(list);
                Toast.makeText(context, "Placed " + testOrder.getProductName() + " in cart", Toast.LENGTH_LONG).show();

            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Order testOrder = list.get(position);
                delete(testOrder);
                counter(list);
                Toast.makeText(context, "Removed " + testOrder.getProductName() + " from cart", Toast.LENGTH_LONG).show();

            }
        });
        return view;
    }




}


