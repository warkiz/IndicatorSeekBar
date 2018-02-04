package com.warkiz.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

/**
 * created by ZhuangGuangquan on 2017/9/9
 * Version : 2.0
 * Date: 2017/12/10
 * New Feature: indicator stay always.
 */


class BuilderParams {

    Context mContext;
    //seekBar
    int mSeekBarType = 0;
    float mMax = 100;
    float mMin = 0;
    float mProgress = 0;
    boolean mClearPadding = false;
    boolean mIsFloatProgress = false;
    boolean mForbidUserSeek = false;
    boolean mTouchToSeek = true;
    //indicator
    int mIndicatorType = 0;
    boolean mShowIndicator = true;
    boolean mIndicatorStay = false;
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
    Typeface mTextTypeface = Typeface.DEFAULT;
    //thumb
    int mThumbColor = Color.parseColor("#FF4081");
    int mThumbSize;
    Drawable mThumbDrawable = null;
    boolean mThumbProgressStay = false;

    BuilderParams(Context context) {
        this.mContext = context;
        this.mIndicatorTextSize = IndicatorUtils.sp2px(mContext, 13);
        this.mBackgroundTrackSize = IndicatorUtils.dp2px(mContext, 2);
        this.mProgressTrackSize = IndicatorUtils.dp2px(mContext, 2);
        this.mTickSize = IndicatorUtils.dp2px(mContext, 10);
        this.mTextSize = IndicatorUtils.sp2px(mContext, 13);
        this.mThumbSize = IndicatorUtils.dp2px(mContext, 14);
    }

    BuilderParams copy(BuilderParams p) {
        this.mContext = p.mContext;
        //seekBar
        this.mSeekBarType = p.mSeekBarType;
        this.mMax = p.mMax;
        this.mMin = p.mMin;
        this.mProgress = p.mProgress;
        this.mClearPadding = p.mClearPadding;
        this.mIsFloatProgress = p.mIsFloatProgress;
        this.mForbidUserSeek = p.mForbidUserSeek;
        this.mTouchToSeek = p.mTouchToSeek;
        //indicator
        this.mIndicatorType = p.mIndicatorType;
        this.mShowIndicator = p.mShowIndicator;
        this.mIndicatorStay = p.mIndicatorStay;
        this.mIndicatorColor = p.mIndicatorColor;
        this.mIndicatorTextColor = p.mIndicatorTextColor;
        this.mIndicatorTextSize = p.mIndicatorTextSize;
        this.mIndicatorCustomView = p.mIndicatorCustomView;
        this.mIndicatorCustomTopContentView = p.mIndicatorCustomTopContentView;
        //track
        this.mBackgroundTrackSize = p.mBackgroundTrackSize;
        this.mProgressTrackSize = p.mProgressTrackSize;
        this.mBackgroundTrackColor = p.mBackgroundTrackColor;
        this.mProgressTrackColor = p.mProgressTrackColor;
        this.mTrackRoundedCorners = p.mTrackRoundedCorners;
        //tick
        this.mTickNum = p.mTickNum;
        this.mTickType = p.mTickType;
        this.mTickSize = p.mTickSize;
        this.mTickColor = p.mTickColor;
        this.mTickHideBothEnds = p.mTickHideBothEnds;
        this.mTickOnThumbLeftHide = p.mTickOnThumbLeftHide;
        this.mTickDrawable = p.mTickDrawable;
        //text
        this.mTextSize = p.mTextSize;
        this.mTextColor = p.mTextColor;
        this.mLeftEndText = p.mLeftEndText;
        this.mRightEndText = p.mRightEndText;
        this.mTextArray = p.mTextArray;
        this.mTextTypeface = p.mTextTypeface;
        //thumb
        this.mThumbColor = p.mThumbColor;
        this.mThumbSize = p.mThumbSize;
        this.mThumbDrawable = p.mThumbDrawable;
        this.mThumbProgressStay = p.mThumbProgressStay;
        return this;
    }
}
