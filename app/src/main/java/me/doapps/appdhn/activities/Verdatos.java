package me.doapps.appdhn.activities;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import me.doapps.appdhn.R;
import me.doapps.appdhn.adapters.Lugarescartasadapter;
import me.doapps.appdhn.models.Departamentos;

public class Verdatos extends AppCompatActivity {



    Button tomardata;
    String Message;
    String valorguardadoenmemoriaa, valorguardadoenmemoriab, valorguardadoenmemoriac, valorguardadoenmemoriad,valorguardadoenmemoriae;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verdatosactivity);


        tomardata = findViewById(R.id.verdata);


        tomardata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fileInputStream =  openFileInput("vibrar_file");
                    InputStreamReader inputStreamReader =  new InputStreamReader(fileInputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuffer stringBuffer =  new StringBuffer();
                    try {
                        while ((Message = bufferedReader.readLine())!=null)
                        {
                            stringBuffer.append(Message);
                        }
                        valorguardadoenmemoriaa = stringBuffer.toString();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                Log.d("VALOR_FILE",  valorguardadoenmemoriaa);


            }
        });



    }
}