package test.park.nest.Search;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.park.nest.Adapter.search.SearchResultRecyclerAdapter;
import test.park.nest.BaseActivity;
import test.park.nest.Model.search.SearchResultModel;
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

    private int pageNum = 1;

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

        if(getIntent() != null){
            pageNum = getIntent().getIntExtra("pageNum", 1);
            resultData = getIntent().getParcelableExtra("result");
            tagList.addAll(getIntent().getStringArrayListExtra("tagSidoList"));
            tagList.addAll(getIntent().getStringArrayListExtra("tagConvFacList"));

            Log.d("LIST", tagList.toString());
        }

        makeTagText();

        if(resultData != null && resultData.getShelterSimpleList().size() > 0)
            resultAdapter.setSearchResultItems(resultData.getShelterSimpleList());
        else{
            mSearchResultItemList.setVisibility(View.GONE);
            mEmptyTextView.setVisibility(View.VISIBLE);
        }

        resultAdapter.notifyDataSetChanged();

        mScrollView.getParent().requestChildFocus(mScrollView, mScrollView);
    }


    public void makeTagText(){

        if(tagList != null && tagList.size() > 0){

            StringBuilder tagStr = new StringBuilder();

            for(int i = 0; i < tagList.size(); i ++){

                tagStr.append("<font color='#2ac1bc'># </font>").append(tagList.get(i));

                if(i != tagList.size() - 1){
                    tagStr.append(" ");
                }
            }


            mTextSearchTag.setText(Html.fromHtml(tagStr.toString()), TextView.BufferType.SPANNABLE);

        }

    }

    @Override
    protected int getBackColorRes() {
        return R.color.grey_f8f8f8;
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
}
