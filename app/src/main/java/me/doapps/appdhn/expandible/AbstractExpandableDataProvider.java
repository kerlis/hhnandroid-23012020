package me.doapps.appdhn.expandible;

import me.doapps.appdhn.models.Charts;

/**
 * Created by William_ST on 01/10/15.
 */
public abstract class AbstractExpandableDataProvider {
    public static abstract class BaseData {

        public abstract Charts getFrequentQuestion();

        public abstract boolean isPinnedToSwipeLeft();
    }

    public static abstract class GroupData extends BaseData {
        public abstract long getGroupId();
    }

    public static abstract class ChildData extends BaseData {
        public abstract long getChildId();
    }

    public abstract int getGroupCount();
    public abstract int getChildCount(int groupPosition);

    public abstract GroupData getGroupItem(int groupPosition);
    public abstract ChildData getChildItem(int groupPosition, int childPosition);

}
