package com.project.dungji.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.project.dungji.model.MainRecyclerModel;
import com.project.dungji.R;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {
    private Context context;
    private ArrayList<MainRecyclerModel.shelterItem> dataList = new ArrayList<>();

    public MainRecyclerAdapter(Context c) {
        this.context = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_main_recycler, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final MainRecyclerModel.shelterItem items = dataList.get(position);
        try {
            Glide.with(context)
                    .load(items.getImg())
                    .into(holder.item_main_recy_img);

            holder.item_main_recy_guGunName.setText(items.getGuGunName());
            holder.item_main_recy_name.setText(items.getName());
            holder.item_main_recy_address.setText(items.getAddress());
        } catch (Exception e) {

        }

    }

    public void setDataList(ArrayList<MainRecyclerModel.shelterItem> dataList) {
            this.dataList = dataList;
    }

    @Override

    public int getItemCount() {
        return this.dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.item_main_recy_ll)
        LinearLayout item_main_recy_ll;

        @BindView(R.id.item_main_recy_img)
        ImageView item_main_recy_img;

        @BindView(R.id.item_main_recy_guGunName)
        TextView item_main_recy_guGunName;

        @BindView(R.id.item_main_recy_name)
        TextView item_main_recy_name;

        @BindView(R.id.item_main_recy_address)
        TextView item_main_recy_address;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(context, .class);
//            context.startActivity(intent);

        }
    }
}