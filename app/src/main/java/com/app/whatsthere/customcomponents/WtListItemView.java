package com.app.whatsthere.customcomponents;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.app.whatsthere.R;


import java.util.List;

/**
 * Created by maratibragimov on 11/12/14.
 */
public class WtListItemView extends RelativeLayout {
    private List<String>imageUrls;
    private LinearLayout imagesGridView;
    public WtListItemView(Context context) {
        super(context);
        imagesGridView = (LinearLayout) findViewById(R.id.imageListContainer);
    }

    public WtListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WtListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public List<String> getImageUrls(){
        return  this.imageUrls;
    }
    public void  setImageUrls(List<String> imageUrls){
        this.imageUrls = imageUrls;

    }
}
