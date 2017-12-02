package com.warkiz.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * created by ZhuangGuangquan on 2017/9/9
 */


class BuilderParams
{

    Context mContext;
    //seekBar
    int mSeekBarType = 0;
    float mMax = 100;
    float mMin = 0;
    float mProgress = 0;
    boolean mClearPadding = false;
    boolean mIsFloatProgress = false;
    //indicator
    int mIndicatorType = 0;
    boolean mShowIndicator = true;
    int mIndicatorColor = Color.parseColor("#FF4081");
    int mIndicatorTextColor = Color.parseColor("#FFFFFF");
    int mIndicatorTextSize;
    View mIndicatorCustomView = null;
    View mIndicatorCustomTopContentView = null;
    //track
    int mBackgroundTrackSize;
    int mProgressTrackSize;
    int mBackgroundTrackColor = Color.parseColor("#D7D7D7");
    int mProgressTrackColor = Color.parseColor("#FF4081");
    boolean mTrackRoundedCorners = true;
    //tick
    int mTickNum = 5;
    int mTickType = 1;
    int mTickSize;
    int mTickColor = Color.parseColor("#FF4081");
    boolean mTickHideBothEnds = false;
    boolean mTickOnThumbLeftHide = false;
    Drawable mTickDrawable = null;
    //text
    int mTextSize;
    int mTextColor = Color.parseColor("#FF4081");
    String mLeftEndText = null;
    String mRightEndText = null;
    CharSequence[] mTextArray = null;
    //thumb
    int mThumbColor = Color.parseColor("#FF4081");
    int mThumbSize;
    Drawable mThumbDrawable = null;
    boolean mThumbProgressStay = false;

    BuilderParams(Context context)
    {
        this.mContext = context;
        this.mIndicatorTextSize = IndicatorUtils.sp2px(mContext, 13);
        this.mBackgroundTrackSize = IndicatorUtils.dp2px(mContext, 2);
        this.mProgressTrackSize = IndicatorUtils.dp2px(mContext, 2);
        this.mTickSize = IndicatorUtils.dp2px(mContext, 8);
        this.mTextSize = IndicatorUtils.sp2px(mContext, 13);
        this.mThumbSize = IndicatorUtils.dp2px(mContext, 14);
    }
}
