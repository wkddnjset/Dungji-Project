package test.park.nest.Activitiy;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.park.nest.Adapter.ShelterDetailViewAdapter;
import test.park.nest.R;

public class ShelterDetailActivity extends BaseActivity{

    @BindView(R.id.detail_list)
    RecyclerView shelterDetailView;


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

    }
}
