package test.park.nest.Adapter.search;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import test.park.nest.R;

public class SearchMainRecyclerAdapter extends RecyclerView.Adapter<SearchMainRecyclerAdapter.SearchMainViewHolder> {


    @NonNull
    @Override
    public SearchMainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_search_filter_child, parent, false);

        return new SearchMainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchMainViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SearchMainViewHolder extends RecyclerView.ViewHolder{

        public SearchMainViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
