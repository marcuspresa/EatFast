package com.example.eatfast;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final ListView list = (ListView) findViewById(R.id.listView);

        ArrayList<String> category = new ArrayList<>(); //lägger till saker till listan
        category.add("Nuggets");
        category.add("Burgers");
        category.add("Alcoholic Drinks");
        category.add("Sodas");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, category);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //kollar vad man
            // trycker på och skickar det vidare
            // vet inte riktigt om detta är helt rätt för jag kan bara få antigen String eller postion id att
            //skickas med men inte båda. Om någon ser felet så får ni fixa det.
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Object category = (list.getItemAtPosition(position).toString());



                Intent intent = new Intent(MenuActivity.this, CategoryDetail.class);
                intent.putExtra("id", list.getItemAtPosition(position).toString());
                startActivity(intent);
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_cart);

        View actionView = MenuItemCompat.getActionView(menuItem);
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_cart) {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}

