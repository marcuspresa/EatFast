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

public class CustomAdapterOrders extends BaseAdapter implements ListAdapter {


    private ArrayList<String> list = new ArrayList<String>();
    private Context context;

    public CustomAdapterOrders(){

    }

    public CustomAdapterOrders(ArrayList<String> list, Context context){
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
            view = inflater.inflate(R.layout.frag_order_layout, null);
        }
        TextView listItemText = (TextView)view.findViewById(R.id.order);
        String text = list.get(position);
        listItemText.setText(text);

        return view;
    }


}


