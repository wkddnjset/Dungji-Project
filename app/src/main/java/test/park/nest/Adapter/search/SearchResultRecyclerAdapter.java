package test.park.nest.Adapter.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.park.nest.Adapter.search.SearchResultRecyclerAdapter.SearchResultViewHolder;
import test.park.nest.Model.search.SearchResultModel;
import test.park.nest.Model.search.SearchResultModel.SearchResultItem;
import test.park.nest.R;

public class SearchResultRecyclerAdapter extends RecyclerView.Adapter<SearchResultViewHolder> {

    private Context context;
    private ArrayList<SearchResultItem> searchResultItems = new ArrayList<>();


    public SearchResultRecyclerAdapter(Context context){
        this.context = context;
    }


    @NonNull
    @Override
    public SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_search_result_child, parent, false);

        return new SearchResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultViewHolder holder, int position) {

        GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams)holder.itemView.getLayoutParams();

        if(position % 2 == 0){
            params.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 7f, context.getResources().getDisplayMetrics());
        }else{
            params.leftMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 7f, context.getResources().getDisplayMetrics());
        }

        holder.itemView.setLayoutParams(params);


        Glide.with(context)
                .load(searchResultItems.get(position).getImg())
                .apply(new RequestOptions().transform(new RoundedCorners(10)))
                .into(holder.shelterImageView);

        holder.shelterName.setText(searchResultItems.get(position).getName());
        holder.shelterArea.setText(searchResultItems.get(position).getSidoName() + " " + searchResultItems.get(position).getGuGunName());



        if(searchResultItems.get(position).getType() == 1){

            holder.shelterType.setText("일시 쉼터");
            holder.shelterType.setTextColor(context.getResources().getColor(R.color.green_86c894));

        }else if (searchResultItems.get(position).getType() == 2) {

            holder.shelterType.setText("단기 쉼터");
            holder.shelterType.setTextColor(context.getResources().getColor(R.color.red_eb6877));

        }else {

            holder.shelterType.setText("중장기 쉼터");
            holder.shelterType.setTextColor(context.getResources().getColor(R.color.blue_0068b7));
        }

    }

    @Override
    public int getItemCount() {
        return searchResultItems.size();
    }


    public void setSearchResultItems(ArrayList<SearchResultItem> searchResultItems){
        this.searchResultItems = searchResultItems;
    }



    public class SearchResultViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.search_item_image)
        ImageView shelterImageView;

        @BindView(R.id.search_shelter_type)
        TextView shelterType;

        @BindView(R.id.search_shelter_name)
        TextView shelterName;

        @BindView(R.id.search_shelter_area)
        TextView shelterArea;


        public SearchResultViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);


        }
    }
}
