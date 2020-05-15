package com.example.musiccentral;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class MySong implements Parcelable {

    public String Name;
    public String Artist;
    public String URL;
    public Bitmap Image;

    public MySong() {
    }

    public MySong(Parcel parcel) {
        this.Name = parcel.readString();
        this.Artist = parcel.readString();
        this.URL = parcel.readString();
        this.Image = parcel.readParcelable(getClass().getClassLoader()); // changed from null here
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //change here (3)
        dest.writeString(Name);
        dest.writeString(Artist);
        dest.writeString(URL);
        dest.writeParcelable(Image, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Method to recreate a Catch from a Parcel
    public static Creator<MySong> CREATOR = new Creator<MySong>() {

        @Override
        public MySong createFromParcel(Parcel source) {
            return new MySong(source);
        }

        @Override
        public MySong  [] newArray(int size) {
            return new MySong  [size];
        }

    };
}