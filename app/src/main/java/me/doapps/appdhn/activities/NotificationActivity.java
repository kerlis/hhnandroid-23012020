package me.doapps.appdhn.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import me.doapps.appdhn.R;
import me.doapps.appdhn.connection.MgpApi;
import me.doapps.appdhn.databases.DatabaseHelper;
import me.doapps.appdhn.models.Notification;

public class NotificationActivity extends AppCompatActivity {

    private final String TAG = NotificationActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private RecyclerView recyclerView;
    private DatabaseHelper databaseHelper;
    private List<Notification> notificationList;

    private MgpApi mgpApi = MgpApi.RETROFIT_MGP.create(MgpApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        instanceToolbar();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        notificationList = new ArrayList<>();

        /*mgpApi.getListPush("sonido_alarma", 10).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                for (JsonElement x : response.body().get("data").getAsJsonArray()) {
                    notificationList.add(new Notification(x.getAsJsonObject().get("DATESTAMP").getAsString()
                            , x.getAsJsonObject().get("TITLE").getAsString(), x.getAsJsonObject().get("CONTENT").getAsString()
                            , x.getAsJsonObject().get("LATITUDE").getAsDouble(), x.getAsJsonObject().get("LONGITUDE").getAsDouble(), true));
                }

                NotificationAdapter notificationAdapter = new NotificationAdapter(notificationList, NotificationActivity.this);
                notificationAdapter.setOnItemClick(new NotificationAdapter.OnItemClick() {
                    @Override
                    public void item(Notification notification) {
                        try {
                            Intent intent = new Intent(NotificationActivity.this, NotificationDescriptionActivity.class);
                            intent.putExtra(NotificationDescriptionActivity.PARAM_NOTIFICATION, notification);
                            startActivity(intent);
                        } catch (Exception e) {
                            Log.e(TAG, "item " + e.toString());
                        }
                    }
                });
                recyclerView.setAdapter(notificationAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(NotificationActivity.this));
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });*/
    }

    public void instanceToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getString(R.string.title_notifications));
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
