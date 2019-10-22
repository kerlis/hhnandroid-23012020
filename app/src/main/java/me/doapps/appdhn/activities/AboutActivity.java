package me.doapps.appdhn.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import me.doapps.appdhn.R;


public class AboutActivity extends AppCompatActivity {

    private final String TAG = AboutActivity.class.getSimpleName();
    Toolbar mToolbar;
    ImageView imageViewWWW, imageViewTwitter, imageViewFacebook, imageViewYoutube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        instanceToolbar();
        imageViewWWW = (ImageView) findViewById(R.id.image_view_www);
        imageViewTwitter = (ImageView) findViewById(R.id.image_view_twitter);
        imageViewFacebook = (ImageView) findViewById(R.id.image_view_facebook);
        imageViewYoutube = (ImageView) findViewById(R.id.image_view_youtube);

        imageViewWWW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url_www)));
                startActivity(intent);
            }
        });

        imageViewTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url_twitter)));
                startActivity(intent);
            }
        });

        imageViewFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url_facebook)));
                startActivity(intent);
            }
        });

        imageViewYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url_youtube)));
                startActivity(intent);
            }
        });
    }

    public void instanceToolbar(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getString(R.string.title_about));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tips, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_share:
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
                    String sAux = "\n"+getString(R.string.s_text)+":\n\n";
                    sAux = sAux + "https://play.google.com/store/apps/details?id=me.doapps.appdhn&hl=es";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, getString(R.string.s_share)));
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
