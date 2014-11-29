package com.app.whatsthere;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.whatsthere.api.PictureApi;
import com.app.whatsthere.api.PictureUploader;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
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
    private   Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captured_image_preview);
        btnUploadPic = (Button)findViewById(R.id.btnUploadPic);
        imageView = (ImageView)findViewById(R.id.previewImage);

          imageUri  = Uri.parse(getIntent().getExtras().getString(IMAGE_URI));
//
//
//        imageView.setImageBitmap(imageBitmap);

        try {
            // hide video preview


            // bimatp factory
            BitmapFactory.Options options = new BitmapFactory.Options();

            // downsizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 8;

              bitmap = BitmapFactory.decodeFile(imageUri.getPath(),
                    options);

            imageView.setImageBitmap(bitmap);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        btnUploadPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                RestAdapter restAdapter = new RestAdapter
//                        .Builder()
//                        .setEndpoint("http://194.90.78.215:80/")
//                        .setLogLevel(RestAdapter.LogLevel.FULL)
//                        .build();
//
//               PictureApi pictureApi = restAdapter.create(PictureApi.class);
//
//
//                TypedFile typedFile = new TypedFile("image/jpeg",new File(imageUri.getPath()));
//                pictureApi.uploadFile(new TypedString("Marat"),typedFile,new Callback<Response>() {
//                    @Override
//                    public void success(Response response, Response response2) {
//
//                        if(response.getStatus()==200){
//
//                            Toast.makeText(CapturedImagePreviewActivity.this,"image uploaded successfully",Toast.LENGTH_LONG);
//                        }
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//
//                        Toast.makeText(CapturedImagePreviewActivity.this,"error in image upload ",Toast.LENGTH_LONG);
//                    }
//                });

                File file = new File(imageUri.getPath());

                PictureUploader.PictureRequestBuilder builder = new PictureUploader.PictureRequestBuilder ();


                builder.addPart("file",file)
                        .addPart("name",new SimpleDateFormat("yyyyMMdd_HHmmss",
                                Locale.getDefault()).format(new Date()))
                        .setUrl("http://194.90.78.215:80/data/image/upload");

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
