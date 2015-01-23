package com.app.whatsthere.datamodels;


import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by maratibragimov on 12/30/14.
 */
public class Image {


    private long id;
    private String locationOnStorage;
    private String hashTagText;
    private String fbToken;
    private String formattedLocation;
    private  String timeOfCapture;
    private Timestamp modifyDate;


    public Image (String locationOnStorage, String hashTagText ,String fbToken,String formattedLocation,String timeOfCapture){
        this.setLocationOnStorage(locationOnStorage);
        this.setHashTagText(hashTagText);
        this.setFbToken(fbToken);
        this.setFormattedLocation(formattedLocation);
        this.setLocalDateTime(timeOfCapture);
        id = System.currentTimeMillis();
        Date date = new Date();
        modifyDate = new Timestamp(date.getTime());
    }

    public Image() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setLocalDateTime(String timeOfCapture) {
        this.timeOfCapture = timeOfCapture;
    }
    public String getTimeOfCapture() {
        return timeOfCapture;
    }

    public String getLocationOnStorage() {
        return locationOnStorage;
    }

    public void setHashTagText(String hashTagText) {
        this.hashTagText = hashTagText;
    }

    public void setLocationOnStorage(String fileName) {
        this.locationOnStorage = fileName;
    }

    public String getHashTagText() {
        return hashTagText;
    }

    public void setFbToken(String fbToken) {
        this.fbToken = fbToken;
    }
    public String getFbToken() {
        return fbToken;
    }

    public void setFormattedLocation(String formattedLocation) {
        this.formattedLocation = formattedLocation;
    }

    public String getFormattedLocation() {
        return formattedLocation;
    }

    public Timestamp getModifyDate() {
        return modifyDate;
    }

}