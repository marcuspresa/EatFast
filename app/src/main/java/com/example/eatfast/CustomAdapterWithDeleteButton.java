package com.example.eatfast;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
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
import com.example.eatfast.Model.GroupedOrders;
import com.example.eatfast.Model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

public class CustomAdapterWithDeleteButton extends BaseAdapter implements ListAdapter {

    Database db;

    private ArrayList<GroupedOrders> list = new ArrayList<GroupedOrders>();
    private Context context;

    public CustomAdapterWithDeleteButton(ArrayList<GroupedOrders> list, Context context){
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
            view = inflater.inflate(R.layout.admin_order_layout, null);
        }

        TextView listItemText = (TextView)view.findViewById(R.id.order);
        ListView listView = (ListView)view.findViewById(R.id.list);
        Button deleteBtn = (Button)view.findViewById(R.id.deleteBtn);

        GroupedOrders groupedOrders = list.get(position);

        listItemText.setText("ORDER NR: "+groupedOrders.getId());

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
                final GroupedOrders groupedOrders = list.get(position);
                groupedOrders.getId();
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref = database.getInstance().getReference("Orders").child(groupedOrders.getId()).child("status");
                ref.setValue("Done");
                notifyDataSetChanged();
                /*new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        database.getReference("Orders").child(groupedOrders.getId()).removeValue();
                    }
                }, 20000);*/

                alertDialog.setTitle("Mark as done?");
                alertDialog.setPositiveButton(
                        "Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }
                );
                alertDialog.show();

            }
        });

        
        return view;
    }
}
