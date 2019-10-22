package me.doapps.appdhn.activities;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import me.doapps.appdhn.R;
import me.doapps.appdhn.adapters.Listadolugaresadapter;
import me.doapps.appdhn.models.Cartasevacua;

public class Listadorecyclerlugares extends AppCompatActivity {
    DatabaseReference mFirebaseDatabase4;
    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Cartasevacua> list;
    Listadolugaresadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupactivity_lugares);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        reference = FirebaseDatabase.getInstance().getReference("bdrefugy").child("cartas2");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<Cartasevacua>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Cartasevacua p = dataSnapshot1.getValue(Cartasevacua.class);
                    list.add(p);
                }
                adapter = new Listadolugaresadapter(Listadorecyclerlugares.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Listadorecyclerlugares.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }




/*

    reference = FirebaseDatabase.getInstance().getReference().child("Profiles");
        reference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            list = new ArrayList<Profile>();
            for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
            {
                Profile p = dataSnapshot1.getValue(Profile.class);
                list.add(p);
            }
            adapter = new MyAdapter(MainActivity.this,list);
            recyclerView.setAdapter(adapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Toast.makeText(MainActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
        }
    });




    public ArrayList<Cartasevacua> retreive() {
        FirebaseDatabase.getInstance();
        mFirebaseDatabase4.keepSynced(true);
        mFirebaseDatabase4.orderByChild("orden").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Cartasevacua objetoalertaceniza = dataSnapshot.getValue(Cartasevacua.class);
                objetoalertacenizas.add(objetoalertaceniza);
                alertacenizas.setAdapter(adapter3);

            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Cartasevacua objetoalertaceniza = dataSnapshot.getValue(Cartasevacua.class);
                objetoalertacenizas.add(objetoalertaceniza);
                alertacenizas.setAdapter(adapter3);

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
        mFirebaseDatabase4.keepSynced(true);
        return objetoalertacenizas;
    }
    */


}
