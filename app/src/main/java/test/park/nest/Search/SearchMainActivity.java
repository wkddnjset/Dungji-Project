package test.park.nest.Search;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.park.nest.Adapter.search.SearchMainRecyclerAdapter;
import test.park.nest.BaseActivity;
import test.park.nest.Dialog.ProgressBarDialog;
import test.park.nest.Model.search.SearchRecyclerModel;
import test.park.nest.Model.search.SearchRecyclerModel.SearchFilterModel;
import test.park.nest.Network.RetrofitApiCallback;
import test.park.nest.R;

public class SearchMainActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.search_area_list)
    RecyclerView mRecyclerAreaView;

    @BindView(R.id.search_option_list)
    RecyclerView mRecyclerOptionView;

    @BindView(R.id.search_text_btn)
    TextView mTxtSearchBtn;

    private SearchMainRecyclerAdapter mAreaListAdapter;
    private SearchMainRecyclerAdapter mOptionListAdapter;

    private ArrayList<Integer> filterConvFac = new ArrayList<Integer>();
    private ArrayList<Integer> filterSido = new ArrayList<Integer>();


    private View.OnClickListener listViewItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            SearchFilterModel data = (SearchFilterModel)v.getTag();

            if(v.isSelected()){
                v.setSelected(false);

                if(data.getDataType() == 0)
                    filterConvFac.remove((Object)data.getId());

                else
                    filterSido.remove((Object)data.getId());
            }
            else{
                v.setSelected(true);

                if(data.getDataType() == 0)
                    filterConvFac.add(data.getId());

                else
                    filterSido.add(data.getId());
            }

        }
    };

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

        mAreaListAdapter.setItemClickListener(listViewItemClickListener);
        mAreaListAdapter.setDataType(0);

        mOptionListAdapter.setItemClickListener(listViewItemClickListener);
        mAreaListAdapter.setDataType(1);

        mRecyclerAreaView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerOptionView.setLayoutManager(new GridLayoutManager(this, 3));


        mRecyclerAreaView.setAdapter(mAreaListAdapter);
        mRecyclerOptionView.setAdapter(mOptionListAdapter);

        mTxtSearchBtn.setOnClickListener(this);

        initCallData();
    }


    private void initCallData(){

        mProgressDialog.show();

        networkClient.callGetSearchFilter(new RetrofitApiCallback() {
            @Override
            public void onError(Throwable t) {
                mProgressDialog.dismiss();
            }

            @Override
            public void onSuccess(int code, Object resultData) {
                mProgressDialog.dismiss();

                mAreaListAdapter.setDataList(((SearchRecyclerModel)resultData).getSidoList());
                mOptionListAdapter.setDataList(((SearchRecyclerModel)resultData).getConvFacList());

                mAreaListAdapter.notifyDataSetChanged();
                mOptionListAdapter.notifyDataSetChanged();

                filterSido.add(((SearchRecyclerModel)resultData).getSidoList().get(0).getId());
                filterConvFac.add(((SearchRecyclerModel)resultData).getConvFacList().get(0).getId());
            }

            @Override
            public void onFailed(int code, String msg) {
                mProgressDialog.dismiss();

            }
        });
    }



    @Override
    protected int getContentView() {
        return R.layout.activity_search_main;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.search_text_btn:

                Log.d("SERATCH CONV", filterConvFac.toString());
                Log.d("SERATCH SIDO", filterSido.toString());

                break;

        }
    }


}
