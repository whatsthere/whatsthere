package com.app.whatsthere.customcomponents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.app.whatsthere.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by maratibragimov on 11/12/14.
 */
public class WtTwoWayListAdapter extends BaseAdapter {


    List<String>urlList ;
    Context context;
    int cellResourceId;
    public WtTwoWayListAdapter(Context context, int resouceId, List<String> urlList){

        this.context = context;
        this.cellResourceId = resouceId;
        this.urlList = urlList;
    }
    @Override
    public int getCount() {
        if(urlList!=null)
            return urlList.size();
        else
            return 0;
    }

    @Override
    public Object getItem(int i) {
        if(urlList!=null)
         return urlList.get(i);
        else
         return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null)
        {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(98, 98));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    Picasso.
                    with(context).
                    load(urlList.get(i)).
                    resize(200, 200).
                    into(imageView);
            view = imageView;
        }

        return view;

    }
}
