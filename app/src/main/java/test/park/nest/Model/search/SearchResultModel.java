package test.park.nest.Model.search;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SearchResultModel implements Parcelable{


    @SerializedName("list")
    private ArrayList<SearchResultItem> shelterSimpleList = new ArrayList<>();

    @SerializedName("count")
    private int count = 0;

    protected SearchResultModel(Parcel in) {
        this.shelterSimpleList = in.createTypedArrayList(SearchResultItem.CREATOR);
        count = in.readInt();
    }

    public static final Creator<SearchResultModel> CREATOR = new Creator<SearchResultModel>() {
        @Override
        public SearchResultModel createFromParcel(Parcel in) {
            return new SearchResultModel(in);
        }

        @Override
        public SearchResultModel[] newArray(int size) {
            return new SearchResultModel[size];
        }
    };

    public ArrayList<SearchResultItem> getShelterSimpleList() {
        return shelterSimpleList;
    }

    public void setShelterSimpleList(ArrayList<SearchResultItem> shelterSimpleList) {
        this.shelterSimpleList = shelterSimpleList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(shelterSimpleList);
        dest.writeInt(count);
    }





    public static class SearchResultItem implements Parcelable{


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
        private int type = 0 ;


        protected SearchResultItem(Parcel in) {
            id = in.readInt();
            name = in.readString();
            address = in.readString();
            tel = in.readString();
            remain_ppl = in.readInt();
            guGunName = in.readString();
            sidoName = in.readString();
            img = in.readString();
            distance = in.readInt();
            type = in.readInt();
        }

        public static final Creator<SearchResultItem> CREATOR = new Creator<SearchResultItem>() {
            @Override
            public SearchResultItem createFromParcel(Parcel in) {
                return new SearchResultItem(in);
            }

            @Override
            public SearchResultItem[] newArray(int size) {
                return new SearchResultItem[size];
            }
        };

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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(name);
            dest.writeString(address);
            dest.writeString(tel);
            dest.writeInt(remain_ppl);
            dest.writeString(guGunName);
            dest.writeString(sidoName);
            dest.writeString(img);
            dest.writeInt(distance);
            dest.writeInt(type);
        }
    }
}
