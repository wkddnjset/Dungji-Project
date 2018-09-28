package com.project.dungji.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MainRecyclerModel implements Parcelable{


    @SerializedName("banner")
    private ArrayList<bannerItem> bannerList = null;


    @SerializedName("shelter")
    private ArrayList<shelterItem> shelterSimpleList = new ArrayList<>();

    protected MainRecyclerModel(Parcel in) {
        bannerList = in.createTypedArrayList(bannerItem.CREATOR);
    }

    public static final Creator<MainRecyclerModel> CREATOR = new Creator<MainRecyclerModel>() {
        @Override
        public MainRecyclerModel createFromParcel(Parcel in) {
            return new MainRecyclerModel(in);
        }

        @Override
        public MainRecyclerModel[] newArray(int size) {
            return new MainRecyclerModel[size];
        }
    };

    public ArrayList<bannerItem> getBannerList() {
        return bannerList;
    }

    public void setBannerList(ArrayList<bannerItem> bannerList) {
        this.bannerList = bannerList;
    }

    public ArrayList<shelterItem> getShelterSimpleList() {
        return shelterSimpleList;
    }

    public void setShelterSimpleList(ArrayList<shelterItem> shelterSimpleList) {
        this.shelterSimpleList = shelterSimpleList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(bannerList);

    }

    public static class shelterItem {

        @SerializedName("id")
        private int id = 0;

        @SerializedName("name")
        private String name = "";

        @SerializedName("address")
        private String address = "";

        @SerializedName("tel")
        private String tel = "";

        @SerializedName("remain_ppl")
        private int remain_ppl = 0;

        @SerializedName("guGunName")
        private String guGunName = "";

        @SerializedName("sidoName")
        private String sidoName = "";

        @SerializedName("img")
        private String img = "";

        @SerializedName("distance")
        private int distance = 0;

        @SerializedName("type")
        private int type = 0;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public int getRemain_ppl() {
            return remain_ppl;
        }

        public void setRemain_ppl(int remain_ppl) {
            this.remain_ppl = remain_ppl;
        }

        public String getGuGunName() {
            return guGunName;
        }

        public void setGuGunName(String guGunName) {
            this.guGunName = guGunName;
        }

        public String getSidoName() {
            return sidoName;
        }

        public void setSidoName(String sidoName) {
            this.sidoName = sidoName;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

    }

    public static class bannerItem  implements Parcelable{


        @SerializedName("name")
        private String name;

        @SerializedName("img")
        private String img;

        protected bannerItem(Parcel in) {
            name = in.readString();
            img = in.readString();
        }

        public static final Creator<bannerItem> CREATOR = new Creator<bannerItem>() {
            @Override
            public bannerItem createFromParcel(Parcel in) {
                return new bannerItem(in);
            }

            @Override
            public bannerItem[] newArray(int size) {
                return new bannerItem[size];
            }
        };



        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

            dest.writeString(name);
            dest.writeString(img);
        }
    }
}
