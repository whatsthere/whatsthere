package com.app.whatsthere.customcomponents;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.List;

/**
 * Created by maratibragimov on 11/12/14.
 */
public class WhatsthereListItemView extends RelativeLayout {
    private List<String>imageUrls;

    public WhatsthereListItemView(Context context) {
        super(context);
    }

    public WhatsthereListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WhatsthereListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public List<String> getImageUrls(){
        return  this.imageUrls;
    }
    public void  setImageUrls(List<String> imageUrls){
        this.imageUrls = imageUrls;
    }
}
