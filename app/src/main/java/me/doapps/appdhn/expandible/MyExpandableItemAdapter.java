package me.doapps.appdhn.expandible;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

import java.io.File;

import me.doapps.appdhn.R;
import me.doapps.appdhn.activities.ChartsActivity;
import me.doapps.appdhn.activities.MapsActivity;
import me.doapps.appdhn.models.Charts;
import me.doapps.appdhn.utils.FileUtil;
import me.doapps.appdhn.utils.PermissionUtil;
import me.doapps.appdhn.utils.UrlHelper;

/**
 * Created by William_ST on 01/10/15.
 */
public class MyExpandableItemAdapter extends AbstractExpandableItemAdapter<MyExpandableItemAdapter.MyGroupViewHolder, MyExpandableItemAdapter.MyChildViewHolder> {
    private static final String TAG = MyExpandableItemAdapter.class.getSimpleName();
    private Context context;
    private AbstractExpandableDataProvider mProvider;
    private FileUtil fileUtil;

    public static abstract class MyBaseViewHolder extends AbstractExpandableItemViewHolder {
        public FrameLayout mContainer;
        public TextView mTextView;
        public Button mButton;
        public ImageView imageViewDepartment;

        public MyBaseViewHolder(View v) {
            super(v);
            mContainer = (FrameLayout) v.findViewById(R.id.container);
            mTextView = (TextView) v.findViewById(android.R.id.text1);
            mButton = (Button) v.findViewById(R.id.button_download);
            imageViewDepartment = (ImageView) v.findViewById(R.id.department_icon);
        }
    }

    public static class MyGroupViewHolder extends MyBaseViewHolder {
        public ExpandableItemIndicator mIndicator;

        public MyGroupViewHolder(View v) {
            super(v);
            mIndicator = (ExpandableItemIndicator) v.findViewById(R.id.indicator);
        }
    }

    public static class MyChildViewHolder extends MyBaseViewHolder {
        public MyChildViewHolder(View v) {
            super(v);
        }
    }

    public MyExpandableItemAdapter(AbstractExpandableDataProvider dataProvider, Context context) {
        mProvider = dataProvider;
        this.context = context;
        fileUtil = new FileUtil(context);
        // ExpandableItemAdapter requires stable ID, and also
        // have to implement the getGroupItemId()/getChildItemId() methods appropriately.
        setHasStableIds(true);
    }

    @Override
    public int getGroupCount() {
        return mProvider.getGroupCount();
    }

    @Override
    public int getChildCount(int groupPosition) {
        return mProvider.getChildCount(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return mProvider.getGroupItem(groupPosition).getGroupId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return mProvider.getChildItem(groupPosition, childPosition).getChildId();
    }

    @Override
    public int getGroupItemViewType(int groupPosition) {
        return 0;
    }

    @Override
    public int getChildItemViewType(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public MyGroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View v = inflater.inflate(R.layout.list_group_item, parent, false);
        return new MyGroupViewHolder(v);
    }

    @Override
    public MyChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View v = inflater.inflate(R.layout.list_item, parent, false);
        return new MyChildViewHolder(v);
    }

    @Override
    public void onBindGroupViewHolder(MyGroupViewHolder holder, int groupPosition, int viewType) {
        // child item
        final AbstractExpandableDataProvider.BaseData item = mProvider.getGroupItem(groupPosition);

        // set text
        holder.mTextView.setText(Html.fromHtml(item.getFrequentQuestion().getMenu()));

        // mark as clickable
        holder.itemView.setClickable(true);

        holder.imageViewDepartment.setImageResource(mProvider.getGroupItem(groupPosition).getFrequentQuestion().getIcon());

        // set background resource (target view ID: container)
        final int expandState = holder.getExpandStateFlags();

        if ((expandState & RecyclerViewExpandableItemManager.STATE_FLAG_IS_UPDATED) != 0) {
            int bgResId;
            boolean isExpanded;

            if ((expandState & RecyclerViewExpandableItemManager.STATE_FLAG_IS_EXPANDED) != 0) {
                bgResId = R.drawable.bg_group_item_expanded_state;
                isExpanded = true;
            } else {
                bgResId = R.drawable.bg_group_item_normal_state;
                isExpanded = false;
            }

            holder.mContainer.setBackgroundResource(bgResId);
            holder.mIndicator.setExpandedState(isExpanded, true);
        }
    }

    @Override
    public void onBindChildViewHolder(MyChildViewHolder holder, final int groupPosition, final int childPosition, int viewType) {
        // group item
        final AbstractExpandableDataProvider.ChildData item = mProvider.getChildItem(groupPosition, childPosition);

        // set text
        holder.mTextView.setText(Html.fromHtml(item.getFrequentQuestion().getSubmenu()));
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Charts charts = mProvider.getChildItem(groupPosition, childPosition).getFrequentQuestion();
                Log.e(TAG, "URL: " + charts.getUrl());
                Download(context, charts.getSubmenu(), UrlHelper.downloadPreURL + charts.getUrl());
            }
        });

        int bgResId;
        bgResId = R.drawable.bg_item_normal_state;
        holder.mContainer.setBackgroundResource(bgResId);
    }

    public static String tempName = "", tempUrl= "";

    public static void Download(final Context context,  String name, String url) {
        tempName = name;
        tempUrl = url;
        int result = PermissionUtil.requestPermissionWrite(((ChartsActivity) context));
        if (result == PermissionUtil.PERMISSION_GRANTED) {
            Toast.makeText(context, "Descarga iniciada", Toast.LENGTH_SHORT).show();
            File direct = new File(Environment.getExternalStorageDirectory()
                    + Environment.DIRECTORY_DOWNLOADS + "/Cartas de inundación");
            if (!direct.exists()) {
                direct.mkdirs();
            }

            final DownloadManager dm = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            request.setTitle(name);
            request.setDescription("Carta de inundación");
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS + "/Cartas de inundación", name + url.substring(url.lastIndexOf("."), url.length()));
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            final long enqueue = dm.enqueue(request);

            BroadcastReceiver receiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String action = intent.getAction();
                    if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                        DownloadManager.Query query = new DownloadManager.Query();
                        query.setFilterById(enqueue);
                        Cursor c = dm.query(query);
                        if (c.moveToFirst()) {
                            int columnIndex = c.getColumnIndex(DownloadManager.COLUMN_STATUS);
                            if (DownloadManager.STATUS_SUCCESSFUL == c.getInt(columnIndex)) {
//                            Toast.makeText(context, "Descarga Completa!!!", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            };

            context.registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        } else if(result == PermissionUtil.PERMISSION_ALLWAYS_DENIED){
            PermissionUtil.showSnackbarPermissionPosition(context, ((ChartsActivity)context).linearLayoutContent, context.getString(R.string.permission_message_enable_write), new PermissionUtil.SnackbarOnClickListener() {
                @Override
                public void pressed() {
                    PermissionUtil.showDialogPermissionWrite(((ChartsActivity)context));
//                    PermissionUtil.openPanelConfigurationApp(((ChartsActivity)context), ((ChartsActivity)context).getPackageName());
                }
            });
        }
    }

    @Override
    public boolean onCheckCanExpandOrCollapseGroup(MyGroupViewHolder holder, int groupPosition, int x, int y, boolean expand) {
        if (mProvider.getGroupItem(groupPosition).isPinnedToSwipeLeft()) {
            return false;
        }

        if (!(holder.itemView.isEnabled() && holder.itemView.isClickable())) {
            return false;
        }

        return true;
    }

}
