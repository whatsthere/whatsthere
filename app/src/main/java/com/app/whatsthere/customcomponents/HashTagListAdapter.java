package com.app.whatsthere.customcomponents;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.whatsthere.DownloadedImagesPreviewActivity;
import com.app.whatsthere.R;
import com.app.whatsthere.api.PictureDownloader;
import com.app.whatsthere.datamodels.Image;
import com.app.whatsthere.datamodels.Images;
import com.app.whatsthere.datamodels.TagImagesMap;

import java.util.Collection;
import java.util.List;

/**
 * Created by maratibragimov on 11/12/14.
 */
public class HashTagListAdapter extends BaseAdapter implements View.OnClickListener {

    private  Context context;
    private  Images [] whatsThereDatalist;
    private  int  lastPosition;
    public HashTagListAdapter(Context context, TagImagesMap data){
        this.context = context;
        Collection<Images> collection = null;
        if(data!=null) {
             collection = data.getHashTag().values();
            this.whatsThereDatalist = collection.toArray(new Images[0]);
        }
    }
    @Override
    public int getCount() {
        if(this.whatsThereDatalist!= null)
         return this.whatsThereDatalist.length;
        else
            return 0;
    }

    @Override
    public Object getItem(int i) {
        if(this.whatsThereDatalist!= null)
            return whatsThereDatalist[i];
        else
            return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder = null;
        GridView imageGridView = null;
        TextView hashTagLabel = null;
        if(view == null) {

            RelativeLayout wtListItemView = (RelativeLayout) inflater.inflate(R.layout.hashtag_list_item, null);
            viewHolder = new ViewHolder();
            imageGridView = (GridView)wtListItemView.findViewById(R.id.hashTagImagesGrid);
            hashTagLabel = (TextView)wtListItemView.findViewById(R.id.hashTagLabel);


            viewHolder.imagesGridView = imageGridView;
            viewHolder.textview  = hashTagLabel;
            view = (View)wtListItemView;
            view.setTag(viewHolder);
        }
        else{
          viewHolder = (ViewHolder)view.getTag();
           imageGridView     = viewHolder.imagesGridView;
           hashTagLabel  = viewHolder.textview;
        }

        Images images = (Images) whatsThereDatalist[position];
        HashTagImagesAdapter imagesAdapter = new HashTagImagesAdapter(context,images.getImages());
        imageGridView.setAdapter(imagesAdapter);
        hashTagLabel.setText(images.getImages().get(0).getHashTagText());


        Animation animation = null;
        if (position > lastPosition) {
                animation = new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF,
                        0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 1.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f);

                animation.setDuration(600);
                view.startAnimation(animation);
                lastPosition = position;
            }






        return view;
    }



    @Override
    public void onClick(View v) {

        Intent intent = new Intent(context,DownloadedImagesPreviewActivity.class);
        //intent.putStringArrayListExtra(DownloadedImagesPreviewActivity.URLS_LIST_KEY,data);
        context.startActivity(intent);
    }


    public class ViewHolder{
        GridView imagesGridView;
        TextView textview;
    }
}



