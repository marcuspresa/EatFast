package com.example.eatfast.Fragments;

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

import com.example.eatfast.CustomAdapter.CustomFragmentAdapter;
import com.example.eatfast.Activities.DisplayOrderActivity;
import com.example.eatfast.Model.DoneOrder;
import com.example.eatfast.Model.GroupedOrders;
import com.example.eatfast.Model.Order;
import com.example.eatfast.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class AdminFragDoneOrders extends ListFragment {
    ArrayList<GroupedOrders> groupedOrdersList = new ArrayList<>();
    ArrayList<Order> orderList = new ArrayList<>();
    ArrayList<DoneOrder> doneOrders = new ArrayList<DoneOrder>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragdoneorders, container,
                false);
        final ListView listView = rootView.findViewById(R.id.fragmentList);
        SharedPreferences preferences = this.getActivity().getSharedPreferences("user", MODE_PRIVATE);
        final String uid = preferences.getString("user", "0");

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getInstance().getReference("Orders");



        final CustomFragmentAdapter customFragmentAdapter = new CustomFragmentAdapter(groupedOrdersList, getActivity());
        setListAdapter(customFragmentAdapter);


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()) {
                    final DoneOrder doneOrder = datas.getValue(DoneOrder.class);

                    if (doneOrder.getStatus().equals("Done")) {
                        doneOrder.setOrderID(datas.getKey());
                        final String orderNr = datas.getKey();
                        DatabaseReference foodRef = ref.child(orderNr);
                        DatabaseReference foodsRef = foodRef.child("foods");
                        foodsRef.addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    Order order = snapshot.getValue(Order.class);
                                    orderList.add(order);
                                }
                                doneOrder.setOrders(orderList);
                                doneOrders.add(doneOrder);
                                GroupedOrders groupedOrders = new GroupedOrders(doneOrders, orderNr);
                                groupedOrdersList.add(groupedOrders);
                                System.out.println("TESTING" + groupedOrdersList);
                                customFragmentAdapter.notifyDataSetChanged();

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }
                        });
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        super.onListItemClick(l, v, pos, id);
        Toast.makeText(getActivity(), "Item " + pos + " was clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AdminFragDoneOrders.this.getContext(), DisplayOrderActivity.class);
        GroupedOrders o = groupedOrdersList.get(pos);
        intent.putExtra("KEY", o);
        startActivity(intent);
    }
}
