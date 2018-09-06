package test.park.nest.Adapter.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.park.nest.Model.search.SearchRecyclerModel;
import test.park.nest.Model.search.SearchRecyclerModel.SearchFilterModel;
import test.park.nest.R;

public class SearchMainRecyclerAdapter extends RecyclerView.Adapter<SearchMainRecyclerAdapter.SearchMainViewHolder> {

    private ArrayList<SearchFilterModel> dataList = new ArrayList<>();

    private View.OnClickListener itemClickListener = null;


    // 0이면 지역용, 1이면 편의시설
    private int dataType = 0;



    @NonNull
    @Override
    public SearchMainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_search_filter_child, parent, false);

        return new SearchMainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchMainViewHolder holder, int position) {

//        // 처음에 첫번째는 무조건 선택상태로
//        if(position == 0)
//            holder.itemView.setSelected(true);


        dataList.get(position).setDataType(dataType);
        holder.filterName.setText(dataList.get(position).getName());
        holder.itemView.setTag(dataList.get(position));

        holder.itemView.setOnClickListener(itemClickListener);

    }

    public void setDataList(ArrayList<SearchFilterModel> dataList){
        this.dataList = dataList;
    }

    public void setDataType(int dataType){
        this.dataType = dataType;
    }

    public void setItemClickListener(View.OnClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }


    @Override
    public int getItemCount() {
      return dataList.size();
    }

    public class SearchMainViewHolder extends RecyclerView.ViewHolder{


        @BindView(R.id.filter_text_view)
        TextView filterName;


        public SearchMainViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }
    }
}
