package test.park.nest.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import test.park.nest.R;

public class ShelterDetailViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_BANNER = 0;
    private final int VIEW_INTRODUCE = 1;
    private final int VIEW_FACIL = 2;
    private final int VIEW_RULE = 3;
    private final int VIEW_LOCATION = 4;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = null;

        switch (viewType) {

            case VIEW_BANNER:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_shelter_banner, parent, false);
                return new ShelterBannerView(v);


            case VIEW_INTRODUCE:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_shelter_introduce, parent, false);
                return new ShelterIntroduceView(v);


            case VIEW_FACIL:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_shelter_facilities, parent, false);
                return new ShelterFacilView(v);


            case VIEW_RULE:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_shelter_rule, parent, false);
                return new ShelterRuleView(v);


            default:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_shelter_location, parent, false);
                return new ShelterLocationView(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(position == VIEW_BANNER){



        }
    }


    @Override
    public int getItemCount() {
        return 5;
    }


    @Override
    public int getItemViewType(int position) {

        switch (position) {

            case 0:
                return VIEW_BANNER;

            case 1:
                return VIEW_INTRODUCE;

            case 2:
                return VIEW_FACIL;

            case 3:
                return VIEW_RULE;

            default:
                return VIEW_LOCATION;
        }
    }


    public class ShelterBannerView extends RecyclerView.ViewHolder {

        public ShelterBannerView(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }


    public class ShelterIntroduceView extends RecyclerView.ViewHolder {

        public ShelterIntroduceView(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    public class ShelterFacilView extends RecyclerView.ViewHolder {

        public ShelterFacilView(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    public class ShelterRuleView extends RecyclerView.ViewHolder {

        public ShelterRuleView(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    public class ShelterLocationView extends RecyclerView.ViewHolder {

        public ShelterLocationView(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
