package test.park.nest.Model;

import android.app.Activity;

public class MainRecyclerModel {

    private Activity activity;
    private int id;
    private String name;
    private String address;
    private String tel;
    private int remain_ppl;
    private String guGunName;
    private String sidoName;
    private String img;
    private int distance;

    public MainRecyclerModel(Activity activity, int id, String name, String address, String tel, int remain_ppl, String guGunName, String sidoName, String img, int distance) {
        this.activity = activity;
        this.id = id;
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.remain_ppl = remain_ppl;
        this.guGunName = guGunName;
        this.sidoName = sidoName;
        this.img = img;
        this.distance = distance;
    }

    public Activity getActivity() {
        return activity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTel() {
        return tel;
    }

    public int getRemain_ppl() {
        return remain_ppl;
    }

    public String getGuGunName() {
        return guGunName;
    }

    public String getSidoName() {
        return sidoName;
    }

    public String getImg() {
        return img;
    }

    public int getDistance() {
        return distance;
    }
}
