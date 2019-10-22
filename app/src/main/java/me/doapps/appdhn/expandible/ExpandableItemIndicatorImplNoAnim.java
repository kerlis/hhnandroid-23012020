package me.doapps.appdhn.expandible;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import me.doapps.appdhn.R;

/**
 * Created by William_ST on 01/10/15.
 */
class ExpandableItemIndicatorImplNoAnim extends ExpandableItemIndicator.Impl {
    private ImageView mImageView;

    @Override
    public void onInit(Context context, AttributeSet attrs, int defStyleAttr, ExpandableItemIndicator thiz) {
        View v = LayoutInflater.from(context).inflate(R.layout.widget_expandable_item_indicator_no_anim, thiz, true);
        mImageView = (ImageView) v.findViewById(R.id.image_view);
    }

    @Override
    public void setExpandedState(boolean isExpanded, boolean animate) {
        int resId = (isExpanded) ? R.mipmap.im_collapse_icon : R.mipmap.im_expand_icon;
        mImageView.setImageResource(resId);
    }
}

