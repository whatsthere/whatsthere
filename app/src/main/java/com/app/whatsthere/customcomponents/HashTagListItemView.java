package com.app.whatsthere.customcomponents;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.app.whatsthere.R;
import com.app.whatsthere.api.PictureDownloader;
import com.app.whatsthere.datamodels.Image;



import java.util.List;

/**
 * Created by maratibragimov on 11/12/14.
 */
public class HashTagListItemView extends LinearLayout {
    private List<Image>images;

    public ImageView getRandomImage() {
        return randomImage;
    }

    public void setRandomImage(ImageView randomImage) {
        this.randomImage = randomImage;
    }

    private ImageView randomImage;
    public HashTagListItemView(Context context) {
        super(context);


    }

    public HashTagListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public HashTagListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public List<Image> getImageUrls(){
        return  this.images;
    }
    public void  setImageUrls(List<Image> images){

        this.images = images;
        int imagesCount = images.size();
        int randomImageIndex =(int)(Math.random()%imagesCount);
        PictureDownloader.downloadImage(getContext(),randomImage,images.get(randomImageIndex).getLocationOnStorage());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
       


    }
}
