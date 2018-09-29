package com.project.dungji.activitiy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.google.gson.JsonObject;
import com.matthewtamlin.sliding_intro_screen_library.DotIndicator;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import com.project.dungji.activitiy.search.SearchResultActivity;
import com.project.dungji.adapter.MainRecyclerAdapter;
import com.project.dungji.fragment.MainViewpagerFragment;
import com.project.dungji.model.MainRecyclerModel;
import com.project.dungji.model.search.SearchResultModel;
import com.project.dungji.network.RetrofitApiCallback;
import com.project.dungji.R;
import com.project.dungji.activitiy.search.SearchMainActivity;
import com.project.dungji.utility.GpsUtil;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {


    private GridLayoutManager mLayoutManager;
    private MainRecyclerAdapter mMainRecyclerAdapter;

    private ArrayList<MainRecyclerModel.bannerItem> bannerList = null;
    private ArrayList<MainRecyclerModel.shelterItem> shelterList = new ArrayList<>();
    RecyclerView mRecyclerView;

    ViewPager mViewPager;

    PagerAdapter mPagerAdapter;

    DotIndicator mDotIndicator;

    NestedScrollView mScrollView;

    DrawerLayout drawer;
    NavigationView navigationView;


    private boolean isLoad = false;
    private boolean isAllData = false;

    private int totalCount = 0;

    private int pageNum = 0;


    private ActionBarDrawerToggle toggle;


    Unbinder unbinder;



    private View.OnClickListener moveDetailListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int shelterId = (int)v.getTag();

            Intent intent = new Intent(MainActivity.this, ShelterDetailActivity.class);
            intent.putExtra("shelterId", shelterId);
            startActivity(intent);

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        unbinder = ButterKnife.bind(this);
        unbinder.unbind();
//        ButterKnife.bind(this);
        dataset();

        initLayout();
        initListener();
    }


    private void initLayout() {

        mScrollView = findViewById(R.id.main_scroll);
        mRecyclerView = findViewById(R.id.recyclerView);
        mViewPager = findViewById(R.id.pager);
        mDotIndicator = findViewById(R.id.DotIndicator);
        navigationView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer_layout);

        mMainRecyclerAdapter = new MainRecyclerAdapter(MainActivity.this);
        mMainRecyclerAdapter.setItemClick(moveDetailListener);

        mLayoutManager = new GridLayoutManager(this, 2);
        mLayoutManager.setItemPrefetchEnabled(true);


        mDotIndicator.setSelectedDotColor(Color.parseColor("#ffffff"));
        mDotIndicator.setUnselectedDotColor(Color.parseColor("#a5dddb"));

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mMainRecyclerAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);



        mScrollView.getParent().requestChildFocus(mScrollView, mScrollView);
        mScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getActionMasked()) {

                    case MotionEvent.ACTION_UP:

                        if(!mScrollView.canScrollVertically(1)){
                            if(!isLoad && !isAllData)
                                dataset();
                        }

                        break;
                }

                return false;
            }
        });

    }

    private void initListener() {
        setLeftIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                drawer.openDrawer(Gravity.LEFT);
            }
        });

        setRightIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchMainActivity.class);
                startActivity(intent);
            }
        });

        mViewPager.addOnPageChangeListener(this);
        navigationView.setNavigationItemSelectedListener(this);
    }


    private void dataset() {


        if(isAllData)
            return;

        isLoad = true;

        GpsUtil gpsInfo = new GpsUtil(MainActivity.this);

        JsonObject innerObject = new JsonObject();
        innerObject.addProperty("longitude", String.valueOf(gpsInfo.getLongitude()));
        innerObject.addProperty("latitude", String.valueOf(gpsInfo.getLatitude()));
        innerObject.addProperty("pageNo", pageNum += 1);

        JsonObject bodyObject = new JsonObject();
        bodyObject.add("shelter", innerObject);


        networkClient.callPostMainResult(new RetrofitApiCallback() {
            @Override
            public void onError(Throwable t) {
                mProgressDialog.dismiss();
                isLoad = false;
            }

            @Override
            public void onSuccess(int code, Object resultData) {
                mProgressDialog.dismiss();


                totalCount = ((MainRecyclerModel) resultData).getCount();

                shelterList.addAll(((MainRecyclerModel) resultData).getShelterSimpleList());

                if(totalCount == 0 ||
                        totalCount == shelterList.size())
                    isAllData = true;
                else
                    isAllData = false;


                int startIndex = shelterList.size();

                if(((MainRecyclerModel) resultData).getShelterSimpleList().size() == shelterList.size()){

                    mMainRecyclerAdapter.setDataList(shelterList);
                    mMainRecyclerAdapter.notifyDataSetChanged();
                }
                else
                    mMainRecyclerAdapter.notifyItemRangeInserted(startIndex, ((MainRecyclerModel) resultData).getShelterSimpleList().size());



                if(bannerList != null && bannerList.size() > 0)
                    bannerList.clear();

                bannerList = ((MainRecyclerModel) resultData).getBannerList();

                mPagerAdapter = new PagerAdapter(getSupportFragmentManager(), bannerList);
                mViewPager.setAdapter(mPagerAdapter);


                mDotIndicator.setNumberOfItems(bannerList.size());

                isLoad = false;

            }

            @Override
            public void onFailed(int code, String msg) {
                mProgressDialog.dismiss();
                isLoad = false;

            }
        }, bodyObject);

    }


    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }


    protected int getRightIconRes() {
        return R.drawable.search;
    }

    @Override
    protected int getTitleRes() {
        return R.string.app_name;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        mDotIndicator.setSelectedItem(mViewPager.getCurrentItem(), true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    private class PagerAdapter extends FragmentStatePagerAdapter {

        private ArrayList<MainRecyclerModel.bannerItem> bannerItems;

        public PagerAdapter(FragmentManager fm, ArrayList<MainRecyclerModel.bannerItem> bannerItems) {
            super(fm);
            this.bannerItems = bannerItems;
        }

        @Override
        public Fragment getItem(int position) {
            return MainViewpagerFragment.create(position, bannerItems);
        }

        @Override
        public int getCount() {
            return bannerItems.size();
        }
    }
}
