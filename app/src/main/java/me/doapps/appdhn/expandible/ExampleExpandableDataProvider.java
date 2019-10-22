package me.doapps.appdhn.expandible;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.util.Pair;
import android.util.Log;

import com.h6ah4i.android.widget.advrecyclerview.swipeable.RecyclerViewSwipeManager;
import com.j256.ormlite.stmt.QueryBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import me.doapps.appdhn.databases.DatabaseHelper;
import me.doapps.appdhn.models.Charts;
import me.doapps.appdhn.utils.ValueHelper;

/**
 * Created by William_ST on 01/10/15.
 */
public class ExampleExpandableDataProvider extends AbstractExpandableDataProvider {
    private List<Pair<GroupData, List<ChildData>>> mData;
    private final String TAG = ExampleExpandableDataProvider.class.getSimpleName();

    public ExampleExpandableDataProvider(Context context) {
        try {
            mData = new LinkedList<>();
            String menuTemp = "DoApps";
            ConcreteGroupData group = null;
            List<ChildData> children = new ArrayList<>();

            if (ValueHelper.collectionChart.size() > 0) {
                menuTemp = "DoApps";
                for (int i = 0; i < ValueHelper.collectionChart.size(); i++) {
                    if (!menuTemp.equals(ValueHelper.collectionChart.get(i).getMenu())) {
                        final long groupId = i;
                        final int groupSwipeReaction = RecyclerViewSwipeManager.REACTION_CAN_SWIPE_LEFT | RecyclerViewSwipeManager.REACTION_CAN_SWIPE_RIGHT;
                        group = new ConcreteGroupData(groupId, ValueHelper.collectionChart.get(i), groupSwipeReaction);
                        children = new ArrayList<>();
                    }

                    menuTemp = ValueHelper.collectionChart.get(i).getMenu();

                    final long childId = group.generateNewChildId();
                    final int childSwipeReaction = RecyclerViewSwipeManager.REACTION_CAN_SWIPE_LEFT | RecyclerViewSwipeManager.REACTION_CAN_SWIPE_RIGHT;
                    children.add(new ConcreteChildData(childId, ValueHelper.collectionChart.get(i), childSwipeReaction));

                    if (i + 1 == ValueHelper.collectionChart.size()) {
//                        Log.e(TAG, "ÚLTIMO");
                        mData.add(new Pair<GroupData, List<ChildData>>(group, children));
                    } else {
                        if (!menuTemp.equals(ValueHelper.collectionChart.get(i + 1).getMenu())) {
                            mData.add(new Pair<GroupData, List<ChildData>>(group, children));
                        }
                    }
//                    Log.e(TAG, "add: " + i);
//                    menuTemp = chartsList.get(i).getMenu();
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "ExampleExpandableDataProvider " + e.toString());
        }
    }


    @Override
    public int getGroupCount() {
        return mData.size();
    }

    @Override
    public int getChildCount(int groupPosition) {
        return mData.get(groupPosition).second.size();
    }

    @Override
    public GroupData getGroupItem(int groupPosition) {
        if (groupPosition < 0 || groupPosition >= getGroupCount()) {
            throw new IndexOutOfBoundsException("groupPosition = " + groupPosition);
        }

        return mData.get(groupPosition).first;
    }

    @Override
    public ChildData getChildItem(int groupPosition, int childPosition) {
        if (groupPosition < 0 || groupPosition >= getGroupCount()) {
            throw new IndexOutOfBoundsException("groupPosition = " + groupPosition);
        }

        final List<ChildData> children = mData.get(groupPosition).second;

        if (childPosition < 0 || childPosition >= children.size()) {
            throw new IndexOutOfBoundsException("childPosition = " + childPosition);
        }

        return children.get(childPosition);
    }

    public static final class ConcreteGroupData extends GroupData {

        private final long mId;
        private final Charts charts;
        private final int mSwipeReaction;
        private boolean mPinnedToSwipeLeft;
        private long mNextChildId;

        ConcreteGroupData(long id, Charts text, int swipeReaction) {
            mId = id;
            charts = text;
            mSwipeReaction = swipeReaction;
            mNextChildId = 0;
        }

        @Override
        public long getGroupId() {
            return mId;
        }

        @Override
        public Charts getFrequentQuestion() {
            return charts;
        }

        @Override
        public boolean isPinnedToSwipeLeft() {
            return mPinnedToSwipeLeft;
        }

        public long generateNewChildId() {
            final long id = mNextChildId;
            mNextChildId += 1;
            return id;
        }
    }

    public static final class ConcreteChildData extends ChildData {

        private long mId;
        private final Charts mText;
        private final int mSwipeReaction;
        private boolean mPinnedToSwipeLeft;

        ConcreteChildData(long id, Charts text, int swipeReaction) {
            mId = id;
            mText = text;
            mSwipeReaction = swipeReaction;
        }

        @Override
        public long getChildId() {
            return mId;
        }

        @Override
        public Charts getFrequentQuestion() {
            return mText;
        }

        @Override
        public boolean isPinnedToSwipeLeft() {
            return mPinnedToSwipeLeft;
        }

        public void setChildId(long id) {
            this.mId = id;
        }
    }
}
