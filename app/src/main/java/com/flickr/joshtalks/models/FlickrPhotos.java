package com.flickr.joshtalks.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FlickrPhotos implements Parcelable {

    @Expose
    @SerializedName("page")
    Integer page;
    @Expose
    @SerializedName("pages")
    Integer pages;

    @Expose
    @SerializedName("perpage")
    Integer perpage;

    @Expose
    @SerializedName("total")
    Integer total;

    @Expose
    @SerializedName("photo")
    List<Photo> photoList;

    protected FlickrPhotos(Parcel in) {
        if (in.readByte() == 0) {
            page = null;
        } else {
            page = in.readInt();
        }
        if (in.readByte() == 0) {
            pages = null;
        } else {
            pages = in.readInt();
        }
        if (in.readByte() == 0) {
            perpage = null;
        } else {
            perpage = in.readInt();
        }
        if (in.readByte() == 0) {
            total = null;
        } else {
            total = in.readInt();
        }
        photoList = in.createTypedArrayList(Photo.CREATOR);
    }

    public static final Creator<FlickrPhotos> CREATOR = new Creator<FlickrPhotos>() {
        @Override
        public FlickrPhotos createFromParcel(Parcel in) {
            return new FlickrPhotos(in);
        }

        @Override
        public FlickrPhotos[] newArray(int size) {
            return new FlickrPhotos[size];
        }
    };

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getPerpage() {
        return perpage;
    }

    public void setPerpage(Integer perpage) {
        this.perpage = perpage;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (page == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(page);
        }
        if (pages == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(pages);
        }
        if (perpage == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(perpage);
        }
        if (total == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(total);
        }
        parcel.writeTypedList(photoList);
    }
}
