package test.park.nest.Fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import test.park.nest.Model.MainRecyclerModel;
import test.park.nest.R;

public class MainViewpagerFragment  extends Fragment {

    private int mPageNumber;
    private ArrayList<MainRecyclerModel.bannerItem> bannerList = null;


    public static MainViewpagerFragment create(int pageNumber,ArrayList<MainRecyclerModel.bannerItem> bannerList ){
        MainViewpagerFragment fragment = new MainViewpagerFragment();
        Bundle args = new Bundle();
        args.putInt("page",pageNumber);
        args.putParcelableArrayList("bannerList",bannerList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt("page");
        bannerList = getArguments().getParcelableArrayList("bannerList");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.main_viewpager, container, false);
        ImageView imageView =  rootView.findViewById(R.id.image);

        Glide.with(this).load(bannerList.get(mPageNumber).getImg())
                .thumbnail(0.1f)
                .into(imageView);


        return rootView;
    }

}