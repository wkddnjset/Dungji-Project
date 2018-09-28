package com.project.dungji.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.project.dungji.model.detail.DetailRuleItem;
import com.project.dungji.R;

public class ShelterDetailRuleFragment extends Fragment {


    private ArrayList<DetailRuleItem> items;

    private int position = 0;

    public static ShelterDetailRuleFragment create(int position, ArrayList<DetailRuleItem> items){

        ShelterDetailRuleFragment fragment = new ShelterDetailRuleFragment();

        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putParcelableArrayList("items", items);
        fragment.setArguments(args);

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.view_rule_pager_child, container, false);

        TextView ruleTitle = rootView.findViewById(R.id.rule_child_title);
        TextView ruleContent = rootView.findViewById(R.id.rule_child_context);

        position = getArguments().getInt("position");
        items = getArguments().getParcelableArrayList("items");


        ruleTitle.setText(items.get(position).getRule());
        ruleContent.setText(items.get(position).getDetail());


        return rootView;
    }
}
