package test.park.nest.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import test.park.nest.Fragment.ShelterDetailBannerFragment;
import test.park.nest.Model.detail.DetailImageItem;

public class DetailBannerPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<DetailImageItem> items;


    public DetailBannerPagerAdapter(FragmentManager fm, ArrayList<DetailImageItem> items) {
        super(fm);
        this.items = items;
    }



    @Override
    public Fragment getItem(int position) {
        return ShelterDetailBannerFragment.create(position, items);
    }

    @Override
    public int getCount() {
        return items.size();
    }
}
