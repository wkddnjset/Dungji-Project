package com.project.dungji.activitiy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.project.dungji.R;
import com.project.dungji.adapter.ShelterDetailViewAdapter;
import com.project.dungji.model.ShelterDetailModel;
import com.project.dungji.network.RetrofitApiCallback;


import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShelterDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.parent_scroll)
    NestedScrollView mScrollView;

    @BindView(R.id.detail_list)
    RecyclerView shelterDetailView;

    @BindView(R.id.shelter_bottom_name)
    TextView bottomName;

    @BindView(R.id.shelter_bottom_call)
    ImageView bottomCall;

    @BindView(R.id.shelter_bottom_chat)
    ImageView bottomChat;

    @BindView(R.id.location_view)
    LinearLayout locationView;

    @BindView(R.id.shelter_addr)
    TextView shelterAddrText;

    @BindView(R.id.map_view)
    FrameLayout mapParent;

    private String callNumber;

    private MapView mapView;

    private ShelterDetailViewAdapter detailViewAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_shelter_detail;
    }

    @Override
    protected int getTitleRes() {
        return super.getTitleRes();
    }

    @Override
    protected int getLeftIconRes() {
        return R.drawable.back;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setLeftIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        shelterDetailView.setLayoutManager(linearLayoutManager);

        detailViewAdapter = new ShelterDetailViewAdapter();
        shelterDetailView.setAdapter(detailViewAdapter);
        shelterDetailView.setNestedScrollingEnabled(false);

        bottomCall.setOnClickListener(this);

        mapView = new MapView(this);

        mapView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                float x = event.getX();
                float y = event.getY();

                if (mapView.getHeight() > y && mapView.getWidth() > x) {
                    mScrollView.requestDisallowInterceptTouchEvent(true);
                } else {
                    mScrollView.requestDisallowInterceptTouchEvent(false);
                }
                return false;
            }
        });

        initCallData();
    }

    private void initCallData(){

        JsonObject innerObject = new JsonObject();
        innerObject.addProperty("id", getIntent().getIntExtra("shelterId", 0));

        JsonObject bodyObject = new JsonObject();
        bodyObject.add("shelter", innerObject);

        mProgressDialog.show();

        networkClient.callDetailShelterInfo(new RetrofitApiCallback() {
            @Override
            public void onError(Throwable t) {
                mProgressDialog.dismiss();
            }

            @Override
            public void onSuccess(int code, Object resultData) {
                mProgressDialog.dismiss();

                detailViewAdapter.setShelterDetailData((ShelterDetailModel)resultData);
                detailViewAdapter.notifyDataSetChanged();

                bottomName.setText(((ShelterDetailModel)resultData).getDetailInfoItem().getName());

                callNumber = ((ShelterDetailModel)resultData).getDetailInfoItem().getTel();

                MapPoint mp = MapPoint.mapPointWithGeoCoord(Double.parseDouble(((ShelterDetailModel)resultData).getDetailInfoItem().getLatitude()),
                        Double.parseDouble(((ShelterDetailModel)resultData).getDetailInfoItem().getLongitude()));

                MapPOIItem marker = new MapPOIItem();
                marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
                marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
                marker.setItemName(((ShelterDetailModel)resultData).getDetailInfoItem().getName());
                marker.setMapPoint(mp);

                mapView.setMapCenterPointAndZoomLevel(mp, 3, false);
                mapView.removeAllPOIItems();
                mapView.addPOIItem(marker);

                mapParent.removeAllViews();
                mapParent.addView(mapView);

                shelterAddrText.setText(((ShelterDetailModel)resultData).getDetailInfoItem().getAddress());

                locationView.setVisibility(View.VISIBLE);


                mScrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        mScrollView.scrollTo(0,0);
                    }
                });
            }

            @Override
            public void onFailed(int code, String msg) {
                mProgressDialog.dismiss();

            }
        }, bodyObject);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.shelter_bottom_call:

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + callNumber));
                startActivity(intent);

                break;

        }
    }


}
