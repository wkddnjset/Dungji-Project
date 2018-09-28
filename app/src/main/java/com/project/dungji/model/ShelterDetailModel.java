package com.project.dungji.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import com.project.dungji.model.detail.DetailConvFacItem;
import com.project.dungji.model.detail.DetailImageItem;
import com.project.dungji.model.detail.DetailInfoItem;
import com.project.dungji.model.detail.DetailRuleItem;

public class ShelterDetailModel {

    @SerializedName("detail")
    private DetailInfoItem detailInfoItem;

    @SerializedName("image")
    private ArrayList<DetailImageItem> images = new ArrayList<>();

    @SerializedName("convFac")
    private ArrayList<DetailConvFacItem> convFacs = new ArrayList<>();

    @SerializedName("rule")
    private ArrayList<DetailRuleItem> rules = new ArrayList<>();



    public DetailInfoItem getDetailInfoItem() {
        return detailInfoItem;
    }

    public void setDetailInfoItem(DetailInfoItem detailInfoItem) {
        this.detailInfoItem = detailInfoItem;
    }

    public ArrayList<DetailImageItem> getImages() {
        return images;
    }

    public void setImages(ArrayList<DetailImageItem> images) {
        this.images = images;
    }

    public ArrayList<DetailConvFacItem> getConvFacs() {
        return convFacs;
    }

    public void setConvFacs(ArrayList<DetailConvFacItem> convFacs) {
        this.convFacs = convFacs;
    }

    public ArrayList<DetailRuleItem> getRules() {
        return rules;
    }

    public void setRules(ArrayList<DetailRuleItem> rules) {
        this.rules = rules;
    }
}
