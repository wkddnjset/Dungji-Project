package test.park.nest.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import test.park.nest.Model.detail.DetailImageItem;
import test.park.nest.R;

public class ShelterDetailBannerFragment extends Fragment {

    private ArrayList<DetailImageItem> items;

    private int position = 0;

    public static ShelterDetailBannerFragment create(int position, ArrayList<DetailImageItem> items){

        ShelterDetailBannerFragment fragment = new ShelterDetailBannerFragment();

        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putParcelableArrayList("items", items);
        fragment.setArguments(args);

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_detail_banner, container, false);
        ImageView imageView =  rootView.findViewById(R.id.detail_image);

        position = getArguments().getInt("position");
        items = getArguments().getParcelableArrayList("items");

        Glide.with(this).load(items.get(position).getImg())
                .thumbnail(0.1f)
                .into(imageView);

        return rootView;
    }
}
