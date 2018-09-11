package test.park.nest.Model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MainRecyclerModel {


    @SerializedName("banner")
    private ArrayList<bannerItem> bannerList = null;


    @SerializedName("shelter")
    private ArrayList<shelterItem> shelterSimpleList = new ArrayList<>();

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


    public static class bannerItem {


        @SerializedName("name")
        private String name;

        @SerializedName("img")
        private String img;

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
    }
}
