package com.app.whatsthere;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ListView;
import com.google.android.gms.maps.MapFragment;
import com.app.whatsthere.customcomponents.MenuTabListener;
import com.app.whatsthere.customcomponents.PageViewListener;
import com.app.whatsthere.locationservice.WtLocationServices;
import com.app.whatsthere.utils.BitmapWorker;
import com.app.whatsthere.utils.FileUtils;
import com.app.whatsthere.utils.ImageUtils;


public class MenuActivity extends FragmentActivity {

    public static final String IMAGE_DIRECTORY_NAME ="WhatsThere";
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static int  REQUEST_IMAGE_CAPTURE = 1;


    private Fragment []fragments = {new FeedFragment(), new SearchTagFragment(),new HomeFragment()};
    private ViewPager mPager;
    private CharSequence mTitle;

    // slide menu items
    private String[] mNavMenuTitles;
    private TypedArray mNavMenuIcons;



    ActionBar mActionBar;

    Uri fileUri;

    Animation animationFadeIn,animationFadeOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        loadControls(savedInstanceState);

        startLocationServices();

    }



 private void takePicture(){

     Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
     if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

         fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
         takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name

         startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
     }

 }

    private void loadControls(Bundle savedInstanceState) {

        mActionBar = getActionBar();
        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(new WtPagerFragmentsAdapter(getSupportFragmentManager(), fragments));
        mPager.setOnPageChangeListener(new PageViewListener(mActionBar));





        // enabling action bar app icon and behaving it as toggle button
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);


        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        MenuTabListener tabListener = new MenuTabListener(mPager);

        int [] icon_ids = {R.drawable.ic_action_event,R.drawable.ic_action_search,R.drawable.ic_action_place};
        for (int i = 0; i < fragments.length; i++) {

            mActionBar.addTab(
                    mActionBar.newTab()
                            .setIcon(icon_ids[i])
                            .setTabListener(tabListener));
        }

        SlideMenuClickListener slideMenuClickListener = new SlideMenuClickListener();



    }


    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
           if(position==0)
               takePicture();
        }
    }


    /***
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items


        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls

    }

    public void startLocationServices(){
        WtLocationServices wtLocationServices = WtLocationServices.getInstance(this);

    }
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(FileUtils.getOutputMediaFile(type));
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if(item.getItemId()==R.id.take_picture){
            takePicture();
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        MenuActivity.this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode ==RESULT_OK){
            if(requestCode==REQUEST_IMAGE_CAPTURE){




                BitmapWorker bitmapWorker = new BitmapWorker(768,1024);
                bitmapWorker.setCallback(new BitmapWorker.Callback() {
                    @Override
                    public void onBitmapProcessingDone(Bitmap bitmap) {

                        Intent imagePreviewIntent = new Intent(MenuActivity.this,CapturedImagePreviewActivity.class);
                        ImageUtils.writeBitmapToFile(bitmap,fileUri.getPath());
                        imagePreviewIntent.putExtra(CapturedImagePreviewActivity.IMAGE_URI,fileUri.toString());
                        startActivity(imagePreviewIntent);

                    }
                });

                bitmapWorker.execute(fileUri.getPath());



            }

        }
    }





}
