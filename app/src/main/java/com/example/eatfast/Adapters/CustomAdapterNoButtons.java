package com.example.eatfast.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.eatfast.Database.Database;
import com.example.eatfast.Model.FoodItem;
import com.example.eatfast.R;

import java.util.ArrayList;

public class CustomAdapterNoButtons extends BaseAdapter implements ListAdapter {


    Database db;

    private ArrayList<FoodItem> list = new ArrayList<FoodItem>();
    private Context context;

    public CustomAdapterNoButtons() {

    }

    public CustomAdapterNoButtons(ArrayList<FoodItem> list, Context context) {
        db = new Database(context);
        this.list = list;
        this.context = context;
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
            view = inflater.inflate(R.layout.activity_display_order, null);
        }
        TextView listItemText = (TextView) view.findViewById(R.id.orderItem);
        FoodItem text = list.get(position);
        listItemText.setText(text.getProductName() + " " + text.getPrice() + " :-");

        return view;
    }


}


