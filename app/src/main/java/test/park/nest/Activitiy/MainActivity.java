package test.park.nest.Activitiy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.JsonObject;
import com.matthewtamlin.sliding_intro_screen_library.DotIndicator;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import test.park.nest.Adapter.MainRecyclerAdapter;
import test.park.nest.Fragment.MainViewpagerFragment;
import test.park.nest.Model.MainRecyclerModel;
import test.park.nest.Network.RetrofitApiCallback;
import test.park.nest.R;
import test.park.nest.Search.SearchMainActivity;
import test.park.nest.Utility.GpsUtil;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {


    private StaggeredGridLayoutManager mLayoutManager;
    private MainRecyclerAdapter mMainRecyclerAdapter;

    private ArrayList<MainRecyclerModel.bannerItem> bannerList = null;
    RecyclerView mRecyclerView;

    ViewPager mViewPager;

    PagerAdapter mPagerAdapter;

    DotIndicator mDotIndicator;


    DrawerLayout drawer;
    NavigationView navigationView;

    private ActionBarDrawerToggle toggle;


    Unbinder unbinder;

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

        mRecyclerView = findViewById(R.id.recyclerView);
        mViewPager = findViewById(R.id.pager);
        mDotIndicator = findViewById(R.id.DotIndicator);
        navigationView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer_layout);

//        toggle = new ActionBarDrawerToggle(this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();


        mMainRecyclerAdapter = new MainRecyclerAdapter(MainActivity.this);

        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager.setItemPrefetchEnabled(true);


        mDotIndicator.setSelectedDotColor(Color.parseColor("#ff5580"));
        mDotIndicator.setUnselectedDotColor(Color.parseColor("#dadada"));

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mMainRecyclerAdapter);
    }

    private void initListener() {
        setLeftIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
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

        GpsUtil gpsInfo = new GpsUtil(MainActivity.this);

        JsonObject innerObject = new JsonObject();
        innerObject.addProperty("longitude", gpsInfo.getLongitude());
        innerObject.addProperty("latitude", gpsInfo.getLatitude());
        innerObject.addProperty("pageNo", 10);

        JsonObject bodyObject = new JsonObject();
        bodyObject.add("shelter", innerObject);


        networkClient.callPostMainResult(new RetrofitApiCallback() {
            @Override
            public void onError(Throwable t) {
                mProgressDialog.dismiss();
            }

            @Override
            public void onSuccess(int code, Object resultData) {
                mProgressDialog.dismiss();


                mMainRecyclerAdapter.setDataList(((MainRecyclerModel) resultData).getShelterSimpleList());
                mMainRecyclerAdapter.notifyDataSetChanged();

                bannerList = ((MainRecyclerModel) resultData).getBannerList();

                mPagerAdapter = new PagerAdapter(getSupportFragmentManager(), bannerList);
                mViewPager.setAdapter(mPagerAdapter);


                mDotIndicator.setNumberOfItems(bannerList.size());


            }

            @Override
            public void onFailed(int code, String msg) {
                mProgressDialog.dismiss();

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


    protected int getLeftIconRes() {
        return R.drawable.menu;
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
