package com.example.crypto;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class eAdapter extends FragmentPagerAdapter {

    int tab_count;
    public eAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tab_count = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new coinsFragment();
        }else if(position == 1){
            return new inrFragment();
        }else if(position == 2){
            return new usdtFragment();
        }
            return new btcFragment();
    }

    @Override
    public int getCount() {
        return tab_count;
    }
}
