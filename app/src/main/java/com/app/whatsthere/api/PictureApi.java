package com.app.whatsthere.api;


import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Streaming;

/**
 * Created by maratibragimov on 11/9/14.
 */
public interface PictureApi {
    @POST("")
    void uploadPhoto(Callback<Response>response);

    @GET("/images/{filename}")
    @Streaming
    void getPhotos( @Path("filename") String filename,Callback<Response> callback);
}
