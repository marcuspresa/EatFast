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


import java.util.ArrayList;

public class CustomAdapterTwoButtons extends BaseAdapter implements ListAdapter {


    Database db;

    private ArrayList<String> list = new ArrayList<String>();
    private Context context;

    public CustomAdapterTwoButtons(ArrayList<String> list, Context context){
        db = new Database(context);
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public String getItem(int pos){
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
        listItemText.setText(list.get(position));

        Button addBtn = (Button)view.findViewById(R.id.addBtn);
        Button deleteBtn = (Button)view.findViewById(R.id.deleteBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });


        return view;
    }




}


