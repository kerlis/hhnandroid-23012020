package me.doapps.appdhn.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import me.doapps.appdhn.R;
import me.doapps.appdhn.expandible.ExampleExpandableDataProviderFragment;
import me.doapps.appdhn.expandible.FrequentQuestionRecyclerListViewFragment;
import me.doapps.appdhn.expandible.FrequentsQuestionExpandableDataProviderFragment;
import me.doapps.appdhn.expandible.RecyclerListViewFragment;

public class FrequentQuestionsActivity extends AppCompatActivity {

    private final String TAG = FrequentQuestionsActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private static final String FRAGMENT_TAG_DATA_PROVIDER = "data provider";
    private static final String FRAGMENT_LIST_VIEW = "list view";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frequent_questions);


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

        instanceToolbar();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(new FrequentsQuestionExpandableDataProviderFragment(), FRAGMENT_TAG_DATA_PROVIDER)
                    .commit();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new FrequentQuestionRecyclerListViewFragment(), FRAGMENT_LIST_VIEW)
                    .commit();
        }
    }

    public void instanceToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getString(R.string.title_frequent_questions));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
}
