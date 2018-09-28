package test.park.nest.Activitiy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.park.nest.Adapter.ShelterDetailViewAdapter;
import test.park.nest.Model.ShelterDetailModel;
import test.park.nest.Network.RetrofitApiCallback;
import test.park.nest.R;

public class ShelterDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.detail_list)
    RecyclerView shelterDetailView;

    @BindView(R.id.shelter_bottom_name)
    TextView bottomName;

    @BindView(R.id.shelter_bottom_call)
    ImageView bottomCall;

    @BindView(R.id.shelter_bottom_chat)
    ImageView bottomChat;

    private String callNumber;

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

        bottomCall.setOnClickListener(this);

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
