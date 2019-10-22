package me.doapps.appdhn.expandible;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by William_ST on 10/08/16.
 */
public class FrequentsQuestionExpandableDataProviderFragment extends Fragment {
    public static FrequentQuestionsExpandableDataProvider mDataProvider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);  // keep the mDataProvider instance
        mDataProvider = new FrequentQuestionsExpandableDataProvider(getActivity());
    }

    public AbstractFrequentQuestionDataProvider getDataProvider() {
        return mDataProvider;
    }
}
