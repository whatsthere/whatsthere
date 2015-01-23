package com.app.whatsthere.facebook;

import com.facebook.model.GraphUser;

/**
 * Created by maratibragimov on 1/13/15.
 */
public class FaceBookUser {
    private static FaceBookUser faceBookUser;
    public GraphUser getUser() {
        return user;
    }

    public void setUser(GraphUser user) {
        this.user = user;
    }

    private GraphUser user;
    private FaceBookUser(){

    }

     public static FaceBookUser getInstance(){

         if(faceBookUser == null)
             faceBookUser = new FaceBookUser();

         return faceBookUser;
    }
}
