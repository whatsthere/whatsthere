package com.app.whatsthere.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.facebook.android.Util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by maratibragimov on 11/29/14.
 */
public class ImageUtils {

    public static File compressFile(File file,int quality)
    {

        Bitmap originalBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        originalBitmap.compress(Bitmap.CompressFormat.JPEG,quality,outputStream);
        File compressedFile = null;
        try {

            compressedFile = FileUtils.getOutputMediaFile(FileUtils.MEDIA_TYPE_IMAGE);
            FileOutputStream fileOutputStream = new FileOutputStream(compressedFile);
            outputStream.writeTo(fileOutputStream);


        }catch (FileNotFoundException e){

            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }


        return compressedFile;


    }

}
