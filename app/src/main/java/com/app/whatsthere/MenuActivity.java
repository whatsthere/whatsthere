package com.app.whatsthere;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.app.whatsthere.api.PictureApi;
import com.squareup.picasso.Picasso;

import java.io.InputStream;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MenuActivity extends Activity  {

    Button btnTakePic;
    Button btnWhatsThere;
    static int  REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnTakePic = (Button)findViewById(R.id.btnTakePic);
        btnWhatsThere = (Button)findViewById(R.id.btnWhatsThere);

        btnTakePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }

            }
        });

        btnWhatsThere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String url = "http://www.avajava.com";
//                RestAdapter restAdapter = new RestAdapter.Builder()
//                        .setEndpoint(url).build();
//                PictureApi pictureApi = restAdapter.create(PictureApi.class);
//                pictureApi.getPhotos("avajavalogo.jpg",new Callback<Response>() {
//                    @Override
//                    public void success(Response response, Response response2) {
//
//                        try
//                        {
//                            InputStream is = response.getBody().in();
//
//                            Bitmap bmp = BitmapFactory.decodeStream(is);
//                            ImageView img = (ImageView)findViewById(R.id.img);
//                            img.setImageBitmap(bmp);
//
//                        }
//                        catch (Exception e)
//                        {
//
//                        }
//                    }
//
//                    @Override
//                    public void failure(RetrofitError retrofitError) {
//
//                    }
//                });
                ImageView img = (ImageView)findViewById(R.id.img);

                Picasso.with(MenuActivity.this).load("http://i.imgur.com/DvpvklR.png").into(img);
            }
        });

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
}
