package com.app.whatsthere.api;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.app.whatsthere.utils.ImageUtils;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by maratibragimov on 11/29/14.
 */
public class PictureUploader {

    private String TAG = "100";
    private ImageUploadCallback callback;
    private PictureRequestBuilder builder;
    public  PictureUploader(){


    }

    public void uploadImage(PictureRequestBuilder builder , final ImageUploadCallback callback){

        this.callback = callback;
        this.builder  = builder;

       AsyncTask<Object,Void,Integer>  asyncTask = new AsyncTask<Object, Void, Integer>() {

           @Override
           protected Integer doInBackground(Object... params) {
               DefaultHttpClient httpClient = new DefaultHttpClient();

               HttpResponse response = null;
               PictureRequestBuilder builder = (PictureRequestBuilder)params [0];
               ImageUploadCallback callback = (ImageUploadCallback) params [1];

                   builder.build();
                   builder.httpPost.setEntity(builder.multiPartEntity);
                   Log.i(TAG, "request " +   builder.httpPost.getRequestLine());

                   try {
                       response = httpClient.execute(builder.httpPost);
                   } catch (ClientProtocolException e) {
                       // TODO Auto-generated catch block
                       e.printStackTrace();
                       callback.onError(1,"error in uploading image");
                   } catch (IOException e) {
                       // TODO Auto-generated catch block
                       e.printStackTrace();
                       callback.onError(1,"error in uploading image");
                   }
                   catch (Exception e){

                       e.printStackTrace();
                       callback.onError(1,"error in uploading image");
                   }

               return  response.getStatusLine().getStatusCode();
           }

           @Override
           protected void onPostExecute(Integer status) {
               super.onPostExecute(status);
               if(status==200)
                   callback.onSuccess(0,"Image uploaded successfully");
               else
                   callback.onError(1,"Error in image uploading process");
           }
       };

        asyncTask.execute(builder,callback);
    }

    public static class PictureRequestBuilder{


        MultipartEntity multiPartEntity;
        HttpPost httpPost;
        HashMap<String,Object>hashMap;
        public  PictureRequestBuilder(){
            multiPartEntity = new MultipartEntity();
            httpPost = new HttpPost();
            hashMap = new HashMap<String, Object>();
        }
        public PictureRequestBuilder setUrl(String url){
            httpPost.setURI(URI.create(url));
            return this;
        }


        public  PictureRequestBuilder addPart(String key,String value){

//            try {
//                multiPartEntity.addPart("name", new StringBody(value));
//            }catch (UnsupportedEncodingException e){
//                e.printStackTrace();
//            }
            hashMap.put(key,value);
            return  this;
        }



        public PictureRequestBuilder addPart(final String key, final File value){
            //multiPartEntity.addPart(key,new FileBody(value,"image/png"));
            hashMap.put(key,value);
            return this;
        }

        public void build (){

            for(Map.Entry<String,Object>entry:hashMap.entrySet()){

                if(entry.getValue() instanceof File){

                  File file =  ImageUtils.compressFile((File)entry.getValue(),50);


                   multiPartEntity.addPart(entry.getKey(),new FileBody(file,"image/jpeg"));
                }else
                {
                    try {
                        multiPartEntity.addPart("name", new StringBody(entry.getValue().toString()));
                    }catch (UnsupportedEncodingException e){
                        e.printStackTrace();
                    }
                }
            }
        }


    }



    public interface  ImageUploadCallback{
        public void onSuccess(int code , String description);
        public void onError(int code , String description);
    }
}
