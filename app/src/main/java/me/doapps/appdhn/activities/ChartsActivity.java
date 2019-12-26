package me.doapps.appdhn.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import me.doapps.appdhn.R;
import me.doapps.appdhn.databases.DatabaseHelper;
import me.doapps.appdhn.expandible.ExampleExpandableDataProviderFragment;
import me.doapps.appdhn.expandible.MyExpandableItemAdapter;
import me.doapps.appdhn.expandible.RecyclerListViewFragment;
import me.doapps.appdhn.utils.DateUtil;
import me.doapps.appdhn.utils.HtmlDepure;
import me.doapps.appdhn.utils.PermissionUtil;
import me.doapps.appdhn.utils.PreferencesUtil;
import me.doapps.appdhn.utils.PreprocessorHelper;
import me.doapps.appdhn.utils.UrlHelper;
import me.doapps.appdhn.utils.ValueHelper;


public class ChartsActivity extends AppCompatActivity {

    private final String TAG = ChartsActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private static final String FRAGMENT_TAG_DATA_PROVIDER = "data provider";
    private static final String FRAGMENT_LIST_VIEW = "list view";
    public LinearLayout linearLayoutContent;

    private HtmlDepure htmlDepure;
    private DatabaseHelper databaseHelper;

//    PreferencesUtil preferencesUtil;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);


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


//        preferencesUtil = new PreferencesUtil(ChartsActivity.this);

        instanceToolbar();
        linearLayoutContent = (LinearLayout) findViewById(R.id.linear_layout_content);
//        if (preferencesUtil.getUpdateCharts().equals(DateUtil.getCurrentDate())) {

//            final ProgressDialog progressDialog = new ProgressDialog(ChartsActivity.this);
//            progressDialog.setCancelable(false);
//            progressDialog.show();
/*
            htmlDepure = new HtmlDepure(ChartsActivity.this);
            AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
            asyncHttpClient.get(ChartsActivity.this, UrlHelper.charts, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    try {
                        String temp = new String(responseBody, "UTF-8");
                        htmlDepure.setInterfaceSavecharts(new HtmlDepure.InterfaceSavecharts() {
                            @Override
                            public void finish() {

                            }
                        });
                        htmlDepure.loadCharts(new PreprocessorHelper(ChartsActivity.this).cutCharts(temp));
//                        preferencesUtil.setUpdateCharts(DateUtil.getCurrentDate());
                    } catch (Exception e) {
//                        progressDialog.dismiss();
                        Log.e(TAG, "onSuccess " + e.toString());
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                    progressDialog.dismiss();
                }
            });
*/
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(new ExampleExpandableDataProviderFragment(), FRAGMENT_TAG_DATA_PROVIDER)
                    .commit();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new RecyclerListViewFragment(), FRAGMENT_LIST_VIEW)
                    .commit();
//                                    progressDialog.dismiss();
        }
    }
//    }


    public DatabaseHelper getDataBaseHelper() {
        if (databaseHelper == null) databaseHelper = new DatabaseHelper(ChartsActivity.this);
        return databaseHelper;
    }

    public void instanceToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getString(R.string.title_downloadable_content));
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
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PermissionUtil.REQUEST_WRITE) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                MyExpandableItemAdapter.Download(ChartsActivity.this, MyExpandableItemAdapter.tempName, MyExpandableItemAdapter.tempUrl);
            }
        }
    }
}
