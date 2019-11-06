package me.doapps.appdhn.adapters;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import me.doapps.appdhn.R;
import me.doapps.appdhn.activities.Mapapoligonos;
import me.doapps.appdhn.activities.MapsActivity;
import me.doapps.appdhn.models.Cartasevacua;
import me.doapps.appdhn.models.Departamentos;

public class Listadolugaresadapter extends RecyclerView.Adapter<Listadolugaresadapter.MyViewHolder>{

    DatabaseReference reference2;
    ArrayList<Cartasevacua> list2;
    Mapaskmladapter adapter2;
    Context context;
    ArrayList<Departamentos> listadorecyclerlugares;

    public Listadolugaresadapter(Context c, ArrayList<Departamentos> p) {
        context = c;
        listadorecyclerlugares = p;
    }

    @NonNull
    @Override
    public Listadolugaresadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new Listadolugaresadapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {


        String uri = "@mipmap/"+listadorecyclerlugares.get(position).getMapa();
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
        Drawable res = context.getResources().getDrawable(imageResource);
        holder.mapa.setImageDrawable(res);


        holder.cerrar.setImageResource(R.drawable.noexpand);

        holder.recyclerView2.setVisibility(View.GONE);

        holder.cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

/*
                Intent intent = new Intent(holder.itemView.getContext(), Mapapoligonos.class);
                intent.putExtra("PRIMERA", "SDWDWEDEWD");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.itemView.getContext().startActivity(intent);

*/



                if(holder.recyclerView2.getVisibility() == View.VISIBLE){
                    holder.recyclerView2.setVisibility(View.GONE);
                    holder.cerrar.setImageResource(R.drawable.noexpand);
                }
                else if(holder.recyclerView2.getVisibility() == View.GONE){
                    holder.recyclerView2.setVisibility(View.VISIBLE);
                    holder.cerrar.setImageResource(R.drawable.expand);
                }

            }
        });

        holder.name.setText(listadorecyclerlugares.get(position).getKey());

        holder.recyclerView2.setLayoutManager(new LinearLayoutManager(context));

        reference2 = FirebaseDatabase.getInstance().getReference("bdrefugy").child("cartas4").child(listadorecyclerlugares.get(position).getKey()).child("zonas");

        reference2.addChildEventListener(new ChildEventListener()   {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                list2 = new ArrayList<Cartasevacua>();

                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Cartasevacua p = dataSnapshot1.getValue(Cartasevacua.class);

                    String commentKey = dataSnapshot1.getKey();
                    list2.add(p);
                }


                adapter2 = new Mapaskmladapter(context,list2);
                holder.recyclerView2.setAdapter(adapter2);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public RecyclerView recyclerView2;
        TextView name;
        ImageButton cerrar;
        ImageView mapa;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            cerrar = (ImageButton) itemView.findViewById(R.id.cerrar);
            recyclerView2 = itemView.findViewById(R.id.my_recycler_view2);
            mapa = itemView.findViewById(R.id.mapa);

        }

        public void onClick(final int position) {
            recyclerView2.setVisibility(View.GONE);
            cerrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   recyclerView2.setVisibility(View.GONE);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return listadorecyclerlugares.size();
    }
}
