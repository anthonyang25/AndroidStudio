package com.delta.listviews;

/**
 * Created by spawrks on 2/13/14.
 */
public class Place {
    //bean -> store info of object (in each row of the list)
    public String mNameOfPlace;
    public int mZipCode;
    public String mNameOfImage;
    public String mPopup;

    public Place (String startNameOfPlace, int startZipCode,String startNameOfImage, String startPopup ){

        this.mNameOfPlace = startNameOfPlace;
        this.mZipCode = startZipCode;
        this.mNameOfImage = startNameOfImage;
        this.mPopup = startPopup;

    }

}