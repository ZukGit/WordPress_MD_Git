package com.and.zmain_life.base;

import android.os.Parcelable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;


public class CommPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<? extends Fragment> items;
    private String[] mTitles;

    public CommPagerAdapter(FragmentManager fm, ArrayList< ? extends Fragment> items, String[] mTitles) {
        super(fm);
        this.items = items;
        this.mTitles = mTitles;
    }

    @Override
    public int getCount() {
        return items.size()==0 ? 0 :items.size();
    }

    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;

    }
    @Override
    public Parcelable saveState() {
        return null;
    }
}