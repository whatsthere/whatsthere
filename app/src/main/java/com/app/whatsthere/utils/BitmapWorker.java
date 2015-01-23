package com.app.whatsthere.utils;

import android.graphics.Bitmap;
import android.os.AsyncTask;

/**
 * Created by maratibragimov on 1/18/15.
 */
public class BitmapWorker extends AsyncTask<String,Void,Bitmap> {

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    Callback callback;
    int width  = 768;
    int height  = 1024;
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }



    public  BitmapWorker(int width,int height){
        this.width = width;
        this.height = height;
    }
    @Override
    protected Bitmap doInBackground(String... params) {
       String path = params[0];


        return ImageUtils.decodeSampledBitmapFromResource(path,width,height);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        if(callback!=null){
            callback.onBitmapProcessingDone(bitmap);
        }
    }


   public interface Callback{
       public void onBitmapProcessingDone(Bitmap bitmap);
   }
}
