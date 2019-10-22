package me.doapps.appdhn.adapters;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.util.List;

import me.doapps.appdhn.R;
import me.doapps.appdhn.models.FileDate;
import me.doapps.appdhn.models.FileProvince;
import me.doapps.appdhn.sqllite.Setences;

/**
 * Created by Leandro on 10/18/17.
 */

public class FileProvinceAdapter extends RecyclerView.Adapter<FileProvinceAdapter.FilePRovinceHHolder> {

    private Activity activity;
    private List<FileProvince> list;
    private File mDir;
    private Setences setences;

    public FileProvinceAdapter(Activity activity, List<FileProvince> list) {
        this.activity = activity;
        this.list = list;
        setences = new Setences(activity);
    }

    @Override
    public FilePRovinceHHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FilePRovinceHHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_file_province, parent, false));
    }

    @Override
    public void onBindViewHolder(final FilePRovinceHHolder holder, final int position) {
        holder.progressBar.setVisibility(list.get(position).isDownload() ? View.VISIBLE : View.INVISIBLE);
        holder.descargaButton.setText(list.get(position).isDownload() ? "" : list.get(position).isStatus() ? "VER" : "DESCARGAR");
        holder.titleText.setText(list.get(position).getName());
        holder.descargaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("isStatus", list.get(position).isStatus() +"");
                Log.e("isButtonstatus", list.get(position).isButtonstatus() +"");
                if (!list.get(position).isButtonstatus()) {

                    if (!list.get(position).isStatus()) {
                        Log.e("download_log","file");
                        list.get(position).setDownload(true);
                        notifyItemChanged(position);
                        getFileData(list.get(position).getUrlbase(), list.get(position).getNameexten(), position, holder.descargaButton);
                    } else {

                        if (isStatuFile(getStringURL(list.get(position).getNameexten()))) {

                            if (getCaracter(list.get(position).getNameexten()).equalsIgnoreCase("pdf")) {
                                Uri uri = Uri.parse(list.get(position).getUrlbase());
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                activity.startActivity(intent);
                            } else {
                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_VIEW);
                                intent.setDataAndType(Uri.parse(list.get(position).getUrlbase()), "image/*");
                                activity.startActivity(intent);
                            }

                        } else {
                            getMessage();
                        }
                    }
                } else {
                    Toast.makeText(activity, "Espere, descargando archivo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FilePRovinceHHolder extends RecyclerView.ViewHolder {

        TextView titleText;
        Button descargaButton;
        ProgressBar progressBar;

        public FilePRovinceHHolder(View itemView) {
            super(itemView);

            titleText = (TextView) itemView.findViewById(R.id.title_text);
            descargaButton = (Button) itemView.findViewById(R.id.descarga_button);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    private void getFileData(String rutabase, String filename, int position, Button button) {

        for (FileProvince x : list) {
            x.setButtonstatus(true);
        }
        notifyDataSetChanged();

        File URL_BASE = Environment.getExternalStorageDirectory();
        mDir = new File(URL_BASE, "mgp");
        mDir.mkdirs();

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(rutabase));
        request.setDescription("Downloading file " + filename);
        request.setTitle("Descargando");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        request.setDestinationInExternalPublicDir("/mgp", filename);

        if (getStringURL(filename).isEmpty()) {
            FileDate x = new FileDate(list.get(position).getNameexten().replace("%20", " "), mDir.getAbsolutePath() + "/" + filename.trim().replace("%20", " "));
            setences.registro(x);
        }

        DownloadManager manager = (DownloadManager) activity.getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);

        activity.registerReceiver(new mReceiver(position, button


        ), new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    private String getStringURL(String filename) {

        List<FileDate> lista = setences.lista_histori();

        for (FileDate x : lista) {
            if (x.getName().equals(filename.replace("%20", " "))) {
                return x.getBaseUrl();
            }
        }
        return "";
    }

    private boolean isStatuFile(String url) {
        File file = new File(url);

        if (file.exists()) {
            return true;
        }
        return false;
    }

    private void getMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("El Archivo no se encuentra");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private class mReceiver extends BroadcastReceiver {
        private int position;
        private Button button;

        public mReceiver(int position, Button button) {
            this.position = position;
            this.button = button;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            list.get(position).setStatus(true);
            list.get(position).setDownload(false);
            for (FileProvince x : list) {
                x.setButtonstatus(false);
            }
            notifyItemChanged(position);
        }
    }

    private String getCaracter(String name) {
        return name.substring(name.length() - 3);
    }
}