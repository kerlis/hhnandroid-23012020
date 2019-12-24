package me.doapps.appdhn.activities;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.google.android.gms.gcm.GcmReceiver;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

import me.doapps.appdhn.R;

public class GcmBroadcastReceiver extends GcmReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        String titulo, cuerpo, id,id2;
        cuerpo =  bundle.getString("gcm.notification.body");
        titulo =  bundle.getString("gcm.notification.title");
        id =  bundle.getString("gcm.notification.registration_id");
        id2 =  bundle.getString("gcm.notification.message_id");


        procesar(context,cuerpo,titulo);


        Log.d("BroadCast Values ZZZxm", cuerpo + "/" + "\n" + "/" +   titulo );

        Log.d("BroadCast Values ZZZvs", " " + bundle.toString());

        Log.d("BroadCast Values ZZZvse", id2 + "");


        Random rand = new Random();
        int codigo = rand.nextInt(100);
        bundle.keySet();
        Set<String> keySet = bundle.keySet();


        if(keySet != null && keySet.isEmpty() == false) {
            Iterator<String> it = keySet.iterator();
            int i = 0;
            while(it.hasNext()){
                String  key = it.next();
                String  desc = bundle.getString(key);
                Log.d("BroadCast Values ZZZx", " " + bundle.getString("gcm.notification.title"));
                Log.d("BroadCast Values ZZZx", " " + bundle.getString("gcm.notification.body"));
            }
        }

        else{
            Log.d("", "In Receive Method of Broadcast Receiver");

        }



        Log.d("", "In Receive Method of Broadcast Receiver");

        if (bundle != null && bundle.containsKey("gcm.notification.body")) {
            String message = bundle.getString("gcm.notification.body","");
            Long ID = new Date().getTime();
            String date = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.ENGLISH).format(new Date());
            Log.d("", "MENSAJE" +  message);
        }

        super.onReceive(context, intent);

    }



    private void sendNotificationtwo(Context context,String title,String message) {
        Intent notificationintent = new Intent(context, Mapapoligonos.class);
        TaskStackBuilder stackbuilder = TaskStackBuilder.create(context);
        stackbuilder.addParentStack(Mapapoligonos.class);
        stackbuilder.addNextIntent(notificationintent);

        PendingIntent pendingintent = stackbuilder.getPendingIntent(100, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder b = new NotificationCompat.Builder(context);

        Uri defaultSoundUri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.beep2);


        Notification notification = b.setContentTitle("->" + title)
                .setContentText("->" + message)
                .setContentTitle("->" + message)
                .setColor(Color.parseColor("#0D2142"))
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingintent).build();

        Random rand = new Random();
        int codigo = rand.nextInt(100);



        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(codigo, notification);

    }


    private void procesar(Context context,String title,String message) {
        eliminar(context,title, message);
        // sendNotificationTHREE(context, title, message);
        eliminar(context,title, message);
    }

    private void eliminar(Context context ,String title,String message) {
        Removenotificationtwo funcion = new Removenotificationtwo();
        funcion.removerz(context, title, message);
    }


    private void sendNotificationTHREE(Context context,String title,String message) {
        Intent notificationintent = new Intent(context, Mapapoligonos.class);
        TaskStackBuilder stackbuilder = TaskStackBuilder.create(context);
        stackbuilder.addParentStack(Mapapoligonos.class);
        stackbuilder.addNextIntent(notificationintent);

        Uri defaultSoundUri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.beep2);
        PendingIntent pendingintent = stackbuilder.getPendingIntent(100, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder b = new NotificationCompat.Builder(context);

        Notification notification = b.setContentTitle("->" + title)
                .setContentText("->" + message)
                .setContentTitle("->" + message)
                .setColor(Color.parseColor("#0D2142"))
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingintent).build();

        //Random rand = new Random();
        //int aNumber = (int) (20 * Math.random()) + 1;

        Random rand = new Random();
        int codigo = rand.nextInt(100);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(5, notification);
        //  notificationManager.cancel(5);
    }










}


