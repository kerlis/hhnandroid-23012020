package me.doapps.appdhn.fcm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import me.doapps.appdhn.R;
import me.doapps.appdhn.activities.AlarmActivity;
import me.doapps.appdhn.activities.MapsActivity;
import me.doapps.appdhn.config.Setting;

/**
 * Created by Leandro on 10/24/17.
 */

public class MessagingService extends FirebaseMessagingService {

    private String title2, subtitle, content;
    private String lat, lng;
    private String dato;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = pm.isScreenOn();


        Log.d("datos999:", "" + remoteMessage.getData());

/*


        if (isScreenOn == false) {
            PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "MyLock");
            wl.acquire(10000);
            PowerManager.WakeLock wl_cpu = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyCpuLock");
        }
        **/
        if (remoteMessage.getData() != null) {
            Log.e("DATA_NOTIFICATION", "" + remoteMessage.getData());
            getNotification(
            );
            dato = remoteMessage.getData().get("curpo");
            /*
            title2 = remoteMessage.getData().get("TITLE");
            subtitle = remoteMessage.getData().get("SUBTITLE");
            content = remoteMessage.getData().get("CONTENTS");
            lng = remoteMessage.getData().get("LONGITUDE");
            lat = remoteMessage.getData().get("LATITUDE");
            */

        }
        if (remoteMessage.getNotification() != null) {
            Log.e("SOUND_DATA", remoteMessage.getNotification().getSound() + " null");
             getNotification(
            );
        }


    }

    private void getNotification() {


        Log.e("DATITOS:", "" + "AQUI LOS DATOS PI");

        String id = getString(R.string.chanelMGP);


        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, MapsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, id)
                .setSmallIcon(R.drawable.alarmanotification)
                .setContentTitle("ALERTA DE TSUNAMI")
                .setContentText("DHN - ALERTA DE TSUNAMI")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(55, builder.build());






        /*

        Log.e("notification", "yes" + activity);
        Class c = null;
        try {
            c = Class.forName(getApplicationContext().getPackageName() + ".activities" + activity);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, c);
        intent.putExtra("tile", title);
        intent.putExtra("contex", content);
        intent.putExtra("lat", lat);
        intent.putExtra("lot", lng);
        intent.putExtra("title", title2);
        intent.putExtra("subtitle", subtitle);
        intent.putExtra("content", content);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (activity.equals("SplashActivity") || activity.equals("NotificationDescriptionActivity")) {
            Log.e("ACTIVITYIS", "SplashActivity");
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        Uri soundUri = null;
        int drawableResourceId = this.getResources().getIdentifier(sound, "raw", this.getPackageName());
        soundUri = Uri.parse("android.resource://" + getPackageName() + "/" + drawableResourceId);
        grantUriPermission("com.android.systemui", soundUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String name = getString(R.string.chanelMGP);
        String id = getString(R.string.chanelMGP);
        NotificationCompat.Builder notibuilder;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_MAX;
            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
            mChannel.setDescription(getString(R.string.chanelMGP));
            mChannel.enableLights(true);
            mChannel.setLightColor(ContextCompat.getColor(this, R.color.colorPrimary));
            notificationManager.createNotificationChannel(mChannel);
        }
        notibuilder = new NotificationCompat.Builder(this, name);

        notibuilder.setSmallIcon(R.drawable.ic_notification2_white);
        notibuilder.setColor(ContextCompat.getColor(this, R.color.colorPrimary));
        notibuilder.setContentTitle(title);
        notibuilder.setSound(soundUri);
        notibuilder.setContentText(body);
        notibuilder.setAutoCancel(true);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            notibuilder.setPriority(Notification.PRIORITY_MAX);
        } else {
            notibuilder.setChannelId(id);
        }
        notibuilder.setContentIntent(pendingIntent);
        notificationManager.notify(0, notibuilder.build());
*/

        /*
        if (Setting.isExecute) {
            Setting.isExecute = false;
            Log.e("OPEN_MESSAGE", "changeExecute");
        } else {
            Log.e("OPEN_MESSAGE", "alarmActivity1");
            Intent in = new Intent(getApplicationContext(), AlarmActivity.class);
            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(in);
            Setting.isExecute = false;
        }
        */

    }

    @Override
    public void onCreate() {
        super.onCreate();
        /*
        Setting.isExecute = true;

        Intent in = new Intent(getApplicationContext(), AlarmActivity.class);
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        in.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        // in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //  in.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(in);
        */

    }
}