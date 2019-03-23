package com.example.eatfast.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.eatfast.Database.Database;
import com.example.eatfast.Model.Order;
import com.example.eatfast.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CustomAdapterWithDeleteButton extends BaseAdapter implements ListAdapter {

    Database db;

    private ArrayList<Order> list = new ArrayList<Order>();
    private Context context;

    public CustomAdapterWithDeleteButton(ArrayList<Order> list, Context context){
        db = new Database(context);
        this.list = list;
        this.context = context;
    }

    //android adapter notify activity google
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

    public void delete(Order o){
        list.remove(o);
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.admin_order_layout, null);
        }

        TextView listItemText = (TextView)view.findViewById(R.id.FoodItem);
        ListView listView = (ListView)view.findViewById(R.id.list);
        final Button deleteBtn = (Button)view.findViewById(R.id.deleteBtn);


        Order order = list.get(position);

        listItemText.setText("ORDER NR: "+ order.getId());

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
                Order order = list.get(position);
                order.getId();

                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref = database.getInstance().getReference("Orders").child(order.getId()).child("status");

                alertDialog.setTitle("Mark as done?");

                alertDialog.setPositiveButton(
                        "Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ref.setValue("Done");
                                Order o = list.get(position);
                                delete(o);
                            }
                        }
                );
                alertDialog.show();
            }
        });

        return view;
    }
}
