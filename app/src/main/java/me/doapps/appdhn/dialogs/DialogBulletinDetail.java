package me.doapps.appdhn.dialogs;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import me.doapps.appdhn.R;
import me.doapps.appdhn.models.Bulletin;
import me.doapps.appdhn.utils.UrlHelper;

/**
 * Created by William_ST on 13/10/15.
 */
public class DialogBulletinDetail extends AlertDialog {

    final String TAG = DialogBulletinDetail.class.getSimpleName();
    Context context;

    public  DialogBulletinDetail(Context context, Bulletin bulletin) {
        super(context);
        this.context = context;
        initDialog(bulletin);
    }

    public void initDialog(final Bulletin bulletin){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_bulletin_detail, null);
        setView(view);

        ((TextView) view.findViewById(R.id.text_view_title)).setText(Html.fromHtml(bulletin.getTitle()));
        ((TextView) view.findViewById(R.id.text_view_sub_title)).setText(Html.fromHtml(bulletin.getSubtitle()));
        ((TextView) view.findViewById(R.id.text_view_description)).setText(Html.fromHtml(bulletin.getDescription()));
        ((ImageView) view.findViewById(R.id.image_view_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Download(bulletin.getTitle(), UrlHelper.downloadPreUrlBulletin+bulletin.getUrl());
            }
        });

    }

    public void Download(String name, String url) {
        Log.e(TAG, "URL: "+url);
        Toast.makeText(context, "Descarga iniciada", Toast.LENGTH_SHORT).show();
        File direct = new File(Environment.getExternalStorageDirectory()
                + Environment.DIRECTORY_DOWNLOADS + "/Cartas de inundación");
        if (!direct.exists()) {
            direct.mkdirs();
        }

        final DownloadManager dm = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle(name);
        request.setDescription("Carta de inundación");
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS + "/Cartas de inundación", name + ".pdf");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        final long enqueue = dm.enqueue(request);

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(enqueue);
                    Cursor c = dm.query(query);
                    if (c.moveToFirst()) {
                        int columnIndex = c.getColumnIndex(DownloadManager.COLUMN_STATUS);
                        if (DownloadManager.STATUS_SUCCESSFUL == c.getInt(columnIndex)) {
//                            Toast.makeText(context, "Descarga Completa!!!", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        };

        context.registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }
}
