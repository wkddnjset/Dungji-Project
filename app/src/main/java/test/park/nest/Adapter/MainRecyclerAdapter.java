package test.park.nest.Adapter;

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

import test.park.nest.Model.MainRecyclerModel;
import test.park.nest.R;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<MainRecyclerModel> arrData;

    public MainRecyclerAdapter(Context c, ArrayList<MainRecyclerModel> arr) {
        this.context = c;
        this.arrData = arr;

        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_main_recycler, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final MainRecyclerModel items = arrData.get(position);
        try {

//            Glide.with(items.getActivity()).load(items.getImg()).into(holder.item_main_recy_img);

            holder.item_main_recy_guGunName.setText(items.getGuGunName());
            holder.item_main_recy_name.setText(items.getName());
            holder.item_main_recy_address.setText(items.getAddress());
        } catch (Exception e) {

        }

    }

    @Override

    public int getItemCount() {
        return this.arrData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private LinearLayout item_main_recy_ll;
        private ImageView item_main_recy_img;
        private TextView item_main_recy_guGunName, item_main_recy_name, item_main_recy_address;

        public ViewHolder(View itemView) {
            super(itemView);
            item_main_recy_ll = (LinearLayout) itemView.findViewById(R.id.item_main_recy_ll);
            item_main_recy_img = (ImageView) itemView.findViewById(R.id.item_main_recy_img);
            item_main_recy_guGunName = (TextView) itemView.findViewById(R.id.item_main_recy_guGunName);
            item_main_recy_name = (TextView) itemView.findViewById(R.id.item_main_recy_name);
            item_main_recy_address = (TextView) itemView.findViewById(R.id.item_main_recy_address);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(context, .class);
//            context.startActivity(intent);

        }
    }
}