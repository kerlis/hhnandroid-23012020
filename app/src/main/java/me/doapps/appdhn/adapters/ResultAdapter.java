package me.doapps.appdhn.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import me.doapps.appdhn.R;
import me.doapps.appdhn.models.Distrito;

/**
 * Created by William_ST on 11/01/16.
 */
public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.EventsAdapterHolder> {
    private final String TAG = ResultAdapter.class.getSimpleName();
    private List<Distrito> listResult;
    private OnItemClickListener mItemClickListener;
    private Context context;

    public ResultAdapter(List<Distrito> listResult, Context context) {
        this.listResult = listResult;
        this.context = context;
    }

    @Override
    public EventsAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result, parent, false);
        EventsAdapterHolder holder = new EventsAdapterHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(EventsAdapterHolder holder, int position) {
        try {
            holder.textView.setText(listResult.get(position).getDescription());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        try {
            return listResult.size();
        } catch (Exception e){
            return 0;
        }
    }

    public class EventsAdapterHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {
        TextView textView;

        public EventsAdapterHolder(final View itemView) {
            super(itemView);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            textView = (TextView) itemView.findViewById(R.id.text_view_result);
        }


        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
