package test.park.nest.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.matthewtamlin.sliding_intro_screen_library.DotIndicator;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.park.nest.Model.ShelterDetailModel;
import test.park.nest.Model.detail.DetailConvFacItem;
import test.park.nest.R;
import test.park.nest.Utility.CustomViewPager;

public class ShelterDetailViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private final int VIEW_BANNER = 0;
    private final int VIEW_INTRODUCE = 1;
    private final int VIEW_FACIL = 2;
    private final int VIEW_RULE = 3;
    private final int VIEW_LOCATION = 4;

    private ShelterDetailModel shelterDetailData;

    private Context context;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();

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

        if(shelterDetailData == null)
            return;

        if(holder instanceof ShelterBannerView){

            DetailBannerPagerAdapter adapter = new DetailBannerPagerAdapter(((AppCompatActivity) context).getSupportFragmentManager(), shelterDetailData.getImages());

            ((ShelterBannerView)holder).shelterPager.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            ((ShelterBannerView)holder).shelterPagerDot.setNumberOfItems(shelterDetailData.getImages().size());

            ((ShelterBannerView)holder).shelterName.setText(shelterDetailData.getDetailInfoItem().getName());
            ((ShelterBannerView)holder).shelterAddr.setText(shelterDetailData.getDetailInfoItem().getSidoName());

            if(shelterDetailData.getDetailInfoItem().getType() == 1){

                ((ShelterBannerView)holder).shelterType.setText("일시 쉼터");
                ((ShelterBannerView)holder).shelterType.setTextColor(context.getResources().getColor(R.color.green_86c894));

            }else if (shelterDetailData.getDetailInfoItem().getType() == 2) {

                ((ShelterBannerView)holder).shelterType.setText("단기 쉼터");
                ((ShelterBannerView)holder).shelterType.setTextColor(context.getResources().getColor(R.color.red_eb6877));

            }else {
                ((ShelterBannerView)holder).shelterType.setText("중장기 쉼터");
                ((ShelterBannerView)holder).shelterType.setTextColor(context.getResources().getColor(R.color.blue_0068b7));
            }

            ((ShelterBannerView)holder).shelterPeople.setText(shelterDetailData.getDetailInfoItem().getRemain_ppl() + "명");
        }

        else if(holder instanceof ShelterIntroduceView){


            if(TextUtils.isEmpty(shelterDetailData.getDetailInfoItem().getIntroduction()))
                ((ShelterIntroduceView)holder).itemView.setVisibility(View.GONE);

            else {
                ((ShelterIntroduceView)holder).itemView.setVisibility(View.VISIBLE);

                ((ShelterIntroduceView)holder).shelterIntroTitle.setText(shelterDetailData.getDetailInfoItem().getName() +"에서는\n항상 여러분을 기다리고 있습니다.");
                ((ShelterIntroduceView)holder).shelterIntroContent.setText(shelterDetailData.getDetailInfoItem().getIntroduction());
            }


        }

        else if(holder instanceof ShelterFacilView){

            if(shelterDetailData.getConvFacs() == null || shelterDetailData.getConvFacs().size() <= 0){
                ((ShelterFacilView)holder).itemView.setVisibility(View.GONE);

            }else{
                ((ShelterFacilView)holder).itemView.setVisibility(View.VISIBLE);
                ((ShelterFacilView)holder).adapter.setItems(shelterDetailData.getConvFacs());
                ((ShelterFacilView)holder).adapter.notifyDataSetChanged();
            }

        }

        else if(holder instanceof ShelterRuleView){

            if(shelterDetailData.getRules().size() <= 0){
                ((ShelterRuleView)holder).itemView.setVisibility(View.GONE);

            }else{

                ((ShelterRuleView)holder).itemView.setVisibility(View.VISIBLE);

                DetailRulePagerAdapter adapter = new DetailRulePagerAdapter(((AppCompatActivity) context).getSupportFragmentManager(), shelterDetailData.getRules());

                ((ShelterRuleView)holder).rulePager.setAdapter(adapter);
            }


        }

        else{
            ((ShelterLocationView)holder).shelterAddr.setText(shelterDetailData.getDetailInfoItem().getAddress());
        }
    }


    @Override
    public int getItemCount() {

        int totalCount = 5;

        if(shelterDetailData != null){

            if(shelterDetailData.getConvFacs().size() == 0)
                totalCount--;

            if(shelterDetailData.getRules().size() == 0)
                totalCount--;
        }

        return totalCount;
    }


    public void setShelterDetailData(ShelterDetailModel shelterDetailData) {
        this.shelterDetailData = shelterDetailData;
    }


    @Override
    public int getItemViewType(int position) {

        switch (position) {

            case 0:
                return VIEW_BANNER;

            case 1:
                return VIEW_INTRODUCE;

            case 2:
                if(shelterDetailData != null){
                    if(shelterDetailData.getConvFacs().size() == 0 &&
                            shelterDetailData.getRules().size() > 0)
                        return VIEW_RULE;

                    else if(shelterDetailData.getConvFacs().size() > 0 &&
                            shelterDetailData.getRules().size() == 0)
                        return VIEW_FACIL;

                    else
                        return VIEW_LOCATION;
                }

                return VIEW_FACIL;


            case 3:
                if(shelterDetailData != null){
                    if(shelterDetailData.getRules().size() == 0)
                        return VIEW_LOCATION;

                    else
                        return VIEW_RULE;
                }
                return VIEW_RULE;

            default:
                return VIEW_LOCATION;
        }
    }


    public class ShelterBannerView extends RecyclerView.ViewHolder {

        @BindView(R.id.top_back)
        ImageView back;

        @BindView(R.id.shelter_img_pager)
        ViewPager shelterPager;

        @BindView(R.id.shelter_img_dot)
        DotIndicator shelterPagerDot;

        @BindView(R.id.txt_shelter_type)
        TextView shelterType;

        @BindView(R.id.txt_shelter_name)
        TextView shelterName;

        @BindView(R.id.txt_shelter_addr)
        TextView shelterAddr;

        @BindView(R.id.txt_shelter_people)
        TextView shelterPeople;

        public ShelterBannerView(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            back.bringToFront();
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity)context).finish();
                }
            });
        }
    }


    public class ShelterIntroduceView extends RecyclerView.ViewHolder {

        @BindView(R.id.shelter_intro_title)
        TextView shelterIntroTitle;

        @BindView(R.id.shelter_intro)
        TextView shelterIntroContent;


        public ShelterIntroduceView(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    public class ShelterFacilView extends RecyclerView.ViewHolder {

        @BindView(R.id.facilities_list)
        RecyclerView facilList;

        ShelterFacilViewAdapter adapter;

        public ShelterFacilView(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            GridLayoutManager gd = new GridLayoutManager(context, 3);
            facilList.setLayoutManager(gd);

            adapter = new ShelterFacilViewAdapter();

            facilList.setAdapter(adapter);
        }

        public class ShelterFacilViewAdapter extends RecyclerView.Adapter{

            private ArrayList<DetailConvFacItem> items = new ArrayList<>();

            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_facil_child, parent, false);
                return new FacilViewHolder(v);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

                Glide.with(context).load(items.get(position).getImg())
                        .thumbnail(0.1f)
                        .into(((FacilViewHolder)holder).facilImage);

                ((FacilViewHolder)holder).facilText.setText(items.get(position).getName());
            }

            @Override
            public int getItemCount() {
                return items.size();
            }

            public void setItems(ArrayList<DetailConvFacItem> items) {
                this.items = items;
            }
        }



        public class FacilViewHolder extends RecyclerView.ViewHolder{

            @BindView(R.id.facil_image)
            ImageView facilImage;

            @BindView(R.id.facil_text)
            TextView facilText;


            public FacilViewHolder(View itemView) {
                super(itemView);

                ButterKnife.bind(this, itemView);

            }
        }
    }





    public class ShelterRuleView extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.rule_pager_right)
        ImageView pagerRight;

        @BindView(R.id.rule_pager_left)
        ImageView pagerLeft;

        @BindView(R.id.rule_pager)
        CustomViewPager rulePager;


        public ShelterRuleView(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            int padding = convertDip2Pixels(context, 10);

            rulePager.setClipToPadding(false);
            rulePager.setPadding(padding, padding, padding, padding);
            rulePager.setPageMargin(padding);

            pagerRight.setVisibility(View.INVISIBLE);
            pagerLeft.setOnClickListener(this);
            pagerRight.setOnClickListener(this);

            rulePager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                    if(position > 0){

                        pagerRight.setVisibility(View.VISIBLE);
                    }else{

                        pagerRight.setVisibility(View.INVISIBLE);
                    }


                    if(position < shelterDetailData.getRules().size() - 1){
                        pagerLeft.setVisibility(View.VISIBLE);
                    }else{
                        pagerLeft.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }

        public int convertDip2Pixels(Context context, int dip) {
            return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.rule_pager_right:

                    if(rulePager.getCurrentItem() > 0){

                        rulePager.setCurrentItem(rulePager.getCurrentItem() - 1 , true);
                    }

                    break;

                case R.id.rule_pager_left:

                    if(rulePager.getCurrentItem() < shelterDetailData.getRules().size()){
                        rulePager.setCurrentItem(rulePager.getCurrentItem() + 1 , true);
                    }

                    break;
            }
        }
    }

    public class ShelterLocationView extends RecyclerView.ViewHolder {

        @BindView(R.id.shelter_addr)
        TextView shelterAddr;

        public ShelterLocationView(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
