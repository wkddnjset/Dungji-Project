package test.park.nest.Activitiy.Search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import butterknife.BindBitmap;
import butterknife.BindView;
import butterknife.ButterKnife;
import test.park.nest.Adapter.search.SearchMainRecyclerAdapter;
import test.park.nest.Activitiy.BaseActivity;
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

    @BindView(R.id.txt_all)
    TextView mTxtSexAll;

    @BindView(R.id.txt_man)
    TextView mTxtSexMan;

    @BindView(R.id.txt_woman)
    TextView mTxtSexWoman;

    @BindView(R.id.progress_type)
    SeekBar mTypeProgress;

    private SearchMainRecyclerAdapter mAreaListAdapter;
    private SearchMainRecyclerAdapter mOptionListAdapter;

    private ArrayList<Integer> filterConvFac = new ArrayList<Integer>();
    private ArrayList<Integer> filterSido = new ArrayList<Integer>();

    private ArrayList<String> tagSidoList = new ArrayList<>();
    private ArrayList<String> tagConvFacList = new ArrayList<>();

    private String tagSex = "";
    private String tagType = "";

    private View.OnClickListener listViewItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            SearchFilterModel data = (SearchFilterModel)v.getTag();


            if(data.getName().equals("전체")){

                if(v.isSelected()){
                    if(data.getDataType() == 0){

                        filterConvFac.clear();
                        tagConvFacList.clear();

                        mOptionListAdapter.setAllSelect(false);
                        mOptionListAdapter.notifyDataSetChanged();

                    }else{
                        filterSido.clear();
                        tagSidoList.clear();

                        mAreaListAdapter.setAllSelect(false);
                        mAreaListAdapter.notifyDataSetChanged();
                    }
                }else{

                    if(data.getDataType() == 0){
                        filterConvFac.clear();
                        tagConvFacList.clear();

                        tagConvFacList.add("편의시설 전체");

                        for(SearchFilterModel model : mOptionListAdapter.getDataList()){
                            filterConvFac.add(model.getId());
                        }

                        mOptionListAdapter.setAllSelect(true);
                        mOptionListAdapter.notifyDataSetChanged();

                    }else{
                        filterSido.clear();
                        tagSidoList.clear();

                        tagSidoList.add("지역 전체");

                        for(SearchFilterModel model : mAreaListAdapter.getDataList()){
                            filterSido.add(model.getId());
                        }

                        mAreaListAdapter.setAllSelect(true);
                        mAreaListAdapter.notifyDataSetChanged();
                    }
                }

            }else{
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

        mTypeProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(seekBar.getProgress() < 25){
                    seekBar.setProgress(0);
                    tagType = "일시";
                }
                else if(25 <= seekBar.getProgress() && seekBar.getProgress() < 75){
                    seekBar.setProgress(50);
                    tagType = "단기";
                }
                else{
                    seekBar.setProgress(100);
                    tagType = "중장기";
                }
            }
        });

        tagType = "일시";

        mTxtSexAll.setSelected(true);

        tagSex = "공용";

        mTxtSexAll.setOnClickListener(this);
        mTxtSexMan.setOnClickListener(this);
        mTxtSexWoman.setOnClickListener(this);

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
                innerObject.addProperty("convFac", TextUtils.join(",", filterConvFac));
                innerObject.addProperty("sido", TextUtils.join(",", filterSido));

                if(tagType.equals("일시"))
                    innerObject.addProperty("type", "1");
                else if(tagType.equals("단기"))
                    innerObject.addProperty("type", "2");
                else
                    innerObject.addProperty("type", "3");

                if(tagSex.equals("여자"))
                    innerObject.addProperty("sex", "1");
                else if(tagSex.equals("남자"))
                    innerObject.addProperty("sex", "2");
                else
                    innerObject.addProperty("sex", "3");


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

                        intent.putExtra("tagSex", tagSex);
                        intent.putExtra("tagType", tagType);

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


            case R.id.txt_all:

                mTxtSexAll.setSelected(true);
                mTxtSexMan.setSelected(false);
                mTxtSexWoman.setSelected(false);

                tagSex = "공용";

                break;

            case R.id.txt_man:

                mTxtSexAll.setSelected(false);
                mTxtSexMan.setSelected(true);
                mTxtSexWoman.setSelected(false);

                tagSex = "남자";

                break;

            case R.id.txt_woman:

                mTxtSexAll.setSelected(false);
                mTxtSexMan.setSelected(false);
                mTxtSexWoman.setSelected(true);

                tagSex = "여자";

                break;

        }
    }


}
