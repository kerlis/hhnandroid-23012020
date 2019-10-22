package me.doapps.appdhn.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import me.doapps.appdhn.R;
import me.doapps.appdhn.adapters.ViewPagerAdapter;
import me.doapps.appdhn.databases.DatabaseHelper;
import me.doapps.appdhn.fragments.BulletinFragment;
import me.doapps.appdhn.models.Bulletin;
import me.doapps.appdhn.utils.ConnectionUtil;
import me.doapps.appdhn.utils.DateUtil;
import me.doapps.appdhn.utils.HtmlDepure;
import me.doapps.appdhn.utils.PreferencesUtil;
import me.doapps.appdhn.utils.PreprocessorHelper;
import me.doapps.appdhn.utils.UrlHelper;


public class BulletinNoticesActivity extends AppCompatActivity {

    private ConnectionUtil connectionUtil;
    private DatabaseHelper databaseHelper;
    private final String TAG = BulletinNoticesActivity.class.getSimpleName();
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    int[] icons;
    private boolean flagTidesWinds = false, flagSismic = false;
    PreferencesUtil preferencesUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletin_notices);

        preferencesUtil = new PreferencesUtil(BulletinNoticesActivity.this);
        connectionUtil = new ConnectionUtil(BulletinNoticesActivity.this);

        icons = new int[]{
                R.mipmap.ic_tides, R.mipmap.ic_earthquakes, R.mipmap.ic_winds
        };
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_bulletin_notices);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final ProgressDialog progressDialog = new ProgressDialog(BulletinNoticesActivity.this);
        progressDialog.setMessage(getString(R.string.loading));
        progressDialog.setCancelable(false);
        progressDialog.show();

        if (!preferencesUtil.getUpdateBulleting().equals(DateUtil.getCurrentDate()) && connectionUtil.connect()) {

            AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

            asyncHttpClient.get(BulletinNoticesActivity.this, UrlHelper.sismic, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    try {
                        String response = new String(responseBody, "UTF-8");
                        PreprocessorHelper preprocessorHelper = new PreprocessorHelper(BulletinNoticesActivity.this);
                        HtmlDepure htmlDepure = new HtmlDepure(BulletinNoticesActivity.this);

                        htmlDepure.setInterfaceSaveBulletin(new HtmlDepure.InterfaceSaveBulletin() {
                            @Override
                            public void finish() {
                                flagSismic = true;
                                if (flagTidesWinds && flagSismic) {
                                    try {
                                        setupViewPager(viewPager);
                                        tabLayout.setupWithViewPager(viewPager);

                                        for (int i = 0; i < tabLayout.getTabCount(); i++) {
                                            tabLayout.getTabAt(i).setIcon(icons[i]);
                                        }

                                        tabLayout.setTabMode(TabLayout.MODE_FIXED);
                                        progressDialog.dismiss();
                                    } catch (Exception e) {
                                        Log.e(TAG, "finish() " + e.toString());
                                    }
                                    preferencesUtil.setUpdateBulleting(DateUtil.getCurrentDate());
                                }
                            }
                        });
                        if (!preprocessorHelper.cutBulletin(response).equals("")) {
                            htmlDepure.loadBulletin(preprocessorHelper.cutBulletin(response), true);
                        } else {
                            Log.e(TAG, "temp is null!! D:");
                        }

                    } catch (Exception e) {
                        Log.e(TAG, "onSuccess " + e.toString());
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    progressDialog.dismiss();
                }
            });

            asyncHttpClient.get(BulletinNoticesActivity.this, UrlHelper.bulleting, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    try {

                        String response = new String(responseBody, "UTF-8");
                        PreprocessorHelper preprocessorHelper = new PreprocessorHelper(BulletinNoticesActivity.this);
                        HtmlDepure htmlDepure = new HtmlDepure(BulletinNoticesActivity.this);


                        htmlDepure.setInterfaceSaveBulletin(new HtmlDepure.InterfaceSaveBulletin() {
                            @Override
                            public void finish() {
                                flagTidesWinds = true;
                                if (flagTidesWinds && flagSismic) {
                                    try {
                                        setupViewPager(viewPager);
                                        tabLayout.setupWithViewPager(viewPager);

                                        for (int i = 0; i < tabLayout.getTabCount(); i++) {
                                            tabLayout.getTabAt(i).setIcon(icons[i]);
                                        }

                                        tabLayout.setTabMode(TabLayout.MODE_FIXED);
                                        progressDialog.dismiss();
                                    } catch (Exception e) {
                                        Log.e(TAG, "finish() " + e.toString());
                                    }
                                    preferencesUtil.setUpdateBulleting(DateUtil.getCurrentDate());
                                }
                            }
                        });
                        if (!preprocessorHelper.cutBulletin(response).equals("")) {
                            htmlDepure.loadBulletin(preprocessorHelper.cutBulletin(response), false);
                        } else {
                            Log.e(TAG, "temp is null!! D:");
                        }

                    } catch (Exception e) {
                        Log.e(TAG, "onSuccess " + e.toString());
                    }

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    progressDialog.dismiss();
                }
            });
        }else{
            try {
                setupViewPager(viewPager);
                tabLayout.setupWithViewPager(viewPager);

                for (int i = 0; i < tabLayout.getTabCount(); i++) {
                    tabLayout.getTabAt(i).setIcon(icons[i]);
                }

                tabLayout.setTabMode(TabLayout.MODE_FIXED);

                progressDialog.dismiss();
            } catch (Exception e) {
                Log.e(TAG, "finish() " + e.toString());
                progressDialog.dismiss();
            }
        }
    }

    public DatabaseHelper getDatabaseHelper() {
        if (databaseHelper == null)
            databaseHelper = new DatabaseHelper(BulletinNoticesActivity.this);
        return databaseHelper;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(BulletinFragment.newInstance(0));
        adapter.addFrag(BulletinFragment.newInstance(1));
        adapter.addFrag(BulletinFragment.newInstance(2));
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_tips, menu);
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
