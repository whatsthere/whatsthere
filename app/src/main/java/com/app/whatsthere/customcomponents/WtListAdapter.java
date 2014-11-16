package com.app.whatsthere.customcomponents;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.app.whatsthere.R;
import com.app.whatsthere.datamodels.WtListItem;
import com.squareup.picasso.Picasso;

import org.lucasr.twowayview.TwoWayView;

import java.util.List;

/**
 * Created by maratibragimov on 11/12/14.
 */
public class WtListAdapter extends BaseAdapter {

    private  Context context;
    private  List <WtListItem> whatsThereDatalist;
    public WtListAdapter(Context context, List<WtListItem> data){
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if(view == null){

            WtListItemView wtListItemView =(WtListItemView) inflater.inflate(R.layout.whatsthere_list_item, null);
            LinearLayout imageListContainer = (LinearLayout) wtListItemView.findViewById(R.id.imageListContainer);
            WtListItem listItem = whatsThereDatalist.get(position);

            TextView titleTextVie =(TextView) wtListItemView.findViewById(R.id.hashTagLabel);
            Typeface font = Typeface.createFromAsset(context.getAssets(), "Bellerose.ttf");
            titleTextVie.setTypeface(font);
            List<String>data = listItem.getImages();

            for (int i = 0; i <  imageListContainer.getChildCount(); i++) {
                if(i<data.size()){
                    ImageView imageView = (ImageView)imageListContainer.getChildAt(i);
                    Picasso.
                            with(context).
                            load(data.get(i)).
                            resize(200, 200).
                            placeholder(R.drawable.placeholder).
                            into(imageView);

                }
            }

//            WtTwoWayListAdapter wtTwoWayListAdapter = new WtTwoWayListAdapter(context,R.layout.grid_cell,data);
//            imageGridView.setAdapter(wtTwoWayListAdapter);
            view = (View) wtListItemView;

        }

        return view;
    }
}
