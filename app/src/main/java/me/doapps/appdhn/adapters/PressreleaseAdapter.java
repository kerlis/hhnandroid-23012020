package me.doapps.appdhn.adapters;

import android.content.Context;
import android.nfc.Tag;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import me.doapps.appdhn.R;
import me.doapps.appdhn.models.PressRelease;

/**
 * Created by William_ST on 27/07/16.
 */
public class PressreleaseAdapter extends RecyclerView.Adapter<PressreleaseAdapter.ViewHolder> {

    private final String TAG = PressreleaseAdapter.class.getSimpleName();
    private Context context;
    private List<PressRelease> pressReleaseList;

    public PressreleaseAdapter(List<PressRelease> pressReleaseList, Context context) {
        this.context = context;
        this.pressReleaseList = pressReleaseList;
    }

    @Override
    public PressreleaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pressrelease, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(PressreleaseAdapter.ViewHolder holder, final int position) {
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy' 'HH:mm");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");

        Date date = null;

        try {
            date = simpleDateFormat2.parse(pressReleaseList.get(position).getTitle());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.textViewTitle.setText(simpleDateFormat1.format(date));
        holder.textViewTitle2.setText(pressReleaseList.get(position).getSubTitle());
        holder.textViewDescription.setText(pressReleaseList.get(position).getDescription());

        holder.linearLayoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.item(pressReleaseList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return pressReleaseList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout linearLayoutItem;
        public TextView textViewTitle, textViewTitle2, textViewDescription;

        public ViewHolder(View v) {
            super(v);
            linearLayoutItem = (LinearLayout) v.findViewById(R.id.linear_layout_item);
            textViewTitle = (TextView) v.findViewById(R.id.text_view_title);
            textViewTitle2 = (TextView) v.findViewById(R.id.text_view_title2);
            textViewDescription = (TextView) v.findViewById(R.id.text_view_description);
        }
    }

    private OnItemClick onItemClick;

    public interface OnItemClick {
        void item(PressRelease notification);
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

}