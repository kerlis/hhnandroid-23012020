package me.doapps.appdhn.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
import me.doapps.appdhn.models.Notification;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    Context context;
    List<Notification> notificationList;

    public NotificationAdapter(List<Notification> notificationList, Context context) {
        this.context = context;
        this.notificationList = notificationList;
    }

    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(NotificationAdapter.ViewHolder holder, final int position) {
        holder.textViewDate.setText(notificationList.get(position).getTitle());
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy' 'HH:mm");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");


        Date date = null;

        try {
            date = simpleDateFormat2.parse(notificationList.get(position).getDateTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.textViewDate2.setText(simpleDateFormat1.format(date));


        holder.textViewDescription.setText(notificationList.get(position).getDescription());

        holder.linearLayoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.item(notificationList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout linearLayoutItem;
        public TextView textViewDate, textViewDate2, textViewDescription;

        public ViewHolder(View v) {
            super(v);
            linearLayoutItem = (LinearLayout) v.findViewById(R.id.linear_layout_item);
            textViewDate = (TextView) v.findViewById(R.id.text_view_date);
            textViewDate2 = (TextView) v.findViewById(R.id.text_view_date2);
            textViewDescription = (TextView) v.findViewById(R.id.text_view_description);
        }
    }

    private OnItemClick onItemClick;

    public interface OnItemClick {
        void item(Notification notification);
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

}