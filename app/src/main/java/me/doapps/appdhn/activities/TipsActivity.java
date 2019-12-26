package me.doapps.appdhn.activities;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;

import me.doapps.appdhn.R;


public class TipsActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private Toolbar mToolbar;
    private SliderLayout mDemoSlider;
    private final String TAG = TipsActivity.class.getSimpleName();
    private LinearLayout linearLayoutSea;
    private float widthTotal;
    private float heightTotal;
    private TextView textTip;
    private ImageView imageViewT1, imageViewT2, imageViewT3, imageViewT4, imageViewT5;
    private final String[] title = new String[]{
            "Tips", "Tips", "Tips", "Tips", "Tips", "Mochila Salvavidas"
    };
    private final double[] porcent = new double[]{
            0.58, 0.44, 0.4, 0.3, 0.2, 0.12
    };
    private int[] icons;
    private String[] tips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

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

        instanceScreen();
        instanceToolbar();

        tips = new String[]{
                getString(R.string.first_slider), getString(R.string.second_slider), getString(R.string.third_slider),
                getString(R.string.fourth_slider), getString(R.string.fifth_slider), getString(R.string.sixth_slider)
        };

        icons = new int[]{
              R.mipmap.tip_01, R.mipmap.tip_02, R.mipmap.tip_03, R.mipmap.tip_04, R.mipmap.tip_05, R.mipmap.tip_05
        };

        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        linearLayoutSea = (LinearLayout) findViewById(R.id.linear_layout_sea);
        textTip = (TextView) findViewById(R.id.text_tip);
        imageViewT1 = (ImageView) findViewById(R.id.image_view_tip_1);
        imageViewT2 = (ImageView) findViewById(R.id.image_view_tip_2);
        imageViewT3 = (ImageView) findViewById(R.id.image_view_tip_3);
        imageViewT4 = (ImageView) findViewById(R.id.image_view_tip_4);
        imageViewT5 = (ImageView) findViewById(R.id.image_view_tip_5);

        textTip.setText(getString(R.string.first_slider));

        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("TipOne", R.mipmap.background_transparent);
        file_maps.put("TipTwo", R.mipmap.background_transparent);
        file_maps.put("TipThree", R.mipmap.background_transparent);
        file_maps.put("TipFour", R.mipmap.background_transparent);
        file_maps.put("TipFive", R.mipmap.background_transparent);
        file_maps.put("TipSix", R.mipmap.background_transparent);

        for (String name : file_maps.keySet()) {
            DefaultSliderView defaultSliderView = new DefaultSliderView(TipsActivity.this);
            defaultSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            defaultSliderView.bundle(new Bundle());
            defaultSliderView.getBundle().putString("extra", name);

            mDemoSlider.addSlider(defaultSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.stopAutoCycle();
        mDemoSlider.addOnPageChangeListener(this);

        moveSea(imageViewT1, (int)(heightTotal * 0.2));
        moveSea(imageViewT2, (int)(heightTotal * 0.13));
        moveSea(imageViewT3, (int)(heightTotal * 0.22));
        moveSea(imageViewT4, (int)(heightTotal * 0.15));
        moveSea(imageViewT5, (int)(heightTotal * 0.18));
//        moveSea(imageViewT5, (int)(heightTotal * 0.6));


        moveSea(linearLayoutSea, (int) (heightTotal * porcent[0]));
    }

    public void instanceScreen() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        heightTotal = displaymetrics.heightPixels;
        widthTotal = displaymetrics.widthPixels;
    }

    public void instanceToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getString(R.string.title_tips));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_tips, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:break;
            /*
            case R.id.action_share:
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
                    String sAux = "\n"+getString(R.string.s_text)+":\n\n";
                    sAux = sAux + getString(R.string.s_url) + getPackageName();
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, getString(R.string.s_share)));
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
                break;
                */
        }

        return super.onOptionsItemSelected(item);
    }

    public void moveSea(View view, int px) {
        try {
            ObjectAnimator move = null;
            move = ObjectAnimator.ofFloat(view, "translationY", px);
            move.setDuration(100);
            move.start();
        } catch (Exception e) {
            Log.e(TAG, "animationYFloatingActionButton " + e.toString());
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.e(TAG, "position: " + position + " - positionOffset: " + positionOffset + " - positionOffsetPixels: " + positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        getSupportActionBar().setTitle(title[position]);
        moveSea(linearLayoutSea, (int) (heightTotal * porcent[position]));
        textTip.setText(tips[position]);
        switch (position){
            case 0:
                imageViewT5.setVisibility(View.GONE);
                imageViewT2.setVisibility(View.GONE);
                imageViewT1.setVisibility(View.VISIBLE);
                break;
            case 1:
                imageViewT1.setVisibility(View.GONE);
                imageViewT2.setVisibility(View.VISIBLE);
                imageViewT3.setVisibility(View.GONE);
                break;
            case 2:
                imageViewT2.setVisibility(View.GONE);
                imageViewT3.setVisibility(View.VISIBLE);
                imageViewT4.setVisibility(View.GONE);
                break;
            case 3:
                imageViewT3.setVisibility(View.GONE);
                imageViewT4.setVisibility(View.VISIBLE);
                imageViewT5.setVisibility(View.GONE);
                break;
            case 4:
                imageViewT4.setVisibility(View.GONE);
                imageViewT5.setVisibility(View.VISIBLE);
                imageViewT1.setVisibility(View.GONE);
                break;
            case 5:
                imageViewT4.setVisibility(View.GONE);
                imageViewT5.setVisibility(View.VISIBLE);
                imageViewT1.setVisibility(View.GONE);
                break;
        }
        Log.e(TAG, "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onSliderClick(BaseSliderView baseSliderView) {
//        Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }
}
