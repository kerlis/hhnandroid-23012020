package me.doapps.appdhn.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import me.doapps.appdhn.R;
import me.doapps.appdhn.adapters.MessageAdapter;
import me.doapps.appdhn.adapters.PressreleaseAdapter;
import me.doapps.appdhn.connection.MgpApi;
import me.doapps.appdhn.models.MessageNotification;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PressReleasesActivity extends AppCompatActivity {

    private final String TAG = PressreleaseAdapter.class.getSimpleName();
    private Toolbar mToolbar;
    private RecyclerView recyclerView;
    private MgpApi mgpApi = MgpApi.RETROFIT_MGP.create(MgpApi.class);
    private List<MessageNotification> list;
    private MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_press_releases);
        instanceToolbar();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        list = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        mgpApi.getListPush(10).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.e("ENTRO", response.body() + "");

                for (JsonElement x : response.body().get("data").getAsJsonArray()) {
                    list.add(new MessageNotification(isNull(x.getAsJsonObject().get("ID"))
                            , isNull(x.getAsJsonObject().get("DATESTAMP"))
                            , isNull(x.getAsJsonObject().get("TITLE"))
                            , isNull(x.getAsJsonObject().get("BODY"))
                            , isNull(x.getAsJsonObject().get("LATITUDE"))
                            , isNull(x.getAsJsonObject().get("LONGITUDE"))
                            , isNull(x.getAsJsonObject().get("SUBTITLE"))
                            , isNull(x.getAsJsonObject().get("CONTENTS"))
                            , isNull(x.getAsJsonObject().get("TYPE"))
                            , isNull(x.getAsJsonObject().get("SOUND"))));
                }

                messageAdapter = new MessageAdapter(PressReleasesActivity.this, list);
                recyclerView.setAdapter(messageAdapter);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
            }
        });
    }

    public void instanceToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getString(R.string.title_pressrelease));
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

    private String isNull(JsonElement x) {
        if (x.isJsonNull()) {
            return "";
        }
        return x.getAsString();
    }
}
