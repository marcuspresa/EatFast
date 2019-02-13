package com.example.eatfast;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabWidget;
import android.widget.TextView;

import com.example.eatfast.Model.Order;

import com.example.eatfast.Model.GroupedOrders;


import java.util.ArrayList;
import java.util.List;


public class FragOrders extends ListFragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragorders, container,
                false);

        Order o = new Order("Nuggest", "60"); //test order

        ArrayList<Order> li = new ArrayList<>();
        ArrayList<GroupedOrders> groupedOrdersList = new ArrayList<>();

        //testar med lokala ordrar
        li.add(o);
        li.add(o);

        GroupedOrders groupedOrders = new GroupedOrders("0", li);

        //testar lokala ordrar
        groupedOrdersList.add(groupedOrders);
        groupedOrdersList.add(groupedOrders);

        CustomFragmentAdapter adapter1 = new CustomFragmentAdapter(groupedOrdersList, getActivity());

        setListAdapter(adapter1);

        return rootView;
    }




}
