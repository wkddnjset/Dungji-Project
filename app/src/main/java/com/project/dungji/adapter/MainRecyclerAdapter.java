package com.project.dungji.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
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

    private View.OnClickListener itemClick;

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
        MainRecyclerModel.shelterItem items = dataList.get(position);

        GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();

        if (position % 2 == 0) {
            params.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 7f, context.getResources().getDisplayMetrics());
        } else {
            params.leftMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 7f, context.getResources().getDisplayMetrics());
        }

        holder.itemView.setLayoutParams(params);

        Glide.with(context)
                .load(items.getImg())
                .into(holder.item_main_recy_img);


        if(items.getType() == 1){

            holder.item_main_recy_guGunName.setText("일시 쉼터");
            holder.item_main_recy_guGunName.setTextColor(context.getResources().getColor(R.color.green_86c894));

        }else if (items.getType() == 2) {

            holder.item_main_recy_guGunName.setText("단기 쉼터");
            holder.item_main_recy_guGunName.setTextColor(context.getResources().getColor(R.color.red_eb6877));

        }else {

            holder.item_main_recy_guGunName.setText("중장기 쉼터");
            holder.item_main_recy_guGunName.setTextColor(context.getResources().getColor(R.color.blue_0068b7));
        }


        String shelterName = "";

        if(items.getSex() == 1){
            shelterName += "[여자] ";

        }else if(items.getSex() == 2){
            shelterName += "[남자] ";

        }else{
            shelterName += "[공용] ";
        }

        holder.item_main_recy_name.setText( shelterName + items.getName());

        holder.item_main_recy_address.setText(items.getAddress());

        holder.itemView.setTag(items.getId());
        holder.itemView.setOnClickListener(itemClick);

    }

    public void setItemClick(View.OnClickListener itemClick) {
        this.itemClick = itemClick;
    }

    public void setDataList(ArrayList<MainRecyclerModel.shelterItem> dataList) {
        this.dataList = dataList;
    }

    @Override

    public int getItemCount() {
        return this.dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.main_item_image)
        ImageView item_main_recy_img;

        @BindView(R.id.main_shelter_type)
        TextView item_main_recy_guGunName;

        @BindView(R.id.main_shelter_name)
        TextView item_main_recy_name;

        @BindView(R.id.main_shelter_area)
        TextView item_main_recy_address;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

    }
}