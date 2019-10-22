package me.doapps.appdhn.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.j256.ormlite.stmt.QueryBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

import me.doapps.appdhn.R;
import me.doapps.appdhn.activities.BulletinNoticesActivity;
import me.doapps.appdhn.adapters.BulletinAdapter;
import me.doapps.appdhn.models.Bulletin;
import me.doapps.appdhn.utils.UrlHelper;
import me.doapps.appdhn.utils.ValueHelper;

/**
 * Created by William_ST on 01/10/15.
 */
public class BulletinFragment extends Fragment {

    private final String TAG = BulletinFragment.class.getSimpleName();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layout_manager;
    BulletinAdapter adapter;
    List<Bulletin> bulletinList;
    Handler handler;
    QueryBuilder<Bulletin, Integer> bulletinQb;

    public static BulletinFragment newInstance(int index) {
        BulletinFragment bulletinFragment = new BulletinFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        bulletinFragment.setArguments(args);
        return bulletinFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bulletin, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.bulletinList);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            handler = new Handler();
            layout_manager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layout_manager);
            bulletinList = new ArrayList<>();

            bulletinQb = ((BulletinNoticesActivity) getActivity()).getDatabaseHelper().getBulletinDao().queryBuilder();

            switch (getArguments().getInt("index")) {
                case 0:
                    bulletinQb.where().eq("type", ValueHelper.BULLETIN_TIDES);
                    break;
                case 1:
                    bulletinQb.where().eq("type", ValueHelper.BULLETIN_SEISMIC);
                    break;
                case 2:
                    bulletinQb.where().eq("type", ValueHelper.BULLETIN_WINDS);
                    break;
                default:
                    break;
            }

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (Bulletin bulletin : bulletinQb.query()) {
                            bulletinList.add(bulletin);
                /*
                Log.e(TAG, "=== === === === === ===");
                Log.e(TAG, "TITLE: " + bulletin.getTitle());
                Log.e(TAG, "DESCRIPTION: "+bulletin.getDescription());
                Log.e(TAG, "TIPE: "+bulletin.getType());
                Log.e(TAG, "URL: "+bulletin.getUrl());
                Log.e(TAG, "=== === === === === ===");
                */
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                adapter = new BulletinAdapter(getActivity(), bulletinList);
                                recyclerView.setAdapter(adapter);
                            }
                        });

                    } catch (Exception e) {
                        Log.e(TAG, "thread " + e.toString());
                    }
                }
            }).start();

        } catch (Exception e) {
            Log.e(TAG, "onActivityCreated " + e.toString());
        }
    }
}
