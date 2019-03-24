package com.example.eatfast.Menu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.eatfast.Adapters.CustomAdapter;
import com.example.eatfast.Menu.MenuActivity;
import com.example.eatfast.Model.FoodItem;
import com.example.eatfast.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MenuFragment extends Fragment {


    public MenuFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        int position = getArguments().getInt("position");
        String category = new String();
        if (position == 0) {
            category = "Nuggets";
        }
        if (position == 1) {
            category = "Burgers";
        }
        if (position == 2) {
            category = "Other";
        }

        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        final ListView listView = view.findViewById(R.id.fragmentList);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Category").child(category);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<FoodItem> FoodItems = new ArrayList<>();
                for (DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()) {
                    FoodItem FoodItem = uniqueKeySnapshot.getValue(FoodItem.class);
                    FoodItems.add(FoodItem);
                }
                CustomAdapter adapter = new CustomAdapter(FoodItems, getActivity());
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
