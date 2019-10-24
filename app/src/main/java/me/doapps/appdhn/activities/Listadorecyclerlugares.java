package me.doapps.appdhn.activities;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import me.doapps.appdhn.R;
import me.doapps.appdhn.adapters.Listadolugaresadapter;
import me.doapps.appdhn.models.Departamentos;

public class Listadorecyclerlugares extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Departamentos> list;
    Listadolugaresadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupactivity_lugares);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        reference = FirebaseDatabase.getInstance().getReference("bdrefugy").child("cartas4");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<Departamentos>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Departamentos p = dataSnapshot1.getValue(Departamentos.class);
                    String commentKey = dataSnapshot1.getKey();
                    Log.d("LLAVE:", commentKey);
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
}
