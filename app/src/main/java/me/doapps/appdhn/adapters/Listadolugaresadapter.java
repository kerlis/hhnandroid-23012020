package me.doapps.appdhn.adapters;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.gcm.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import me.doapps.appdhn.R;
import me.doapps.appdhn.activities.ClickPosition;
import me.doapps.appdhn.activities.Listadorecyclerlugares;
import me.doapps.appdhn.models.Cartasevacua;

public class Listadolugaresadapter extends RecyclerView.Adapter<Listadolugaresadapter.MyViewHolder>{


    DatabaseReference mFirebaseDatabase4;
    DatabaseReference reference2;
    RecyclerView recyclerView2;
    ArrayList<Cartasevacua> list2;
    Listadolugaresadapter adapter2;


    Context context;
    ArrayList<Cartasevacua> listadorecyclerlugares;
    ClickPosition clickPosition;





    public Listadolugaresadapter(Context c , ArrayList<Cartasevacua> p) {
        context = c;
        listadorecyclerlugares = p;
    }

    @NonNull
    @Override
    public Listadolugaresadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new Listadolugaresadapter.MyViewHolder(itemView);

       // return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
/*
        int der = getItemCount();
        long k =  getItemId(position);
        holder.getPosition();
        Task task = DataList.get(position);
        final String key_value = Listadorecyclerlugares.list.get(position);
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent singleActivity = new Intent(context,single_task.class);
                singleActivity.putExtra("TaskId",key_value);
                System.out.println("KeyValue is ================================================>"+key_value);
                context.startActivity(singleActivity);
            }
        });
        */


     /*
        final Class<? extends Cartasevacua> key  = listadorecyclerlugares.get(position);
        listadorecyclerlugares.get(position).get
        holder.getPosition();
        holder.get
*/




      //  listadorecyclerlugares.get(position).

       // Task task = DataHolder.get(position);

       // final String key_value = MainActivity.keyArray.get(position);



        holder.name.setText(listadorecyclerlugares.get(position).getFuente());
        holder.email.setText(listadorecyclerlugares.get(position).getUrlkml());







        holder.getAdapterPosition() = new Mapaskmladapter()
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, email;
        ImageView profilePic;
        Button btn;

        RecyclerView recyclerView2;

       // recyclerView2 = (RecyclerView)  findViewById(R.id.my_recycler_view);



        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            email = (TextView) itemView.findViewById(R.id.email);
            btn = (Button) itemView.findViewById(R.id.checkDetails);

            recyclerView2 = itemView.findViewById(R.id.my_recycler_view2);

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



    class MyViewHolder2 extends RecyclerView.ViewHolder {

        TextView valor1;
        TextView valor2;
        Button valor3;

        public MyViewHolder2(View itemView) {
            super(itemView);

        clickPosition = new ClickPosition() {
            @Override
            public void getPosition(int position) {
                Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
            }
        };

        valor1 = itemView.findViewById(R.id.valor1);
        valor2 = itemView.findViewById(R.id.valor2);
        valor3 = itemView.findViewById(R.id.valor3);

        }
    }



    @Override
    public int getItemCount() {
        return listadorecyclerlugares.size();
    }



    public void re(){
/*
        DatabaseReference mFirebaseDatabase4;
        DatabaseReference reference2;
        RecyclerView recyclerView2;
        ArrayList<Cartasevacua> list2;
        Listadolugaresadapter adapter2;
        */

        recyclerView2.setLayoutManager(new LinearLayoutManager(context));

        reference2 = FirebaseDatabase.getInstance().getReference("bdrefugy").child("cartas2");

        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2 = new ArrayList<Cartasevacua>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Cartasevacua p = dataSnapshot1.getValue(Cartasevacua.class);
                    list2.add(p);
                }
                adapter2 = new Mapaskmladapter(Mapaskmladapter.this,list2);
                recyclerView2.setAdapter(adapter2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Listadorecyclerlugares.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });


        /*
        DatabaseReference mFirebaseDatabase4;
        DatabaseReference reference;
        final RecyclerView recyclerView2;
        final ArrayList<Cartasevacua>[] list2 = new ArrayList[Cartasevacua];
        final Mapaskmladapter[] adapter = new Mapaskmladapter[1];

        recyclerView2 = (RecyclerView)  findViewById(R.id.my_recycler_view);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        reference = FirebaseDatabase.getInstance().getReference("bdrefugy").child("cartas2");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2[0] = new ArrayList<Cartasevacua>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Cartasevacua p = dataSnapshot1.getValue(Cartasevacua.class);
                    list2[0].add(p);
                }
                adapter[0] = new Listadolugaresadapter(Listadorecyclerlugares.this, list2[0]);
                recyclerView2.setAdapter(adapter[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Listadorecyclerlugares.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

        */
    }


}