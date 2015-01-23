package com.app.whatsthere.api;

import android.content.Context;
import android.widget.ImageView;

import com.app.whatsthere.Constants;
import com.app.whatsthere.R;
import com.app.whatsthere.datamodels.Image;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * Created by maratibragimov on 1/4/15.
 */
public class PictureDownloader {

 public static void downloadImage(Context context , ImageView imageView,String url){


     String [] fileComponents = url.split("\\/");
     String fileName =  fileComponents[fileComponents.length-1];
     String downloadUrl = Constants.SERVER_URL+"/data/image/download/byName?fileName="+fileName;


     Picasso.
             with(context).
             load(downloadUrl).
             into(imageView);


   }
}
