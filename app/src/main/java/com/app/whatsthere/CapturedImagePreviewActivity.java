package com.app.whatsthere;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.app.whatsthere.Constants;
import com.app.whatsthere.api.PictureApi;
import com.app.whatsthere.api.PictureUploader;
import com.app.whatsthere.locationservice.WtLocationServices;
import com.facebook.Session;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedString;


public class CapturedImagePreviewActivity extends Activity {

    Button btnUploadPic;
    ImageView imageView;
    public static String IMAGE_URI = "imageUri";
    private Uri imageUri;
    private EditText hashTagTextEdit;
    private Bitmap bitmap;
    private Animation scaleAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capturedimage_preview);
        btnUploadPic = (Button)findViewById(R.id.btnUploadPic);
        imageView = (ImageView)findViewById(R.id.previewImage);
        imageView.setVisibility(View.INVISIBLE);
        hashTagTextEdit = (EditText)findViewById(R.id.hashTagTextEdit);
        imageUri  = Uri.parse(getIntent().getExtras().getString(IMAGE_URI));


       Picasso.with(this).load(imageUri).into(imageView,new com.squareup.picasso.Callback() {
           @Override
           public void onSuccess() {
               imageView.setVisibility(View.VISIBLE);
                imageView.setAnimation(scaleAnimation);
                imageView.animate();
           }

           @Override
           public void onError() {

           }
       });

        btnUploadPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                PictureUploader.PictureRequestBuilder builder = createUploadRequestBuilder();

                PictureUploader pictureUploader = new PictureUploader();
                pictureUploader.uploadImage(builder,new PictureUploader.ImageUploadCallback() {
                    @Override
                    public void onSuccess(int code, String description) {

                        Toast.makeText(CapturedImagePreviewActivity.this,description,Toast.LENGTH_SHORT).show();
                        CapturedImagePreviewActivity.this.finish();
                    }

                    @Override
                    public void onError(int code, String description) {

                        Toast.makeText(CapturedImagePreviewActivity.this,description,Toast.LENGTH_SHORT).show();
                        CapturedImagePreviewActivity.this.finish();
                    }
                });
            }
        });
    }

    private PictureUploader.PictureRequestBuilder createUploadRequestBuilder() {
        File file = new File(imageUri.getPath());
        String faceBookToken = Session.getActiveSession().getAccessToken();
        PictureUploader.PictureRequestBuilder builder = new PictureUploader.PictureRequestBuilder ();
        WtLocationServices wtLocationServices = WtLocationServices.getInstance(this);
        String date = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());

        String ts = date;

        Location bestLocation = wtLocationServices.getBestLocation();
        String formattedLocation = String.valueOf(bestLocation.getLatitude())+","+String.valueOf(bestLocation.getLongitude());
        builder.addPart("file",file)
                .addPart("location",formattedLocation)
                .addPart("name",hashTagTextEdit.getText().toString())
                .addPart("fbToken",faceBookToken)
                .addPart("timeStamp",ts)
                .setUrl(Constants.UPLOAD_IMAGE_API);
        return builder;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.captured_image_preview, menu);
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
}
