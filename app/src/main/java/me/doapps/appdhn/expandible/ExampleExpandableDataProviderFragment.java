package me.doapps.appdhn.expandible;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by William_ST on 01/10/15.
 */
public class ExampleExpandableDataProviderFragment extends Fragment {
    public static ExampleExpandableDataProvider mDataProvider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);  // keep the mDataProvider instance
        mDataProvider = new ExampleExpandableDataProvider(getActivity());
    }

    public AbstractExpandableDataProvider getDataProvider() {
        return mDataProvider;
    }
}
