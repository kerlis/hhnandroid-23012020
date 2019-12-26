package me.doapps.appdhn.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import me.doapps.appdhn.R;
import me.doapps.appdhn.fragments.WorkaroundMapFragment;
import me.doapps.appdhn.models.PressRelease;

public class PressReleasesDescriptionActivity extends AppCompatActivity {

    private final String TAG = PressReleasesDescriptionActivity.class.getSimpleName();
    private Toolbar mToolbar;
    public static String PARAM_PRESSRELEASE = "param_pressrelease";
    private TextView textViewSubTitle, textViewDescription;
    private String title, subtitle, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_press_releases_description);


        Snackbar.make(findViewById(android.R.id.content),"Mantén actualizada la aplicación", Snackbar.LENGTH_LONG)
                .setAction("Play store", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW)
                                .setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
                        try {
                            startActivity(new Intent(intent)
                                    .setPackage("com.android.vending"));
                        } catch (android.content.ActivityNotFoundException exception) {
                            startActivity(intent);
                        }
                    }
                }).show();


        Intent intent = getIntent();

        if (intent != null && intent.getExtras() != null) {
            Bundle extras = intent.getExtras();
            String titleid = extras.getString("TITLE");

            if (titleid != null) {
                title = titleid;
                subtitle = extras.getString("SUBTITLE");
                content = extras.getString("CONTENTS");

            } else {
                title = intent.getStringExtra("title");
                subtitle = intent.getStringExtra("subtitle");
                content = intent.getStringExtra("content");
            }
        }

        instanceToolbar();
        textViewDescription = (TextView) findViewById(R.id.text_view_description);
        textViewSubTitle = (TextView) findViewById(R.id.text_view_subtitle);


        getSupportActionBar().setTitle(title);
        textViewSubTitle.setText(subtitle);
        textViewDescription.setText(content);

    }

    public void instanceToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                Log.e(TAG, "onOptionsItemSelected");
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
