package com.warkiz.widget;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * created by ZhuangGuangquan on 2017/9/9
 */


class Indicator {
    private final Context mContext;
    private final int mIndicatorType;
    private final IndicatorSeekBar mSeekBar;
    private final int mWindowWidth;
    private final View mIndicatorCustomView;
    private final View mIndicatorCustomTopContentView;
    private final int mIndicatorTextSize;
    private final int mIndicatorTextColor;
    private int mIndicatorColor;
    private int[] mLocation = new int[2];
    private ArrowView mIndicatorArrow;
    private TextView mIndicatorText;
    private PopupWindow mIndicator;
    private LinearLayout mTopContentView;

    Indicator(Context context, IndicatorSeekBar seekBar, BuilderParams p) {
        this.mContext = context;
        this.mSeekBar = seekBar;
        this.mIndicatorColor = p.mIndicatorColor;
        this.mIndicatorType = p.mIndicatorType;
        this.mIndicatorCustomView = p.mIndicatorCustomView;
        this.mIndicatorCustomTopContentView = p.mIndicatorCustomTopContentView;
        this.mIndicatorTextSize = p.mIndicatorTextSize;
        this.mIndicatorTextColor = p.mIndicatorTextColor;
        initIndicator();
        mWindowWidth = getWindowWidth();
    }

    private void initIndicator() {
        View mIndicatorView = null;
        if (mIndicatorType == IndicatorType.CUSTOM) {
            if (mIndicatorCustomView != null) {
                mIndicatorView = mIndicatorCustomView;
                //for the custom indicator view, if progress need to show when seeking ,
                // need a TextView to show progress and this textView 's identify must be progress;
                int progressTextViewId = mContext.getResources().getIdentifier("isb_progress", "id", mContext.getApplicationContext().getPackageName());
                if (progressTextViewId > 0) {
                    View view = mIndicatorView.findViewById(progressTextViewId);
                    if (view != null) {
                        if (view instanceof TextView) {
                            //progressText
                            mIndicatorText = (TextView) view;
                            mIndicatorText.setText(String.valueOf(mSeekBar.getProgress()));
                            mIndicatorText.setTextSize(IndicatorUtils.px2sp(mContext, mIndicatorTextSize));
                            mIndicatorText.setTextColor(mIndicatorTextColor);
                        } else {
                            throw new ClassCastException("the view identified by isb_progress in indicator custom layout can not be cast to TextView");
                        }
                    }
                }
            }
        } else {
            mIndicatorView = View.inflate(mContext, R.layout.indicator, null);
            //container
            mTopContentView = (LinearLayout) mIndicatorView.findViewById(R.id.indicator_container);
            //arrow
            mIndicatorArrow = (ArrowView) mIndicatorView.findViewById(R.id.indicator_arrow);
            mIndicatorArrow.setColor(mIndicatorColor);
            //progressText
            mIndicatorText = (TextView) mIndicatorView.findViewById(R.id.isb_progress);
            mIndicatorText.setText(String.valueOf(mSeekBar.getProgress()));
            mIndicatorText.setTextSize(IndicatorUtils.px2sp(mContext, mIndicatorTextSize));
            mIndicatorText.setTextColor(mIndicatorTextColor);
            mTopContentView.setBackground(getGradientDrawable());
            //custom top content view
            if (mIndicatorCustomTopContentView != null) {
                //for the custom indicator top content view, if progress need to show when seeking ,
                //need a TextView to show progress and this textView 's identify must be progress;
                int progressTextViewId = mContext.getResources().getIdentifier("isb_progress", "id", mContext.getApplicationContext().getPackageName());
                View topContentView = mIndicatorCustomTopContentView;
                if (progressTextViewId > 0) {
                    View tv = topContentView.findViewById(progressTextViewId);
                    if (tv != null) {
                        setIndicatorTopContentView(topContentView, progressTextViewId);
                    } else {
                        setIndicatorTopContentView(topContentView);
                    }
                } else {
                    setIndicatorTopContentView(topContentView);
                }

            }
        }
        if (mIndicatorView != null) {
            mIndicatorView.measure(0, 0);
            mIndicator = new PopupWindow(mIndicatorView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, false);
        }
    }

    @NonNull
    private GradientDrawable getGradientDrawable() {
        GradientDrawable tvDrawable;
        if (mIndicatorType == IndicatorType.SQUARE_CORNERS) {
            tvDrawable = (GradientDrawable) mContext.getResources().getDrawable(R.drawable.indicator_square_corners);
        } else {
            tvDrawable = (GradientDrawable) mContext.getResources().getDrawable(R.drawable.indicator_rounded_corners);
        }
        tvDrawable.setColor(mIndicatorColor);
        return tvDrawable;
    }

    private int getWindowWidth() {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }

    private int getIndicatorScreenX() {
        mSeekBar.getLocationOnScreen(mLocation);
        return mLocation[0];
    }

    private void adjustArrow(float touchX) {
        if (mIndicatorType == IndicatorType.CUSTOM) {
            return;
        }
        int indicatorScreenX = getIndicatorScreenX();
        if (indicatorScreenX + touchX < mIndicator.getContentView().getMeasuredWidth() / 2) {
            setMargin(-(int) (mIndicator.getContentView().getMeasuredWidth() / 2 - indicatorScreenX - touchX), -1, -1, -1);
        } else if (mWindowWidth - indicatorScreenX - touchX < mIndicator.getContentView().getMeasuredWidth() / 2) {
            setMargin((int) (mIndicator.getContentView().getMeasuredWidth() / 2 - (mWindowWidth - indicatorScreenX - touchX)), -1, -1, -1);
        } else {
            setMargin(0, 0, 0, 0);
        }
    }

    private void setMargin(int left, int top, int right, int bottom) {
        if (mIndicatorArrow == null) {
            return;
        }
        if (mIndicatorArrow.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) mIndicatorArrow.getLayoutParams();
            layoutParams.setMargins(left == -1 ? layoutParams.leftMargin : left, top == -1 ? layoutParams.topMargin : top, right == -1 ? layoutParams.rightMargin : right, bottom == -1 ? layoutParams.bottomMargin : bottom);
            mIndicatorArrow.requestLayout();
        }
    }

    /**
     * update the indicator position
     *
     * @param touchX the x location you touch without padding left.
     */
    void update(float touchX, int seekBarType, int thumbPos) {
        if (mIndicator != null) {
            if (mIndicatorText != null) {
                if (hasTextArray(seekBarType)) {
                    if (thumbPos >= mSeekBar.getTextArray().length) {
                        mIndicatorText.setText("");
                    } else {
                        mIndicatorText.setText(mSeekBar.getTextArray()[thumbPos]);
                    }
                } else {
                    mIndicatorText.setText(mSeekBar.getProgressString());
                }
                mIndicator.getContentView().measure(0, 0);
            }
            mIndicator.update(mSeekBar, (int) (touchX - mIndicator.getContentView().getMeasuredWidth() / 2), -(mSeekBar.getMeasuredHeight() + mIndicator.getContentView().getMeasuredHeight() + mSeekBar.getPaddingTop() + IndicatorUtils.dp2px(mContext, 2)), -1, -1);
            adjustArrow(touchX);
        }
    }

    /**
     * call this method to show the indicator.
     *
     * @param touchX the x location you touch without padding left.
     */
    void showIndicator(float touchX, int seekBarType, int thumbPos) {
        if (mIndicator != null && !mIndicator.isShowing()) {
            if (mIndicatorText != null) {
                if (hasTextArray(seekBarType)) {
                    if (thumbPos >= mSeekBar.getTextArray().length) {
                        mIndicatorText.setText("");
                    } else {
                        mIndicatorText.setText(mSeekBar.getTextArray()[thumbPos]);
                    }
                } else {
                    mIndicatorText.setText(mSeekBar.getProgressString());
                }
                mIndicator.getContentView().measure(0, 0);
            }
            mIndicator.showAsDropDown(mSeekBar, (int) (touchX - mIndicator.getContentView().getMeasuredWidth() / 2f), -(mSeekBar.getMeasuredHeight() + mIndicator.getContentView().getMeasuredHeight() + mSeekBar.getPaddingTop() + IndicatorUtils.dp2px(mContext, 2)));
            adjustArrow(touchX);
        }
    }

    private boolean hasTextArray(int seekBarType) {
        CharSequence[] textArray = mSeekBar.getTextArray();
        return seekBarType == IndicatorSeekBarType.DISCRETE_TICKS_TEXTS && textArray != null && textArray.length > 0;
    }

    /**
     * call this method hide the indicator
     */
    void hideIndicator() {
        if (mIndicator != null && mIndicator.isShowing()) {
            mIndicator.dismiss();
        }
    }

    /**
     * set the  View to the indicator top container, not influence indicator arrow ;
     * if indicator type is custom , this method will be not work.
     *
     * @param topContentView the view is inside the indicator TOP part, not influence indicator arrow;
     */
    public void setIndicatorTopContentView(@NonNull View topContentView) {
        mTopContentView.removeAllViews();
        topContentView.setBackground(getGradientDrawable());
        mTopContentView.addView(topContentView);
    }

    /**
     * set the  View to the indicator top container, not influence indicator arrow ;
     * if indicator type is custom , this method will be not work.
     *
     * @param topContentLayoutId the view id for indicator TOP part, not influence indicator arrow;
     */
    public void setIndicatorTopContentLayout(@LayoutRes int topContentLayoutId) {
        mTopContentView.removeAllViews();
        View topContentView = View.inflate(mContext, topContentLayoutId, null);
        topContentView.setBackground(getGradientDrawable());
        mTopContentView.addView(topContentView);
    }

    /**
     * set the  View to the indicator top container, and show the changing progress in indicator when seek;
     * not influence indicator arrow;
     * * if indicator type is custom , this method will be not work.
     *
     * @param topContentView     the view is inside the indicator TOP part, not influence indicator arrow;
     * @param progressTextViewId the id can be find in @param topContentView, and it is a TextView id.
     */
    public void setIndicatorTopContentView(@NonNull View topContentView, @IdRes int progressTextViewId) {
        View tv = topContentView.findViewById(progressTextViewId);
        if (tv == null) {
            throw new IllegalArgumentException(" can not find the TextView in indicator topContentView by id: " + progressTextViewId);
        }
        if (!(tv instanceof TextView)) {
            throw new ClassCastException(" the view identified by progressTextViewId can not be cast to TextView. ");
        }
        mIndicatorText = (TextView) tv;
        mTopContentView.removeAllViews();
        topContentView.setBackground(getGradientDrawable());
        mTopContentView.addView(topContentView);
    }

    /**
     * check the indicator is showing or not .
     *
     * @return true is showing.
     */
    public boolean isShowing() {
        return mIndicator != null && mIndicator.isShowing();
    }

    /**
     * call this method to replace the current indicator with a new indicator view , indicator arrow will be replace ,too.
     *
     * @param customIndicatorView a new view for indicator.
     */
    public void setCustomIndicator(@NonNull View customIndicatorView) {
        mIndicator.setContentView(customIndicatorView);
    }

    /**
     * the text view to show the progress in indicator , must be found in indicator root view.
     *
     * @param view a text view can be found in indicator root view
     */
    public void setProgressTextView(@NonNull TextView view) {
        mIndicatorText = view;
    }
}