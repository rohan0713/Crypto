package com.example.crypto;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class orderAdapter extends FragmentPagerAdapter {

    int tab_count;
    public orderAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tab_count = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new openFragment();
        }
            return new historyFragment();
    }

    @Override
    public int getCount() {
        return tab_count;
    }
}
