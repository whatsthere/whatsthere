package com.app.whatsthere.customcomponents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.whatsthere.R;
import com.app.whatsthere.api.PictureDownloader;
import com.app.whatsthere.datamodels.Image;

import java.util.List;

/**
 * Created by maratibragimov on 1/12/15.
 */
public class HashTagImagesAdapter extends BaseAdapter {

    Context context;
    List<Image>imageList;
    public  HashTagImagesAdapter(Context context ,List<Image> imageList ){

        this.context = context;
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int position) {


        return imageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        ImageView imageView = null;
        GridImageViewHolder imageViewHolder = null;
        if(view == null){

            LinearLayout gridListItem = (LinearLayout) inflater.inflate(R.layout.hashtag_grid_item, null);
            imageViewHolder = new GridImageViewHolder();
            imageView = (ImageView)gridListItem.findViewById(R.id.gridImageItem);
            imageViewHolder.imageView = imageView;
            gridListItem.setTag(imageViewHolder);

            view = (View)gridListItem;

        }else
        {
            imageViewHolder = (GridImageViewHolder)view.getTag();
            imageView = imageViewHolder.imageView;

        }

        PictureDownloader.downloadImage(context, imageView, imageList.get(position).getLocationOnStorage());

        return view;
    }


    public class GridImageViewHolder{
        ImageView imageView;
    }
}
