package me.doapps.appdhn.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
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
import java.util.TimeZone;

import me.doapps.appdhn.R;
import me.doapps.appdhn.activities.NotificationDescriptionActivity;
import me.doapps.appdhn.activities.PressReleasesDescriptionActivity;
import me.doapps.appdhn.config.Setting;
import me.doapps.appdhn.models.MessageNotification;

/**
 * Created by Leandro on 11/7/17.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder> {

    private Activity activity;
    private List<MessageNotification> list;

    public MessageAdapter(Activity activity, List<MessageNotification> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new MessageHolder(v);
    }

    @Override
    public void onBindViewHolder(final MessageHolder holder, final int position) {
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        if (list.get(position).getType().equalsIgnoreCase("boletin")) {
            holder.contenLinear.setBackgroundColor(ContextCompat.getColor(activity, R.color.blueLight));
        } else if (list.get(position).getType().equalsIgnoreCase("alerta")) {
            holder.contenLinear.setBackgroundColor(ContextCompat.getColor(activity, R.color.orangeBasick));
        } else if (list.get(position).getType().equalsIgnoreCase("alarma")) {
            holder.contenLinear.setBackgroundColor(ContextCompat.getColor(activity, R.color.red));
        } else {
            holder.contenLinear.setBackgroundColor(ContextCompat.getColor(activity, R.color.red));
        }

        Date date = null;

        try {
            date = simpleDateFormat2.parse(list.get(position).getDatesTamp());
            Log.e("date", date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.dateText.setText(simpleDateFormat1.format(date));
        holder.subtTitleText.setText(list.get(position).getSubtitle());
        holder.descripText.setText(list.get(position).getBody());

        holder.contenSecondLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(getActivity(list.get(position).getType(), list.get(position)));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MessageHolder extends RecyclerView.ViewHolder {

        TextView dateText;
        TextView subtTitleText;
        TextView descripText;
        LinearLayout contenLinear, contenSecondLinear;

        public MessageHolder(View itemView) {
            super(itemView);

            dateText = (TextView) itemView.findViewById(R.id.date_text);
            subtTitleText = (TextView) itemView.findViewById(R.id.subt_title_text);
            descripText = (TextView) itemView.findViewById(R.id.descrip_text);
            contenLinear = (LinearLayout) itemView.findViewById(R.id.conten_linear);
            contenSecondLinear = (LinearLayout) itemView.findViewById(R.id.conten_second_linear);
        }
    }

    private Intent getActivity(String type, MessageNotification x) {
        Intent intent = null;

        if (type.equalsIgnoreCase("sonido_informativo")) {

            intent = new Intent(activity, PressReleasesDescriptionActivity.class);
            intent.putExtra("title", x.getTitle());
            intent.putExtra("subtitle", x.getSubtitle());
            intent.putExtra("content", x.getContent());

        } else {

            intent = new Intent(activity, NotificationDescriptionActivity.class);
            intent.putExtra("lat", x.getLatitude());
            intent.putExtra("lot", x.getLongitude());
            intent.putExtra("tile", x.getTitle());
            intent.putExtra("contex", x.getContent());
            intent.putExtra(Setting.BACK_KEY, Setting.BACK_NORMAL);
        }

        return intent;
    }
}