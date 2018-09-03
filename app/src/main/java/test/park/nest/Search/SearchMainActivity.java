package test.park.nest.Search;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.park.nest.Adapter.search.SearchMainRecyclerAdapter;
import test.park.nest.BaseActivity;
import test.park.nest.Network.RetrofitApiCallback;
import test.park.nest.R;

public class SearchMainActivity extends BaseActivity {


    @BindView(R.id.search_area_list)
    RecyclerView mRecyclerAreaView;

    @BindView(R.id.search_option_list)
    RecyclerView mRecyclerOptionView;

    @BindView(R.id.search_text_btn)
    TextView mTxtSearchBtn;


    private SearchMainRecyclerAdapter mAreaListAdapter;

    private SearchMainRecyclerAdapter mOptionListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setLeftIconRes(R.drawable.back);
        setLeftIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        super.onCreate(savedInstanceState);

        setLeftIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        ButterKnife.bind(this);


        mAreaListAdapter = new SearchMainRecyclerAdapter();
        mOptionListAdapter = new SearchMainRecyclerAdapter();

        mRecyclerAreaView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerOptionView.setLayoutManager(new GridLayoutManager(this, 3));


        mRecyclerAreaView.setAdapter(mAreaListAdapter);
        mRecyclerOptionView.setAdapter(mOptionListAdapter);

        mTxtSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                networkClient.callGetTest(new RetrofitApiCallback() {
                    @Override
                    public void onError(Throwable t) {
                        Toast.makeText(SearchMainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onSuccess(int code, Object resultData) {

                        Toast.makeText(SearchMainActivity.this, code + resultData.toString(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailed(int code, String msg) {
                        Toast.makeText(SearchMainActivity.this, code + msg, Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }


    @Override
    protected int getContentView() {
        return R.layout.activity_search_main;
    }
}
