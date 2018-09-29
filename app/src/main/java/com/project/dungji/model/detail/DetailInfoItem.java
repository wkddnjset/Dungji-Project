package com.project.dungji.model.detail;

import com.google.gson.annotations.SerializedName;

public class DetailInfoItem {


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

    @SerializedName("introduction")
    private String introduction = "";

    @SerializedName("intro_title")
    private String intro_title = "";

    @SerializedName("longitude")
    private String longitude = "";

    @SerializedName("latitude")
    private String latitude = "";

    @SerializedName("guGunName")
    private String guGunName = "";

    @SerializedName("sidoName")
    private String sidoName = "";

    @SerializedName("type")
    private int type = 0;

    @SerializedName("sex")
    private int sex = 0;


    public String getIntro_title() {
        return intro_title;
    }

    public void setIntro_title(String intro_title) {
        this.intro_title = intro_title;
    }

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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
