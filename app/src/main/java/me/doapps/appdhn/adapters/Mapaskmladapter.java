package me.doapps.appdhn.adapters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import me.doapps.appdhn.R;
import me.doapps.appdhn.models.Cartasevacua;

public class Mapaskmladapter extends RecyclerView.Adapter<Mapaskmladapter.MyViewHolder>{

    Context context;
    ArrayList<Cartasevacua> listadorecyclerlugares;

    public Mapaskmladapter(Context c , ArrayList<Cartasevacua> p) {
        context = c;
        listadorecyclerlugares = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent,false));



    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(listadorecyclerlugares.get(position).getFuente());
        holder.email.setText(listadorecyclerlugares.get(position).getUrlkml());
    }

    @Override
    public int getItemCount() {
        return listadorecyclerlugares.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,email;
        ImageView profilePic;
        Button btn;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            email = (TextView) itemView.findViewById(R.id.email);
            btn = (Button) itemView.findViewById(R.id.checkDetails);
        }

        public void onClick(final int position) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, position+" is clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

}