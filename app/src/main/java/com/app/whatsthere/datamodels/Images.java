package com.app.whatsthere.datamodels;

import java.util.ArrayList;

/**
 * Created by maratibragimov on 12/30/14.
 */
public class Images {
    ArrayList<Image> images;

    public ArrayList<Image> getImages() {
       if(this.images==null)
           this.images = new ArrayList<Image>();
        return this.images;
    }

    public void addImage(Image image) {
        if (images == null) {
            images = new ArrayList<Image>();
        }
        images.add(image);
    }

}
