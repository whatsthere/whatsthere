package com.app.whatsthere.api;


import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.Streaming;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedString;

/**
 * Created by maratibragimov on 11/9/14.
 */
public interface PictureApi {


    @Multipart
    @POST("/data/image/upload")
    void uploadFile(@Part("name") TypedString name,@Part("file") TypedFile file,Callback<Response>callback);
}
