package com.app.whatsthere;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.app.whatsthere.locationservice.WtLocationServices;
import com.app.whatsthere.utils.FileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MenuActivity extends Activity  {

    public static final String IMAGE_DIRECTORY_NAME ="WhatsThere";
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static int  REQUEST_IMAGE_CAPTURE = 1;
    Button btnTakePic;
    Button btnWhatsThere;
    TextView tvWhatsThere,tvTakePick;
    GridLayout grid ;
    Uri fileUri;

    Animation animationFadeIn,animationFadeOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        loadControls();
        loadAnimations();
        startLocationServices();

        btnTakePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

                    fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name

                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }

            }
        });

        btnWhatsThere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,WhatsThereActivty.class);
                startActivity(intent);
            }
        });

    }

    private void loadAnimations() {
        animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in_with_bounce);
        animationFadeOut = AnimationUtils.loadAnimation(this,R.anim.fade_out);
    }

    private void loadControls() {
        btnTakePic = (Button)findViewById(R.id.btnTakePic);
        btnWhatsThere = (Button)findViewById(R.id.btnWhatsThere);
        tvWhatsThere = (TextView)findViewById(R.id.tvWhatsThere);
        tvTakePick   = (TextView)findViewById(R.id.tvTakePic);
        grid = (GridLayout)findViewById(R.id.menuGrid);
        Typeface font = Typeface.createFromAsset(getAssets(), "RODUSround700.otf");
        tvWhatsThere.setTypeface(font);
        tvTakePick.setTypeface(font);
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
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
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
        grid.setAnimation(animationFadeIn);
        grid.animate();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        grid.setAnimation(animationFadeIn);
        grid.animate();
    }

    @Override
    protected void onStop() {
        super.onStop();
        grid.setAnimation(animationFadeOut);
        grid.animate();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode ==RESULT_OK){
            if(requestCode==REQUEST_IMAGE_CAPTURE){

                Intent imagePreviewIntent = new Intent(MenuActivity.this,CapturedImagePreviewActivity.class);

                imagePreviewIntent.putExtra(CapturedImagePreviewActivity.IMAGE_URI,fileUri.toString());
                startActivity(imagePreviewIntent);

            }

        }
    }
}
