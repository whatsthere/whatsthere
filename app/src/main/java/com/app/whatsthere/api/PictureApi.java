package com.app.whatsthere.api;




import com.app.whatsthere.datamodels.TagImagesMap;
import java.util.Map;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.QueryMap;


/**
 * Created by maratibragimov on 11/9/14.
 */
public interface PictureApi {



    @GET("/data/image/getImage/hashtag")
    void getImages(@QueryMap Map<String,String> queryMap,Callback<TagImagesMap>callback);


}
