package com.app.whatsthere;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.app.whatsthere.api.PictureApi;
import com.app.whatsthere.customcomponents.HashTagListAdapter;
import com.app.whatsthere.datamodels.TagImagesMap;

import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {

    ListView hashTagListView;

    public FeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_feed, container, false);

        return  view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hashTagListView = (ListView)view.findViewById(R.id.whatsThereListViewFeed);

        RestAdapter adapter = new RestAdapter.Builder().
                setEndpoint(Constants.SERVER_URL).
                build();
        PictureApi pictureApi = adapter.create(PictureApi.class);
        Map<String,String> map = new HashMap<String, String>();
        map.put("hashtag","");
        map.put("offset","0");
        pictureApi.getImages(map, new Callback<TagImagesMap>() {
            @Override
            public void success(TagImagesMap images, Response response) {
                TagImagesMap tagImagesMap = images;


                HashTagListAdapter adapter = new HashTagListAdapter(FeedFragment.this.getActivity(), tagImagesMap);
                hashTagListView.setAdapter(adapter);

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
