package com.app.whatsthere;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class DownloadedImagesPreviewActivity extends Activity {

    public static String URLS_LIST_KEY = "UrlsList";
    private ArrayList<String> urls;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloaded_images_preview);
        urls = getIntent().getStringArrayListExtra(URLS_LIST_KEY);
        mPager = (ViewPager)findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter();
        if(urls!=null){

            mPager.setAdapter(mPagerAdapter);
        }

       
    }


    private class ScreenSlidePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return urls.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView imageView = new ImageView(DownloadedImagesPreviewActivity.this);

            LayoutParams imageParams = new LayoutParams(
                    LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(imageParams);

            Picasso.
                    with(DownloadedImagesPreviewActivity.this).
                    load(urls.get(position)).
                    into(imageView);
            LinearLayout layout = new LinearLayout(DownloadedImagesPreviewActivity.this);
            layout.setOrientation(LinearLayout.VERTICAL);
            LayoutParams layoutParams = new LayoutParams(
                    LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
            layout.setBackgroundResource(R.drawable.rounded_corners_imageview);
            layout.setLayoutParams(layoutParams);
            layout.addView(imageView);
            container.addView(layout);

            return  layout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout)object);
        }
    }

}
