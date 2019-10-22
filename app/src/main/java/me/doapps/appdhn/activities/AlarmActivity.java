package me.doapps.appdhn.activities;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.ncorti.slidetoact.SlideToActView;


import me.doapps.appdhn.BuildConfig;
import me.doapps.appdhn.R;
import me.doapps.appdhn.config.Setting;
import me.doapps.appdhn.connection.MgpApi;
import me.doapps.appdhn.models.Alert;
import me.doapps.appdhn.session.Preference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlarmActivity extends AppCompatActivity {

    private TextView titleText;
    private SlideToActView slideToActView;
    private View view;
    private MediaPlayer mp;
    Preference preference;
    MgpApi mgpApi;
    Alert alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        preference = Preference.getIntance(this);
        mgpApi = MgpApi.RETROFIT_FIREBASE.create(MgpApi.class);
        slideToActView = findViewById(R.id.example_gray_on_green);
        titleText = findViewById(R.id.title_text);
        view = findViewById(R.id.content_view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        mp = MediaPlayer.create(this, R.raw.sonido_alarma);
        mp.setLooping(true);
        mgpApi.getNotification().enqueue(new Callback<Alert>() {
            @Override
            public void onResponse(Call<Alert> call, Response<Alert> response) {
                alert = response.body();
                if (alert.getTYPE().equals(BuildConfig.TOPIC_BOLETIN) || alert.getTYPE().equals(BuildConfig.TOPIC_SISMO)) {
                    Log.e("TYPE : ", alert.getTYPE());
                    finish();
                } else {
                    view.setVisibility(View.VISIBLE);
                    initAlarm();
                    if (Setting.countAlarm == 0) {
                        Log.e("ALARM","START : " + Setting.countAlarm);
                        mp.start();
                    }

                    Setting.countAlarm++;
                }
            }

            @Override
            public void onFailure(Call<Alert> call, Throwable t) {

            }
        });
    }

    private void initAlarm() {
        titleText.setText(alert.getBODY());
        slideToActView.setOnSlideCompleteListener(new SlideToActView.OnSlideCompleteListener() {
            @Override
            public void onSlideComplete(SlideToActView slideToActView) {
                if (slideToActView.isCompleted()) {
                    if (mp != null) {
                        if (mp.isPlaying()) {
                            mp.stop();
                        }
                    }
                    Setting.countAlarm--;
                    finish();
                    Intent intent = new Intent(AlarmActivity.this, NotificationDescriptionActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("lat", alert.getLATITUDE() + "");
                    intent.putExtra("lot", alert.getLONGITUDE() + "");
                    intent.putExtra("tile", alert.getTITLE());
                    intent.putExtra("contex", alert.getCONTENTS());
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onPause() {
        Log.e("onPause", "onPause");
        super.onPause();
        //mp.pause();


    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mp != null) {
            if (mp.isPlaying()) {
                mp.pause();
                Log.e("ALARM", "onPause");
            }
        }

    }

    @Override
    protected void onResume() {
        Log.e("onResume", "onResume");
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true);
            setTurnScreenOn(true);
            KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
            keyguardManager.requestDismissKeyguard(this, null);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audio.setStreamVolume(AudioManager.STREAM_MUSIC, audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);


        if (alert != null && mp != null) {
            if (!mp.isPlaying()){
                    Log.e("onResume", "START");
                mp.start();
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        //super.onNewIntent(intent);
    }
}