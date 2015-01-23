package com.app.whatsthere.datamodels;

import java.util.HashMap;

/**
 * Created by maratibragimov on 1/6/15.
 */
public class TagImagesMap {

    HashMap<String,Images> hashTag;
    public HashMap<String, Images> getHashTag() {
        return hashTag;
    }

    public void setHashTag(HashMap<String, Images> hashTag) {
        this.hashTag = hashTag;
    }


}
