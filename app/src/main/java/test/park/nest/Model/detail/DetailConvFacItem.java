package test.park.nest.Model.detail;

import com.google.gson.annotations.SerializedName;

public class DetailConvFacItem {

    @SerializedName("name")
    private String name = "";

    @SerializedName("img")
    private String img = "";


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
