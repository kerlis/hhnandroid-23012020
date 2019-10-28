package me.doapps.appdhn.adapters;
import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;
import me.doapps.appdhn.R;
import me.doapps.appdhn.activities.MapsActivity;
import me.doapps.appdhn.models.Cartasevacua;
public class Mapaskmladapter extends RecyclerView.Adapter<Mapaskmladapter.MyViewHolder>{

    Context context;
    ArrayList<Cartasevacua> listadozonas;

    public Mapaskmladapter(Context c , ArrayList<Cartasevacua> p) {
        context = c;
        listadozonas = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewtwo, parent, false);
        return new Mapaskmladapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.nombre.setText(listadozonas.get(position).getNombre());
        Log.d("DATA LISTADOZONAS", listadozonas.get(position).getUrl_kml1());
        holder.iramapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  ((MapsActivity)context).cerrarpopup(listadozonas.get(position).getUrl_kml1());


             // ((MapsActivity)context).verpopup(listadozonas.get(position).getUrl_kml1());



              ((MapsActivity)context).cerrarpopup(listadozonas.get(position).getUrl_kml1()+"&&"+listadozonas.get(position).getUrl_kml2()+"&&"+listadozonas.get(position).getUrl_kml3());


//             ((MapsActivity)context).verpopup();



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