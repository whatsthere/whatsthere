package com.app.whatsthere;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by maratibragimov on 12/25/14.
 */
public class WtPagerFragmentsAdapter extends FragmentStatePagerAdapter {

    private Fragment[]fragments ;


    public  WtPagerFragmentsAdapter(FragmentManager fm,Fragment[]fragments){
        super(fm);
        this.fragments = fragments;
    }
    @Override
    public Fragment getItem(int i) {
        return fragments[i];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }


}
