package test.park.nest.Model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class ResponseHeaderModel {


    @SerializedName("code")
    private String code = "";

    @SerializedName("message")
    private String message = "";

    @SerializedName("data")
    private HashMap<String, Object> data = new HashMap<String, Object>();


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }
}
