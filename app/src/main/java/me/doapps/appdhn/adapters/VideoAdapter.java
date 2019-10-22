package me.doapps.appdhn.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeStandalonePlayer;

import java.util.List;

import me.doapps.appdhn.R;
import me.doapps.appdhn.activities.VideosActivity;
import me.doapps.appdhn.models.Video;

/**
 * Created by William_ST on 01/10/15.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private final String TAG = VideoAdapter.class.getSimpleName();
    Context context;
    List<Video> data;

    public VideoAdapter(List<Video> data, Context context){
        this.context = context;
        this.data = data;
    }

    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(VideoAdapter.ViewHolder holder, final int position) {
        try {
            holder.imageViewPreview.setImageResource(data.get(position).getPreview());
            holder.textViewTitle.setText(data.get(position).getTitle());
            holder.textViewTime.setText(data.get(position).getTime());

            holder.imageViewPreview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = YouTubeStandalonePlayer.createVideoIntent(((VideosActivity) context), VideosActivity.API_KEY, data.get(position).getVideoId());
                    context.startActivity(intent);
                }
            });
        } catch (NullPointerException e){
            Log.e(TAG, e.toString());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageViewPreview;
        public TextView textViewTitle, textViewTime;
        public ViewHolder(View v) {
            super(v);
            imageViewPreview = (ImageView) v.findViewById(R.id.iv_image_view_preview);
            textViewTitle = (TextView) v.findViewById(R.id.iv_text_view_title);
            textViewTime = (TextView) v.findViewById(R.id.iv_text_view_time);
        }
    }

}

