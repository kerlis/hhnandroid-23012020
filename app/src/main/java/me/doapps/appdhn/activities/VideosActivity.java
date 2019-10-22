package me.doapps.appdhn.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import me.doapps.appdhn.R;
import me.doapps.appdhn.adapters.VideoAdapter;
import me.doapps.appdhn.models.Video;
import me.doapps.appdhn.utils.ValueHelper;

public class VideosActivity extends AppCompatActivity {

    public static final String API_KEY = "AIzaSyAqKCW2omWNDJdNmGP01GrPhsxfmFGwO0A";
    public final String TAG = VideosActivity.class.getSimpleName();
    Toolbar mToolbar;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layout_manager;
    VideoAdapter adapter;
    List<Video> videoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

        instanceToolbar();
        recyclerView = (RecyclerView) findViewById(R.id.videosList);

        layout_manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layout_manager);

        videoList = new ArrayList<>();

        for(Map.Entry<Integer, Video> row : ValueHelper.collectionVideos.entrySet()){
            videoList.add(row.getValue());
        }


//        videoList.add(new Video("Bacon ipsum dolor sit amet porchetta turkey leberkas", R.mipmap.im_holder_video, "10m", "http://www.google.com", "Ws32llLr450"));
//        videoList.add(new Video("Bacon ipsum dolor sit amet porchetta turkey leberkas", R.mipmap.im_holder_video, "10m", "http://www.google.com", "Ws32llLr450"));

        adapter = new VideoAdapter(videoList, VideosActivity.this);
        recyclerView.setAdapter(adapter);
    }

    public void instanceToolbar(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getString(R.string.title_videos));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_tips, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                Log.e(TAG,  "onOptionsItemSelected");
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
