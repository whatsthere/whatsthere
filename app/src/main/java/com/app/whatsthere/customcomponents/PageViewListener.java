package com.app.whatsthere.customcomponents;

import android.app.ActionBar;
import android.support.v4.view.ViewPager;

/**
 * Created by maratibragimov on 1/1/15.
 */
public class PageViewListener implements ViewPager.OnPageChangeListener
{
    ActionBar mActionBar;
    public  PageViewListener(ActionBar actionBar){

        mActionBar = actionBar;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


    }

    @Override
    public void onPageSelected(int position) {
        mActionBar.selectTab(mActionBar.getTabAt(position));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}