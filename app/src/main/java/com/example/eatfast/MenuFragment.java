package com.example.eatfast;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.example.eatfast.Model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int position = getArguments().getInt("position");
        String category = new String();
        if(position == 0){
            category = "Burgers";
        }
        if(position == 1){
            category = "Nuggets";
        }
        if(position == 2){
            category = "Burgers";
        }
        View view =  inflater.inflate(R.layout.fragment_menu, container, false);
        final ListView listView = view.findViewById(R.id.fragmentList);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Category").child(category);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Order> orders = new ArrayList<>();
                for(DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()) {
                    Order order = uniqueKeySnapshot.getValue(Order.class);
                    orders.add(order);
                }
                CustomAdapter adapter = new CustomAdapter(orders, getActivity());
                listView.setAdapter(adapter);
                MenuActivity.spinner.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        return view;
    }

}
