package me.doapps.appdhn.fragments;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import me.doapps.appdhn.R;

public class Fragmentwithdata extends Fragment {
    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;

    public static Fragmentwithdata newInstance(String title, int resImage) {
        Fragmentwithdata fragment = new Fragmentwithdata();
        Bundle args = new Bundle();
        args.putInt("image", resImage);
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        return inflater.inflate(R.layout.fragmentwithdata, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();


        Bundle args = getArguments();
        if (args != null) {
            updateArticleView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            updateArticleView(mCurrentPosition);
        }
    }

    public void recibirdato(String dato) {
        TextView article = (TextView) getActivity().findViewById(R.id.receptor);
        article.setText(dato);

        Log.d("DATORECIBIDO", dato);
        Toast.makeText(getContext(),"Resultante DATOS:", Toast.LENGTH_LONG);

        SharedPreferences sharedPref1 = (SharedPreferences) this.getActivity().getSharedPreferences("MySharedPreference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref1.edit();
        editor.putString("DATO","MI_DATO XXX");
        // editor.apply();
        editor.commit();
    }



    public void updateArticleView(int position) {
        //TextView article = (TextView) getActivity().findViewById(R.id.article);
        //article.setText(Ipsum.Articles[position]);
        //mCurrentPosition = position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }
}



