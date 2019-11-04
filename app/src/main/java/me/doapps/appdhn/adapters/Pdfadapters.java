package me.doapps.appdhn.adapters;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import me.doapps.appdhn.R;
import me.doapps.appdhn.models.Cartaspdf;

public class Pdfadapters extends RecyclerView.Adapter<Pdfadapters.MyViewHolder>{
    DownloadManager descarga;

    Context context;
    ArrayList<Cartaspdf> listadozonas;

    public Pdfadapters(Context c , ArrayList<Cartaspdf> p) {
        context = c;
        listadozonas = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewpdfs, parent, false);
        return new Pdfadapters.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.nombre.setText(listadozonas.get(position).getNombre());
        Log.d("DATA LISTADOZONAS", listadozonas.get(position).getUrl_pdf());
        holder.iramapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, position+" is clicked", Toast.LENGTH_SHORT).show();
                descarga = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri =  Uri.parse(listadozonas.get(position).getUrl_pdf());
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference = descarga.enqueue(request);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listadozonas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        ImageButton iramapa;

        public MyViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.nombre);
            iramapa = (ImageButton) itemView.findViewById(R.id.iramapa);
        }

        public void onClick(final int position) {
/*
            iramapa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Toast.makeText(context, position+" is clicked", Toast.LENGTH_SHORT).show();
                }
            });
            */

        }
    }
}