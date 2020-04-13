package com.flickr.joshtalks.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlickrModel implements Parcelable {
    @Expose
    @SerializedName("photos")
    FlickrPhotos page;

    protected FlickrModel(Parcel in) {
        page = in.readParcelable(FlickrPhotos.class.getClassLoader());
    }

    public static final Creator<FlickrModel> CREATOR = new Creator<FlickrModel>() {
        @Override
        public FlickrModel createFromParcel(Parcel in) {
            return new FlickrModel(in);
        }

        @Override
        public FlickrModel[] newArray(int size) {
            return new FlickrModel[size];
        }
    };

    public FlickrPhotos getPage() {
        return page;
    }

    public void setPage(FlickrPhotos page) {
        this.page = page;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(page, i);
    }
}
