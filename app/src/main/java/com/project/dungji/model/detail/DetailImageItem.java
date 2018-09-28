package com.project.dungji.model.detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DetailImageItem implements Parcelable {

    @SerializedName("id")
    private int id = 0;

    @SerializedName("img")
    private String img = "";


    protected DetailImageItem(Parcel in) {
        id = in.readInt();
        img = in.readString();
    }

    public static final Creator<DetailImageItem> CREATOR = new Creator<DetailImageItem>() {
        @Override
        public DetailImageItem createFromParcel(Parcel in) {
            return new DetailImageItem(in);
        }

        @Override
        public DetailImageItem[] newArray(int size) {
            return new DetailImageItem[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(img);
    }
}
