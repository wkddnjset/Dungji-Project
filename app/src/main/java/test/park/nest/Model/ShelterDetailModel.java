package test.park.nest.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import test.park.nest.Model.detail.DetailConvFacItem;
import test.park.nest.Model.detail.DetailImageItem;
import test.park.nest.Model.detail.DetailInfoItem;
import test.park.nest.Model.detail.DetailRuleItem;

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
