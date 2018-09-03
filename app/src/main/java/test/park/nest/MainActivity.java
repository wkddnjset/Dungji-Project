package test.park.nest;

import android.graphics.Color;
import android.os.Bundle;
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
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.matthewtamlin.sliding_intro_screen_library.DotIndicator;

import java.util.ArrayList;

import test.park.nest.Adapter.MainRecyclerAdapter;
import test.park.nest.Fragment.MainViewpagerFragment;
import test.park.nest.Model.MainRecyclerModel;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {

    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mLayoutManager;
    private MainRecyclerAdapter mMainRecyclerAdapter;
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private DotIndicator mDotIndicator;
    private int pagecount = 3;


    ArrayList<MainRecyclerModel> main_review_models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        setLeftIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        dataset();


        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager.setItemPrefetchEnabled(true);


        mMainRecyclerAdapter = new MainRecyclerAdapter(MainActivity.this, main_review_models);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mMainRecyclerAdapter);

        init();
    }


    private void init(){

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(this);
        mDotIndicator = (DotIndicator) findViewById(R.id.DotIndicator);
        mDotIndicator.setSelectedDotColor(Color.parseColor("#ff5580"));
        mDotIndicator.setUnselectedDotColor(Color.parseColor("#dadada"));
        mDotIndicator.setNumberOfItems(pagecount);
    }


    private void dataset() {

        main_review_models = new ArrayList<MainRecyclerModel>();
        for (int i = 0; i < 5; i++) {
            main_review_models.add(new MainRecyclerModel(MainActivity.this, 0, String.valueOf(i), String.valueOf(i), String.valueOf(i), 0, String.valueOf(i), String.valueOf(i), String.valueOf(i), 0));
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return MainViewpagerFragment.create(position);
        }

        @Override
        public int getCount() {

            return pagecount;
        }
    }
}
