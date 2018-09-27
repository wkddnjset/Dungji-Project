package test.park.nest.Activitiy;

import android.os.Bundle;
import android.view.View;

import test.park.nest.R;

public class ShelterDetailActivity extends BaseActivity{


    @Override
    protected int getContentView() {
        return super.getContentView();
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
    }
}
