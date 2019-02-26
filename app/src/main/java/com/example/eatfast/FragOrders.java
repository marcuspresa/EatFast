package com.example.eatfast;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import com.example.eatfast.Model.Order;
import com.example.eatfast.Model.GroupedOrders;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;


public class FragOrders extends ListFragment {



    ArrayList<Order> li = new ArrayList<>();
    ArrayList<GroupedOrders> groupedOrdersList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragorders, container,
                false);

        Order o = new Order("Nuggets", "60"); //test order
        SharedPreferences preferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        String uid = preferences.getString("user", "0");


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getInstance().getReference("Orders").child("user");
        ref.orderByChild("user").equalTo(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("apa");
              for(DataSnapshot datas: dataSnapshot.getChildren()){
                  ArrayList<Order> orders = new ArrayList<>();
                  Order order = datas.getValue(Order.class);
                  orders.add(order);
                  System.out.println(order.getProductName()+"apa");
              }

                //CustomAdapter adapter = new CustomAdapter(orders, getActivity());
                //setListAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //testar med lokala ordrar
        li.add(o);
        li.add(o);

        GroupedOrders groupedOrders = new GroupedOrders("0", li);
        GroupedOrders groupedOrders2 = new GroupedOrders("2", li);
        GroupedOrders groupedOrders3 = new GroupedOrders("3", li);

        //testar lokala ordrar
        groupedOrdersList.add(groupedOrders);
        groupedOrdersList.add(groupedOrders);
        groupedOrdersList.add(groupedOrders2);
        groupedOrdersList.add(groupedOrders3);

        CustomFragmentAdapter adapter1 = new CustomFragmentAdapter(groupedOrdersList, getActivity());


        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        super.onListItemClick(l, v, pos, id);
        Toast.makeText(getActivity(), "Item " + pos + " was clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(FragOrders.this.getContext(), DisplayOrderActivity.class);
        GroupedOrders o = groupedOrdersList.get(pos);
        intent.putExtra("KEY", o);
        startActivity(intent);
    }




}
