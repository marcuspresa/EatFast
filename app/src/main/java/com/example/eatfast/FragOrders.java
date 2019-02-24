package com.example.eatfast;

import android.content.Intent;
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
import android.widget.Toast;

import com.example.eatfast.Model.Order;

import com.example.eatfast.Model.GroupedOrders;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class FragOrders extends ListFragment {

    ArrayList<Order> li = new ArrayList<>();
    ArrayList<GroupedOrders> groupedOrdersList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragorders, container,
                false);

        Order o = new Order("Nuggets", "60"); //test order

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

        setListAdapter(adapter1);

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
