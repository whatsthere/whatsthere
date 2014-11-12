package com.app.whatsthere.customcomponents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.app.whatsthere.R;
import com.app.whatsthere.datamodels.WhatsthereListItem;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by maratibragimov on 11/12/14.
 */
public class WhatsthereListAdapter extends BaseAdapter {

    private  Context context;
    private  List <WhatsthereListItem> whatsThereDatalist;
    public WhatsthereListAdapter(Context context,List<WhatsthereListItem> data){
        this.context = context;
        this.whatsThereDatalist = data;
    }
    @Override
    public int getCount() {
        if(this.whatsThereDatalist!= null)
         return this.whatsThereDatalist.size();
        else
            return 0;
    }

    @Override
    public Object getItem(int i) {
        if(this.whatsThereDatalist!= null)
            return whatsThereDatalist.get(i);
        else
            return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if(view == null){

            WhatsthereListItemView whatsthereListItemView =(WhatsthereListItemView) inflater.inflate(R.layout.whatsthere_list_item,null);
            ImageView imageView =(ImageView) whatsthereListItemView.getChildAt(1);
            Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(imageView);
            view = (View)whatsthereListItemView;
        }

        return view;
    }
}
