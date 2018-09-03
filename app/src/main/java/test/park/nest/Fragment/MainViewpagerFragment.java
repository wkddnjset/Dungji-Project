package test.park.nest.Fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import test.park.nest.R;

public class MainViewpagerFragment  extends Fragment {

    private int mPageNumber;

    public static MainViewpagerFragment create(int pageNumber){
        MainViewpagerFragment fragment = new MainViewpagerFragment();
        Bundle args = new Bundle();
        args.putInt("page",pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt("page");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.main_viewpager, container, false);
        ImageView imageView =  rootView.findViewById(R.id.image);

        Drawable drawable = getResources().getDrawable(R.drawable.banner);
        imageView.setImageDrawable(drawable);
//        Glide.with(this).load("http://18.218.70.203:8080/Alba_report_img/a"+mPageNumber+".jpg")
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .skipMemoryCache(true)
//                .thumbnail(0.1f)
//                .fitCenter()
//                .into(imageView);


        return rootView;
    }
}