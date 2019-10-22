package me.doapps.appdhn.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rey.material.app.Dialog;
import com.rey.material.app.DialogFragment;
import com.rey.material.app.SimpleDialog;

import java.util.List;

import me.doapps.appdhn.R;
import me.doapps.appdhn.activities.BulletinNoticesActivity;
import me.doapps.appdhn.dialogs.DialogBulletinDetail;
import me.doapps.appdhn.models.Bulletin;

/**
 * Created by William_ST on 01/10/15.
 */
public class BulletinAdapter extends RecyclerView.Adapter<BulletinAdapter.ViewHolder> {

    Context context;
    List<Bulletin> data;
    private final String TAG = BulletinAdapter.class.getSimpleName();

    public BulletinAdapter(Context context, List<Bulletin> data){
        this.context = context;
        this.data = data;
    }

    @Override
    public BulletinAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bulletin, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(BulletinAdapter.ViewHolder holder, final int position) {
        holder.textViewTitle.setText(Html.fromHtml(data.get(position).getTitle()));
        holder.textViewDescription.setText(Html.fromHtml(data.get(position).getSubtitle().trim())+": "+Html.fromHtml(data.get(position).getDescription().trim()));
        holder.linearLayoutItemBulletin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogBulletinDetail dialogBulletinDetail = new DialogBulletinDetail(context, data.get(position));
                dialogBulletinDetail.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public LinearLayout linearLayoutItemBulletin;
        public TextView textViewTitle, textViewDescription;

        public ViewHolder(View v) {
            super(v);
            textViewTitle = (TextView) v.findViewById(R.id.ib_title);
            textViewDescription = (TextView) v.findViewById(R.id.ib_description);
            linearLayoutItemBulletin = (LinearLayout) v.findViewById(R.id.item_bulletin);
        }
    }

}

