package com.connorchurch.james.churchapp.activities;

import android.os.Parcel;
import android.os.Parcelable;

public class ChurchPhoto implements Parcelable {

    private String mUrl;
    private String mTitle;

    public ChurchPhoto(String url, String title) {
        mUrl = url;
        mTitle = title;
    }

    protected ChurchPhoto(Parcel in) {
        mUrl = in.readString();
        mTitle = in.readString();
    }

    public static final Creator<ChurchPhoto> CREATOR = new Creator<ChurchPhoto>() {
        @Override
        public ChurchPhoto createFromParcel(Parcel in) {
            return new ChurchPhoto(in);
        }

        @Override
        public ChurchPhoto[] newArray(int size) {
            return new ChurchPhoto[size];
        }
    };

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public static  ChurchPhoto[] getChurchPhotos() {

        return new ChurchPhoto[]{
                new ChurchPhoto("http://connorpresbyterianchurch.org/wp-content/uploads/Image-50.png", "Church1"),
                new ChurchPhoto("http://connorpresbyterianchurch.org/wp-content/uploads/IMG_1502-resized-image-600x450.jpg", "Church2"),
                new ChurchPhoto("http://connorpresbyterianchurch.org/wp-content/uploads/IMG_1503-resized-image-600x450.jpg", "Church3"),
                new ChurchPhoto("http://connorpresbyterianchurch.org/wp-content/uploads/IMG_1504-resized-image-600x450.jpg", "Church4"),
                new ChurchPhoto("http://connorpresbyterianchurch.org/wp-content/uploads/IMG_1505-resized-image-600x450.jpg", "Church5"),
                new ChurchPhoto("http://connorpresbyterianchurch.org/wp-content/uploads/IMG_1506-resized-image-600x450.jpg", "Church6"),
                new ChurchPhoto("http://connorpresbyterianchurch.org/wp-content/uploads/IMG_1507-resized-image-600x450.jpg", "Church7"),
                new ChurchPhoto("http://connorpresbyterianchurch.org/wp-content/uploads/IMG_1508-resized-image-600x450.jpg", "Church8"),
                new ChurchPhoto("http://connorpresbyterianchurch.org/wp-content/uploads/IMG_1510-resized-image-600x450.jpg", "Church9"),
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mUrl);
        parcel.writeString(mTitle);
    }
}
