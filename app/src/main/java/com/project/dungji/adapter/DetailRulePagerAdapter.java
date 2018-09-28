package com.project.dungji.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import com.project.dungji.fragment.ShelterDetailRuleFragment;
import com.project.dungji.model.detail.DetailRuleItem;

public class DetailRulePagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<DetailRuleItem> items;


    public DetailRulePagerAdapter(FragmentManager fm, ArrayList<DetailRuleItem> items) {
        super(fm);

        this.items = items;
    }

    @Override
    public Fragment getItem(int position) {
        return ShelterDetailRuleFragment.create(position, items);
    }

    @Override
    public int getCount() {
        return items.size();
    }
}
