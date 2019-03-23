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
import android.widget.TextView;
import android.widget.Toast;

import com.example.eatfast.Database.Database;
import com.example.eatfast.Menu.MenuActivity;
import com.example.eatfast.Model.FoodItem;
import com.example.eatfast.R;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter implements ListAdapter {


    Database db;


    private ArrayList<FoodItem> list = new ArrayList<FoodItem>();
    private Context context;

    public CustomAdapter(){

    }

    public CustomAdapter(ArrayList<FoodItem> list, Context context){
        db = new Database(context);
        this.list = list;
        this.context = context;
    }

    public void counter(int amount) {

        MenuActivity.mCartItemCount = 0;
        for (int i = 0; i < amount; i++) {
            MenuActivity.mCartItemCount++;
        }
        if (MenuActivity.mCartItemCount == 0) {
            if (MenuActivity.textCartItemCount.getVisibility() != View.GONE) {
                MenuActivity.textCartItemCount.setVisibility(View.GONE);
            }
        } else {
            if (MenuActivity.textCartItemCount.getVisibility() != View.VISIBLE) {
                MenuActivity.textCartItemCount.setVisibility(View.VISIBLE);
            }
            MenuActivity.textCartItemCount.setText(String.valueOf(MenuActivity.mCartItemCount));
        }
    }

    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public FoodItem getItem(int pos){
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
            view = inflater.inflate(R.layout.activity_categorydetail, null);
        }
        TextView listItemText = (TextView)view.findViewById(R.id.orderItem);
        FoodItem text = list.get(position);
        listItemText.setText(text.getProductName() +" "+  text.getPrice()+":-");

        Button addBtn = (Button)view.findViewById(R.id.addBtn);
        Button infoBtn = (Button)view.findViewById(R.id.infoBtn);



        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                addToCart(position);

            }
        });

        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
                final FoodItem o = getItem(position);
                alertDialog.setMessage(o.getProductName() + " " + o.getPrice() + " :-" + "\n\n"  + o.getFoodInfo() + "\n\n" + o.getCalories() + " Calories");


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

    public void addToCart(int pos){

        FoodItem testFoodItem = getItem(pos);
        db.insertData(testFoodItem.getProductName(), testFoodItem.getPrice());
        Toast.makeText(context, "Placed " + testFoodItem.getProductName() + " in cart", Toast.LENGTH_LONG).show();
        MenuActivity.mCartItemCount++;
        counter(MenuActivity.mCartItemCount);

    }



}


