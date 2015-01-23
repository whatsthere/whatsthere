package com.app.whatsthere.customcomponents;

import android.app.ActionBar;
import android.support.v4.view.ViewPager;

/**
 * Created by maratibragimov on 1/1/15.
 */
public class MenuTabListener implements ActionBar.TabListener {

    ViewPager mPager ;
    public  MenuTabListener(ViewPager pager){

        mPager = pager;
    }
    @Override
    public void onTabSelected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction ft) {

        int index =  tab.getPosition();
        mPager.setCurrentItem(index);
    }

    @Override
    public void onTabUnselected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction ft) {

    }
}