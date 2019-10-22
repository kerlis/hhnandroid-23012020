package me.doapps.appdhn.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import me.doapps.appdhn.R;
import me.doapps.appdhn.models.Report;

/**
 * Created by William_ST on 30/09/15.
 */
public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.CardsViewHolder> {

    private final String TAG = ReportAdapter.class.getSimpleName();
    private static Context context;
    public OnClick onClick;

    private static List<Report> reportList;

    public ReportAdapter(List<Report> reportList, Context context) {
        this.reportList = reportList;
        this.context = context;
    }

    @Override
    public ReportAdapter.CardsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seismic_report, parent, false);
        return new CardsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CardsViewHolder holder, final int position) {
        try {
//            Picasso.with(context).load(reportList.get(position).getImage()).into(holder.imageViewIcon);
//            holder.imageViewIcon.setImageResource(R.mipmap.im_holder_report);
            holder.textViewTitle.setText(reportList.get(position).getEvaluacion());
            holder.textViewDescription.setText(reportList.get(position).getEpicenter());
            holder.textViewMagnitude.setText(reportList.get(position).getMagnitude() + "");
            holder.textViewDate.setText(reportList.get(position).getDate());
            holder.textViewType.setText(reportList.get(position).getType());
            holder.linearLayoutItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClick.click(reportList.get(position));
                }
            });
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }

    public void setOnClick(OnClick onClick){
        this.onClick = onClick;
    }

    public interface OnClick{
        void click(Report report);
    }

    public static class CardsViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout linearLayoutItem;
        public TextView textViewTitle, textViewDescription, textViewMagnitude, textViewDate, textViewType;

        public CardsViewHolder(View v) {
            super(v);
            linearLayoutItem = (LinearLayout) itemView.findViewById(R.id.linear_layout_item);
            textViewTitle = (TextView) itemView.findViewById(R.id.isr_text_view_title);
            textViewDescription = (TextView) itemView.findViewById(R.id.isr_text_view_description);
            textViewMagnitude = (TextView) itemView.findViewById(R.id.isr_text_view_magnitude);
            textViewDate = (TextView) itemView.findViewById(R.id.text_view_date);
            textViewType = (TextView) itemView.findViewById(R.id.text_view_type);
//            imageViewIcon = (ImageView) itemView.findViewById(R.id.isr_image_view);
        }

    }

}