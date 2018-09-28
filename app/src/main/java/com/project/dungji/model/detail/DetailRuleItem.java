package com.project.dungji.model.detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DetailRuleItem implements Parcelable {

    @SerializedName("rule")
    private String rule  = "";

    @SerializedName("detail")
    private String detail = "";

    protected DetailRuleItem(Parcel in) {
        rule = in.readString();
        detail = in.readString();
    }

    public static final Creator<DetailRuleItem> CREATOR = new Creator<DetailRuleItem>() {
        @Override
        public DetailRuleItem createFromParcel(Parcel in) {
            return new DetailRuleItem(in);
        }

        @Override
        public DetailRuleItem[] newArray(int size) {
            return new DetailRuleItem[size];
        }
    };

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(rule);
        dest.writeString(detail);
    }
}
