package me.doapps.appdhn.expandible;

import android.content.Context;
import android.support.v4.util.Pair;
import android.util.Log;

import com.h6ah4i.android.widget.advrecyclerview.swipeable.RecyclerViewSwipeManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import me.doapps.appdhn.models.FrequentQuestion;
import me.doapps.appdhn.utils.ValueHelper;

/**
 * Created by William_ST on 10/08/16.
 */
public class FrequentQuestionsExpandableDataProvider extends AbstractFrequentQuestionDataProvider {
    private List<Pair<GroupData, List<ChildData>>> mData;
    private final String TAG = FrequentQuestionsExpandableDataProvider.class.getSimpleName();

    public FrequentQuestionsExpandableDataProvider(Context context) {
        try {
            mData = new LinkedList<>();
            String menuTemp = "DoApps";
            ConcreteGroupData group = null;
            List<ChildData> children = new ArrayList<>();

            if (ValueHelper.collectionFrequentQuestion.size() > 0) {
                menuTemp = "DoApps";
                for (int i = 0; i < ValueHelper.collectionFrequentQuestion.size(); i++) {
                    if (!menuTemp.equals(ValueHelper.collectionFrequentQuestion.get(i).getMenu())) {
                        final long groupId = i;
                        final int groupSwipeReaction = RecyclerViewSwipeManager.REACTION_CAN_SWIPE_LEFT | RecyclerViewSwipeManager.REACTION_CAN_SWIPE_RIGHT;
                        group = new ConcreteGroupData(groupId, ValueHelper.collectionFrequentQuestion.get(i), groupSwipeReaction);
                        children = new ArrayList<>();
                    }

                    menuTemp = ValueHelper.collectionFrequentQuestion.get(i).getMenu();

                    final long childId = group.generateNewChildId();
                    final int childSwipeReaction = RecyclerViewSwipeManager.REACTION_CAN_SWIPE_LEFT | RecyclerViewSwipeManager.REACTION_CAN_SWIPE_RIGHT;
                    children.add(new ConcreteChildData(childId, ValueHelper.collectionFrequentQuestion.get(i), childSwipeReaction));

                    if(i+1 == ValueHelper.collectionFrequentQuestion.size()){
//                        Log.e(TAG, "ÚLTIMO");
                        mData.add(new Pair<GroupData, List<ChildData>>(group, children));
                    }else {
                        if (!menuTemp.equals(ValueHelper.collectionFrequentQuestion.get(i + 1).getMenu())) {
                            mData.add(new Pair<GroupData, List<ChildData>>(group, children));
                        }
                    }
//                    Log.e(TAG, "add: " + i);
//                    menuTemp = chartsList.get(i).getMenu();
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "FrequentQuestionsExpandableDataProvider " + e.toString());
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
        private final FrequentQuestion frequentQuestion;
        private final int mSwipeReaction;
        private boolean mPinnedToSwipeLeft;
        private long mNextChildId;

        ConcreteGroupData(long id, FrequentQuestion text, int swipeReaction) {
            mId = id;
            frequentQuestion = text;
            mSwipeReaction = swipeReaction;
            mNextChildId = 0;
        }

        @Override
        public long getGroupId() {
            return mId;
        }

        @Override
        public FrequentQuestion getFrequentQuestion() {
            return frequentQuestion;
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
        private final FrequentQuestion mText;
        private final int mSwipeReaction;
        private boolean mPinnedToSwipeLeft;

        ConcreteChildData(long id, FrequentQuestion text, int swipeReaction) {
            mId = id;
            mText = text;
            mSwipeReaction = swipeReaction;
        }

        @Override
        public long getChildId() {
            return mId;
        }

        @Override
        public FrequentQuestion getFrequentQuestion() {
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
