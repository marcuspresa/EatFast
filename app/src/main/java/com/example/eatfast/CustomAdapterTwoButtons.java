package com.example.eatfast;
import com.example.eatfast.Database.Database;
import com.example.eatfast.Model.Order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
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
        db = new Database(context, "Eatit.db",null, 1 );
        this.list = list;
        this.context = context;
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
        listItemText.setText(text.getProductName() + text.getPrice());

        Button addBtn = (Button)view.findViewById(R.id.addBtn);
        Button deleteBtn = (Button)view.findViewById(R.id.deleteBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Order testOrder = list.get(position);

                boolean isInserted = db.insertData(testOrder.getProductName().toString(), testOrder.getPrice().toString());
                if(isInserted == true)
                    Toast.makeText(context, "Placed in cart", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();


            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Order testOrder = list.get(position);
                db.deleteRow(testOrder.getId());
            }
        });
        return view;
    }




}


