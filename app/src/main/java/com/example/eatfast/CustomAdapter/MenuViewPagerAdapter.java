package com.example.eatfast.CustomAdapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.eatfast.Fragments.MenuFragment;

import java.util.ArrayList;

public class MenuViewPagerAdapter extends FragmentPagerAdapter {
    public MenuViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }



    @Override
    public Fragment getItem(int i) {
        MenuFragment menuFragment = new MenuFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", i);
        menuFragment.setArguments(bundle);
        return menuFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        ArrayList<String> menu = new ArrayList<>();
        menu.add("Hamburgers");
        menu.add("Chicken");
        menu.add("Other");
        return menu.get(position);
    }
}
