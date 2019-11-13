package me.doapps.appdhn.activities;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import me.doapps.appdhn.R;
import me.doapps.appdhn.fragments.Mapa1;
import me.doapps.appdhn.fragments.Mapa2;

public class Comunicarfragmentos extends FragmentActivity implements Mapa1.OnFragmentInteractionListener, Mapa2.OnFragmentInteractionListener {

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    Button cambiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comunicarfragmentos);

        cambiar =  findViewById(R.id.cambiar);

        //Mapa1 fragment = (Mapa1) getSupportFragmentManager().findFragmentById(R.id.mapa1);

        Mapa1 fragment1 = new Mapa1();
        fragmentTransaction.add(R.id.mapa1, fragment1);
        fragmentTransaction.commit();

        cambiar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                remplazar_fragmento();
            }
        });
    }


    public void remplazar_fragmento(){

        Fragment fragmento2 = new Mapa2();

        fragmentTransaction.replace(R.id.mapa2, fragmento2);

        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
