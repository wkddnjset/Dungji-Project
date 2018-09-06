package test.park.nest.Search;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.park.nest.Adapter.search.SearchMainRecyclerAdapter;
import test.park.nest.BaseActivity;
import test.park.nest.Model.search.SearchRecyclerModel;
import test.park.nest.Model.search.SearchRecyclerModel.SearchFilterModel;
import test.park.nest.Model.search.SearchResultModel;
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

    private ArrayList<String> tagSidoList = new ArrayList<>();
    private ArrayList<String> tagConvFacList = new ArrayList<>();

    private View.OnClickListener listViewItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            SearchFilterModel data = (SearchFilterModel)v.getTag();

            if(v.isSelected()){
                v.setSelected(false);

                if(data.getDataType() == 0){
                    filterConvFac.remove((Object)data.getId());
                    tagConvFacList.remove(data.getName());
                }
                else{
                    filterSido.remove((Object)data.getId());
                    tagSidoList.remove(data.getName());
                }

            }
            else{
                v.setSelected(true);

                if(data.getDataType() == 0){
                    filterConvFac.add(data.getId());
                    tagConvFacList.add(data.getName());
                }

                else{
                    filterSido.add(data.getId());
                    tagSidoList.add(data.getName());
                }
            }

        }
    };

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

        mAreaListAdapter = new SearchMainRecyclerAdapter();
        mOptionListAdapter = new SearchMainRecyclerAdapter();

        mAreaListAdapter.setItemClickListener(listViewItemClickListener);
        mAreaListAdapter.setDataType(0);

        mOptionListAdapter.setItemClickListener(listViewItemClickListener);
        mAreaListAdapter.setDataType(1);

        mRecyclerOptionView.setNestedScrollingEnabled(false);
        mRecyclerAreaView.setNestedScrollingEnabled(false);

        mRecyclerAreaView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerOptionView.setLayoutManager(new GridLayoutManager(this, 3));


        mRecyclerAreaView.setAdapter(mAreaListAdapter);
        mRecyclerOptionView.setAdapter(mOptionListAdapter);

        mTxtSearchBtn.setOnClickListener(this);

        initCallData();
    }


    /**
     * 초기 검색 필터 요청 함수
     */
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
    protected int getLeftIconRes() {
        return R.drawable.back;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.search_text_btn:

                Log.d("SERATCH CONV", TextUtils.join(",", filterConvFac));
                Log.d("SERATCH SIDO", TextUtils.join(",", filterSido));


                if(filterSido.size() == 0 && filterConvFac.size() == 0){
                    Toast.makeText(SearchMainActivity.this, getResources().getString(R.string.empty_search_filter), Toast.LENGTH_LONG).show();
                    return;
                }

                JsonObject innerObject = new JsonObject();
                innerObject.addProperty("pageNo", 1);
                innerObject.addProperty("filterConvFac", TextUtils.join(",", filterConvFac));
                innerObject.addProperty("filterSido", TextUtils.join(",", filterSido));

                JsonObject bodyObject = new JsonObject();
                bodyObject.add("filter", innerObject);

                mProgressDialog.show();

                networkClient.callPostSearchResult(new RetrofitApiCallback() {
                    @Override
                    public void onError(Throwable t) {
                        mProgressDialog.dismiss();

                    }

                    @Override
                    public void onSuccess(int code, Object resultData) {
                        mProgressDialog.dismiss();


                        Intent intent = new Intent(SearchMainActivity.this, SearchResultActivity.class);
                        intent.putExtra("pageNum", 1);
                        intent.putExtra("filterConvFac", filterConvFac);
                        intent.putExtra("filterSido", filterSido);
                        intent.putExtra("tagConvFacList", tagConvFacList);
                        intent.putExtra("tagSidoList", tagSidoList);
                        intent.putExtra("result", (SearchResultModel)resultData);
                        startActivity(intent);

                        finish();
                    }

                    @Override
                    public void onFailed(int code, String msg) {
                        mProgressDialog.dismiss();

                    }
                }, bodyObject);


                break;

        }
    }


}
