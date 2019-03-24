package com.example.eatfast.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eatfast.Orders.CartActivity;
import com.example.eatfast.Database.Database;
import com.example.eatfast.Model.FoodItem;
import com.example.eatfast.R;

import java.util.ArrayList;

public class CustomAdapterTwoButtons extends BaseAdapter implements ListAdapter {

    Database db;

    private ArrayList<FoodItem> list = new ArrayList<FoodItem>();
    private Context context;

    public CustomAdapterTwoButtons(ArrayList<FoodItem> list, Context context) {
        db = new Database(context);
        this.list = list;
        this.context = context;
    }

    public void add(FoodItem p) {


        int newId = list.get(list.size() - 1).getIntId() + 1;
        System.out.println("TESTING NEWID" + newId);

        FoodItem o = new FoodItem(newId, p.getProductName(), p.getPrice());
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

    public void counter(ArrayList<FoodItem> list) {

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

    public void delete(FoodItem p) {
        db.deleteRow(p.getIntId());
        list.remove(p);

        notifyDataSetChanged();
    }

    public ArrayList<FoodItem> getUpdatedList() {
        return list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public FoodItem getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_cart_layout, null);
        }

        TextView listItemText = (TextView) view.findViewById(R.id.orderItem);
        FoodItem text = list.get(position);
        listItemText.setText(text.getProductName() + " " + text.getPrice() + ":-");

        final Button addBtn = (Button) view.findViewById(R.id.addBtn);
        Button deleteBtn = (Button) view.findViewById(R.id.deleteBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FoodItem testFoodItem = list.get(position);
                System.out.println(testFoodItem.getId() + "TESTING");
                add(testFoodItem);
                counter(list);
                Toast.makeText(context, "Placed " + testFoodItem.getProductName() + " in cart", Toast.LENGTH_LONG).show();

            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FoodItem testFoodItem = list.get(position);
                delete(testFoodItem);
                counter(list);
                Toast.makeText(context, "Removed " + testFoodItem.getProductName() + " from cart", Toast.LENGTH_LONG).show();

            }
        });
        return view;
    }


}


