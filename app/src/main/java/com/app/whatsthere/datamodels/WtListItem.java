package com.app.whatsthere.datamodels;

import java.util.List;

/**
 * Created by maratibragimov on 11/12/14.
 */
public class WtListItem {

    private List<String> images;

    public   List<String>  getImages(){
        return  images;
    }

    public void  setImage( List<String>  images){

        this.images = images;
    }
}
