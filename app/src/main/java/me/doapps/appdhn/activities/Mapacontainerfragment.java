package me.doapps.appdhn.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import me.doapps.appdhn.R;
import me.doapps.appdhn.fragments.Fragmentbegin;
import me.doapps.appdhn.fragments.Fragmentwithdata;

public class Mapacontainerfragment extends AppCompatActivity implements Comunicationfragment.OnHeadlineSelectedListener  {

    FragmentPagerAdapter adapterViewPager;
    RelativeLayout celda1;
    RelativeLayout celda2;
    TextView reporte_hcs, detalles_hcs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapacontainerfragment);

        Snackbar.make(findViewById(android.R.id.content),"Mantén actualizada la aplicación", Snackbar.LENGTH_LONG)
                .setAction("Play store", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW)
                                .setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
                        try {
                            startActivity(new Intent(intent)
                                    .setPackage("com.android.vending"));
                        } catch (android.content.ActivityNotFoundException exception) {
                            startActivity(intent);
                        }
                    }
                }).show();


        reporte_hcs =  findViewById(R.id.opcion1);
        detalles_hcs =  findViewById(R.id.opcion2);

        celda1 = (RelativeLayout) findViewById(R.id.cell1);
        celda2 = (RelativeLayout) findViewById(R.id.cell2);

        final ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new PagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        vpPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            public void onPageSelected(int pageNumber) {
                if(pageNumber == 0){
                    reporte_hcs.setBackgroundColor(getResources().getColor(R.color.celeste));
                    detalles_hcs.setBackgroundColor(getResources().getColor(R.color.azul));
                }
                else if(pageNumber == 1) {
                    detalles_hcs.setBackgroundColor(getResources().getColor(R.color.azul));
                    reporte_hcs.setBackgroundColor(getResources().getColor(R.color.celeste));
                }
                else{
                    detalles_hcs.setBackgroundColor(getResources().getColor(R.color.celeste));
                    reporte_hcs.setBackgroundColor(getResources().getColor(R.color.azul));
                }
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub
            }
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub
            }
        });


        if(vpPager.getCurrentItem() == 0) {
            reporte_hcs.setBackgroundColor(getResources().getColor(R.color.celeste));
            detalles_hcs.setBackgroundColor(getResources().getColor(R.color.azul));
        }
        else if(vpPager.getCurrentItem() == 1) {
            detalles_hcs.setBackgroundColor(getResources().getColor(R.color.azul));
            reporte_hcs.setBackgroundColor(getResources().getColor(R.color.celeste));
        }
        else{
            detalles_hcs.setBackgroundColor(getResources().getColor(R.color.celeste));
            reporte_hcs.setBackgroundColor(getResources().getColor(R.color.azul));
        }

        celda2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                vpPager.setCurrentItem(1, true);
                reporte_hcs.setBackgroundColor(getResources().getColor(R.color.azul));
                detalles_hcs.setBackgroundColor(getResources().getColor(R.color.celeste));
                //   imagen3.setVisibility(View.GONE);

                Fragmentbegin articleFrag2 = (Fragmentbegin) getSupportFragmentManager().findFragmentById(R.id.fragmenttres);
                if (articleFrag2 != null) {
                    articleFrag2.recibirdato("DATOZ");
                } else {

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    Bundle bundle = new Bundle();
                    String myMessage = "Stackoverflow is cool!";
                    bundle.putString("message", myMessage );
                    Fragmentbegin fragInfo = new Fragmentbegin();
                    fragInfo.setArguments(bundle);
                    transaction.replace(R.id.fragmenttres, fragInfo);
                   // transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,  R.anim.enter_from_left, R.anim.exit_to_right);
                    transaction.commit();
                }

                Log.d("DATOENVIADO", "DATOENVIADO");

            }
        });

        celda1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vpPager.setCurrentItem(0, true);
                reporte_hcs.setBackgroundColor(getResources().getColor(R.color.celeste));
                detalles_hcs.setBackgroundColor(getResources().getColor(R.color.azul));


                Fragmentbegin articleFrag2 = (Fragmentbegin) getSupportFragmentManager().findFragmentById(R.id.fragmenttres);
                if (articleFrag2 != null) {
                    articleFrag2.recibirdato("DATOZ");
                } else {

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    Bundle bundle = new Bundle();
                    String myMessage = "Stackoverflow is cool!";
                    bundle.putString("message", myMessage );
                    Fragmentwithdata fragInfo = new Fragmentwithdata();
                    fragInfo.setArguments(bundle);
                    transaction.replace(R.id.fragment_container, fragInfo);
                  //  transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,  R.anim.enter_from_left, R.anim.exit_to_right);
                    transaction.commit();
                }
            }
        });











    }

    @Override
    public void onArticleSelected(int position) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    public   class PagerAdapter extends FragmentPagerAdapter {
        private   int NUM_ITEMS = 2;

        public PagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public int getCount() {

            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 1:
                    return Fragmentbegin.newInstance("",  R.drawable.ir_icon);
                case 0:
                    return Fragmentwithdata.newInstance("",  R.drawable.ir_icon);
                default:return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Tab " + position;
        }
    }



}
