package test.park.nest.Model.search;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import test.park.nest.Model.ResponseHeaderModel;

public class SearchRecyclerModel {


    @SerializedName("convFac")
    private ArrayList<SearchFilterModel> convFacList = null;


    @SerializedName("sido")
    private ArrayList<SearchFilterModel> sidoList = null;

    @SerializedName("type")
    private ArrayList<SearchFilterModel> typeList = null;

    @SerializedName("sex")
    private ArrayList<SearchFilterModel> sexList = null;


    public ArrayList<SearchFilterModel> getConvFacList() {
        return convFacList;
    }

    public void setConvFacList(ArrayList<SearchFilterModel> convFacList) {
        this.convFacList = convFacList;
    }

    public ArrayList<SearchFilterModel> getSidoList() {
        return sidoList;
    }

    public void setSidoList(ArrayList<SearchFilterModel> sidoList) {
        this.sidoList = sidoList;
    }


    public ArrayList<SearchFilterModel> getTypeList() {
        return typeList;
    }

    public void setTypeList(ArrayList<SearchFilterModel> typeList) {
        this.typeList = typeList;
    }

    public ArrayList<SearchFilterModel> getSexList() {
        return sexList;
    }

    public void setSexList(ArrayList<SearchFilterModel> sexList) {
        this.sexList = sexList;
    }

    public class SearchFilterModel {

        @SerializedName("id")
        private int id = 0;

        @SerializedName("name")
        private String name = "";

        @SerializedName("img")
        private String img = "";

        private int dataType = 0;

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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getDataType() {
            return dataType;
        }

        public void setDataType(int dataType) {
            this.dataType = dataType;
        }
    }
}
