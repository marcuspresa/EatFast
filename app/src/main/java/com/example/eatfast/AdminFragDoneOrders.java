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
import android.widget.ListView;
import android.widget.Toast;

import com.example.eatfast.Model.GroupedOrders;
import com.example.eatfast.Model.Order;

import java.util.ArrayList;

public class AdminFragDoneOrders extends ListFragment {
    ArrayList<Order> li = new ArrayList<>();
    ArrayList<GroupedOrders> groupedOrdersList = new ArrayList<>();
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragdoneorders, container,
                false);

        Order o = new Order("Nuggets", "45"); //test order

        //testar med lokala ordrar
        li.add(o);
        li.add(o);

        GroupedOrders groupedOrders = new GroupedOrders("227", li);
        GroupedOrders groupedOrders2 = new GroupedOrders("24", li);
        GroupedOrders groupedOrders3 = new GroupedOrders("63", li);

        //testar lokala ordrar
        groupedOrdersList.add(groupedOrders);
        groupedOrdersList.add(groupedOrders);
        groupedOrdersList.add(groupedOrders2);
        groupedOrdersList.add(groupedOrders3);

        CustomAdapterWithDeleteButton adapter1 = new CustomAdapterWithDeleteButton(groupedOrdersList, getActivity());

        setListAdapter(adapter1);

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
