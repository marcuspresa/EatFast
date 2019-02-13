package com.example.eatfast;
import com.example.eatfast.Database.Database;
import com.example.eatfast.Model.Order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import com.example.eatfast.Model.GroupedOrders;

public class CustomFragmentAdapter extends BaseAdapter implements ListAdapter {

    Database db;

    private ArrayList<GroupedOrders> list = new ArrayList<GroupedOrders>();
    private Context context;

    public CustomFragmentAdapter(ArrayList<GroupedOrders> list, Context context){
        db = new Database(context);
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public GroupedOrders getItem(int pos){
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
        ListView listView = (ListView)view.findViewById(R.id.listV);

        GroupedOrders groupedOrders = list.get(position);

        listItemText.setText("ORDER NR: "+groupedOrders.getId());

        return view;
    }




}


