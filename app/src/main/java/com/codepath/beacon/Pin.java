package com.codepath.beacon;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import com.parse.Parse;
import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Pin")
public class Pin extends ParseObject{
    private static final String TAG = "Pin";
    //Key References for Pin variables in PARSE.
    public static final String KEY_ID = "objectID";
    public static final String KEY_CREATOR = "creator";
    public static final String KEY_UPDATED = "updatedAt";
    public static final String KEY_CREATED = "createdAt";
    public static final String KEY_NAME = "pinName";
    public static final String KEY_CAPTION = "pinCaption";
    public static final String KEY_LOCATION = "pinGeoPoint";
    public static final String KEY_IMAGE = "pinImage";
    public static final String KEY_PUBLIC = "isPublic";
    public static final String KEY_ACCURACY = "pinAccuracy";

    public String getID(){
        return getString(KEY_ID);
    }

    public String getCreator(){
        return getString(KEY_CREATOR);
    }
/*
    public String getCreated(){ //Formatted as a date in PARSE server
        return getString(KEY_CREATED);
    }

    public String getUpdated(){
        return getString(KEY_UPDATED);
    }
 */
    public String getPinName(){
        return getString(KEY_NAME);
    }

    public void setPinName(String string){
        put(KEY_NAME, string);
    }

    public String getPinCaption(){
        return getString(KEY_CAPTION);
    }

    public void setPinCaption(String string){
        put(KEY_CAPTION, string);
    }

    public ParseGeoPoint getPinGeoPoint(){ //Check if this should be List
        return getParseGeoPoint(KEY_LOCATION);
    }

    public void setPinGeoPoint(ParseGeoPoint parseGeoPoint){ //Check if this should be List
        put(KEY_LOCATION, parseGeoPoint);
    }

    public ParseFile getPinImage(){
        return getParseFile(KEY_NAME);
    }

    public void setPinImage(ParseFile image){
        put(KEY_IMAGE, image);
    }

    public boolean getPinPrivacy(){
        return getBoolean(KEY_PUBLIC);
    }

    public void setPinPrivacy(boolean privacy){
        put(KEY_PUBLIC, privacy);
    }

    public double getPinAccuracy(){
        return getDouble(KEY_ACCURACY);
    }

    public void setPinAccuracy(double accurate){
        put(KEY_ACCURACY, accurate);
    }
}
