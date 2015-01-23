package com.app.whatsthere;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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


public class SearchTagFragment extends Fragment implements TextWatcher {

    private ListView SearchListView;
    private EditText hashTagSearchTextEdit;
    private int index;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,container,false);
        SearchListView = (ListView)view.findViewById(R.id.whatsThereListView);
        hashTagSearchTextEdit = (EditText)view.findViewById(R.id.hashTagSearchTextEdit);
        hashTagSearchTextEdit.addTextChangedListener(this);

        return view;
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        String hasTagText = s.toString();
        RestAdapter adapter = new RestAdapter.Builder().
                setEndpoint(Constants.SERVER_URL).
                build();

        PictureApi pictureApi = adapter.create(PictureApi.class);
        Map<String,String> map = new HashMap<String, String>();
        map.put("hashtag",hasTagText);
        map.put("offset",String.valueOf(index));
        pictureApi.getImages(map,new Callback<TagImagesMap>() {
            @Override
            public void success(TagImagesMap images, Response response) {
                TagImagesMap tagImagesMap = images;
                HashTagListAdapter adapter = new HashTagListAdapter(SearchTagFragment.this.getActivity(),tagImagesMap);
                SearchListView.setAdapter(adapter);

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
