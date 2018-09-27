package test.park.nest.Activitiy.Search;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.park.nest.Adapter.search.SearchResultRecyclerAdapter;
import test.park.nest.Activitiy.BaseActivity;
import test.park.nest.Model.search.SearchResultModel;
import test.park.nest.Network.RetrofitApiCallback;
import test.park.nest.R;

public class SearchResultActivity extends BaseActivity {

    @BindView(R.id.text_search_tag)
    TextView mTextSearchTag;

    @BindView(R.id.search_result_list)
    RecyclerView mSearchResultItemList;

    @BindView(R.id.scrollView)
    NestedScrollView mScrollView;

    @BindView(R.id.empty_text)
    TextView mEmptyTextView;

    private SearchResultRecyclerAdapter resultAdapter;

    private SearchResultModel resultData = null;

    private ArrayList<String> tagList = new ArrayList<>();


    private boolean isLoad = false;
    private boolean isAllData = false;

    private int totalCount = 0;

    private int pageNum = 1;
    private ArrayList<Integer> filterConvFac = new ArrayList<>();
    private ArrayList<Integer> filterSido = new ArrayList<>();

    private String tagSex = "";
    private String tagType = "";

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

        resultAdapter = new SearchResultRecyclerAdapter(this);


        mSearchResultItemList.setLayoutManager(new GridLayoutManager(this, 2));
        mSearchResultItemList.setAdapter(resultAdapter);
        mSearchResultItemList.setNestedScrollingEnabled(false);

        if (getIntent() != null) {
            pageNum = getIntent().getIntExtra("pageNum", 1);
            resultData = getIntent().getParcelableExtra("result");

            filterConvFac = getIntent().getIntegerArrayListExtra("filterConvFac");
            filterSido = getIntent().getIntegerArrayListExtra("filterSido");
            tagSex = getIntent().getStringExtra("tagSex");
            tagType = getIntent().getStringExtra("tagType");

            tagList.add(tagSex);
            tagList.add(tagType);
            tagList.addAll(getIntent().getStringArrayListExtra("tagSidoList"));
            tagList.addAll(getIntent().getStringArrayListExtra("tagConvFacList"));

            Log.d("LIST", tagList.toString());
        }

        makeTagText();

        if (resultData != null && resultData.getShelterSimpleList().size() > 0)
            resultAdapter.setSearchResultItems(resultData.getShelterSimpleList());
        else {
            mSearchResultItemList.setVisibility(View.GONE);
            mEmptyTextView.setVisibility(View.VISIBLE);
        }

        resultAdapter.notifyDataSetChanged();

        mScrollView.getParent().requestChildFocus(mScrollView, mScrollView);
        mScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getActionMasked()) {

                    case MotionEvent.ACTION_UP:

                        if(!mScrollView.canScrollVertically(1)){
                            if(!isLoad && !isAllData)
                                callMoreSearchResult();
                        }

                        break;
                }

                return false;
            }
        });
    }


    public void makeTagText() {

        if (tagList != null && tagList.size() > 0) {

            StringBuilder tagStr = new StringBuilder();

            for (int i = 0; i < tagList.size(); i++) {

                tagStr.append("<font color='#2ac1bc'># </font>").append(tagList.get(i));

                if (i != tagList.size() - 1) {
                    tagStr.append(" ");
                }
            }


            mTextSearchTag.setText(Html.fromHtml(tagStr.toString()), TextView.BufferType.SPANNABLE);

        }

    }

    @Override
    protected int getBackColorRes() {
        return getResources().getColor(R.color.grey_f8f8f8);
    }

    @Override
    protected int getLeftIconRes() {
        return R.drawable.back;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_search_result;
    }


    @Override
    protected int getTitleRes() {
        return R.string.search_top_title;
    }


    private void callMoreSearchResult() {


        if(isAllData)
            return;

        if(totalCount == resultData.getShelterSimpleList().size())
            return;

        isLoad = true;

        JsonObject innerObject = new JsonObject();
        innerObject.addProperty("pageNo", pageNum += 1);
        innerObject.addProperty("convFac", TextUtils.join(",", filterConvFac));
        innerObject.addProperty("sido", TextUtils.join(",", filterSido));

        if(tagType.equals("일시"))
            innerObject.addProperty("type", "1");
        else if(tagType.equals("단기"))
            innerObject.addProperty("type", "2");
        else
            innerObject.addProperty("type", "3");

        if(tagSex.equals("남자"))
            innerObject.addProperty("sex", "1");
        else if(tagSex.equals("여자"))
            innerObject.addProperty("sex", "2");
        else
            innerObject.addProperty("sex", "3");


        JsonObject bodyObject = new JsonObject();
        bodyObject.add("filter", innerObject);

        networkClient.callPostSearchResult(new RetrofitApiCallback() {
            @Override
            public void onError(Throwable t) {
                isLoad = false;
            }

            @Override
            public void onSuccess(int code, Object addData) {

                isLoad = false;

                if (addData != null && ((SearchResultModel) addData).getShelterSimpleList().size() > 0) {

                    totalCount = ((SearchResultModel) addData).getCount();


                    if(totalCount == 0 ||
                            totalCount == resultData.getShelterSimpleList().size())
                        isAllData = true;
                    else
                        isAllData = false;

                    int startIndex = resultData.getShelterSimpleList().size();

                    resultData.getShelterSimpleList().addAll(((SearchResultModel) addData).getShelterSimpleList());
                    resultAdapter.notifyItemRangeInserted(startIndex, ((SearchResultModel) addData).getShelterSimpleList().size());

                }else{

                    isAllData = true;

                }
            }

            @Override
            public void onFailed(int code, String msg) {
                isLoad = false;
            }
        }, bodyObject);
    }
}
