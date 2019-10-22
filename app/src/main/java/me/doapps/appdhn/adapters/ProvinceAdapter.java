package me.doapps.appdhn.adapters;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import me.doapps.appdhn.R;
import me.doapps.appdhn.models.Provinces;

/**
 * Created by Leandro on 10/18/17.
 */

public class ProvinceAdapter extends RecyclerView.Adapter<ProvinceAdapter.ProvinceHolder> {

    private Activity activity;
    private List<Provinces> listProvince;
    private FileProvinceAdapter fileProvinceAdapter;

    public ProvinceAdapter(Activity activity, List<Provinces> listProvince) {
        this.activity = activity;
        this.listProvince = listProvince;
    }

    @Override
    public ProvinceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProvinceHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_provinces, parent, false));
    }

    @Override
    public void onBindViewHolder(ProvinceHolder holder, final int position) {

        holder.titleText.setText(listProvince.get(position).getName());
        holder.mapImage.setImageResource(listProvince.get(position).getImage());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        holder.listChartRecycler.setLayoutManager(linearLayoutManager);
        fileProvinceAdapter = new FileProvinceAdapter(activity, listProvince.get(position).getFileProvinces());
        holder.listChartRecycler.setAdapter(fileProvinceAdapter);

        holder.listChartRecycler.setVisibility(listProvince.get(position).isStatus() ? View.VISIBLE : View.GONE);
        holder.indicatorImage.setImageResource(listProvince.get(position).isStatus() ? R.mipmap.im_collapse_icon : R.mipmap.im_expand_icon);

        holder.contenLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listProvince.get(position).setStatus(!listProvince.get(position).isStatus());
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return listProvince.size();
    }

    public class ProvinceHolder extends RecyclerView.ViewHolder {

        LinearLayout contenLayout;
        ImageView mapImage;
        TextView titleText;
        ImageView indicatorImage;
        RecyclerView listChartRecycler;

        public ProvinceHolder(View itemView) {
            super(itemView);

            contenLayout = (LinearLayout) itemView.findViewById(R.id.conten_layout);
            mapImage = (ImageView) itemView.findViewById(R.id.map_image);
            titleText = (TextView) itemView.findViewById(R.id.title_text);
            indicatorImage = (ImageView) itemView.findViewById(R.id.indicator_image);
            listChartRecycler = (RecyclerView) itemView.findViewById(R.id.list_chart_recycler);
        }
    }
}
