package com.warkiz.widget;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * created by zhuangguangquan on 2017/9/9
 * <p>
 * https://github.com/warkiz/IndicatorSeekBar
 * <p>
 * Donation/打赏:
 * If this library is helpful to you ,you can give me a donation by:
 *
 * @see <a href="https://www.paypal.me/BuyMeACupOfTeaThx">ZhuanGuangQuan's Paypal</a>, or
 * @see <a href="https://github.com/warkiz/IndicatorSeekBar/blob/master/app/src/main/res/mipmap-xxhdpi/wechat_pay.png?raw=true">微信支付</a>, or
 * @see <a href="https://github.com/warkiz/IndicatorSeekBar/blob/master/app/src/main/res/mipmap-xxhdpi/alipay.png?raw=true">支付宝</a>
 * <p>
 */
public class Indicator {
    private final int mWindowWidth;
    private int[] mLocation = new int[2];
    private ArrowView mArrowView;
    private TextView mProgressTextView;
    private PopupWindow mIndicatorPopW;
    private LinearLayout mTopContentView;
    private int mGap;
    private int mIndicatorColor;
    private Context mContext;
    private int mIndicatorType;
    private IndicatorSeekBar mSeekBar;
    private View mIndicatorView;
    private View mIndicatorCustomView;
    private View mIndicatorCustomTopContentView;
    private float mIndicatorTextSize;
    private int mIndicatorTextColor;

    public Indicator(Context context,
                     IndicatorSeekBar seekBar,
                     int indicatorColor,
                     int indicatorType,
                     int indicatorTextSize,
                     int indicatorTextColor,
                     View indicatorCustomView,
                     View indicatorCustomTopContentView) {
        this.mContext = context;
        this.mSeekBar = seekBar;
        this.mIndicatorColor = indicatorColor;
        this.mIndicatorType = indicatorType;
        this.mIndicatorCustomView = indicatorCustomView;
        this.mIndicatorCustomTopContentView = indicatorCustomTopContentView;
        this.mIndicatorTextSize = indicatorTextSize;
        this.mIndicatorTextColor = indicatorTextColor;

        mWindowWidth = getWindowWidth();
        mGap = SizeUtils.dp2px(mContext, 2);
        initIndicator();
    }

    private void initIndicator() {
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
                            mProgressTextView = (TextView) view;
                            mProgressTextView.setText(mSeekBar.getIndicatorTextString());
                            mProgressTextView.setTextSize(SizeUtils.px2sp(mContext, mIndicatorTextSize));
                            mProgressTextView.setTextColor(mIndicatorTextColor);
                        } else {
                            throw new ClassCastException("the view identified by isb_progress in indicator custom layout can not be cast to TextView");
                        }
                    }
                }
            } else {
                throw new IllegalArgumentException("the attr：indicator_custom_layout must be set while you set the indicator type to CUSTOM.");
            }
        } else {
            if (mIndicatorType == IndicatorType.CIRCULAR_BUBBLE) {
                mIndicatorView = new CircleBubbleView(mContext, mIndicatorTextSize, mIndicatorTextColor, mIndicatorColor, "1000");
                ((CircleBubbleView) mIndicatorView).setProgress(mSeekBar.getIndicatorTextString());
            } else {
                mIndicatorView = View.inflate(mContext, R.layout.isb_indicator, null);
                //container
                mTopContentView = (LinearLayout) mIndicatorView.findViewById(R.id.indicator_container);
                //arrow
                mArrowView = (ArrowView) mIndicatorView.findViewById(R.id.indicator_arrow);
                mArrowView.setColor(mIndicatorColor);
                //progressText
                mProgressTextView = (TextView) mIndicatorView.findViewById(R.id.isb_progress);
                mProgressTextView.setText(mSeekBar.getIndicatorTextString());
                mProgressTextView.setTextSize(SizeUtils.px2sp(mContext, mIndicatorTextSize));
                mProgressTextView.setTextColor(mIndicatorTextColor);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mTopContentView.setBackground(getGradientDrawable());
                } else {
                    mTopContentView.setBackgroundDrawable(getGradientDrawable());
                }
                //custom top content view
                if (mIndicatorCustomTopContentView != null) {
                    //for the custom indicator top content view, if progress need to show when seeking ,
                    //need a TextView to show progress and this textView 's identify must be progress;
                    int progressTextViewId = mContext.getResources().getIdentifier("isb_progress", "id", mContext.getApplicationContext().getPackageName());
                    View topContentView = mIndicatorCustomTopContentView;
                    if (progressTextViewId > 0) {
                        View tv = topContentView.findViewById(progressTextViewId);
                        if (tv != null && tv instanceof TextView) {
                            setTopContentView(topContentView, (TextView) tv);
                        } else {
                            setTopContentView(topContentView);
                        }
                    } else {
                        setTopContentView(topContentView);
                    }

                }
            }
        }
    }

    @NonNull
    private GradientDrawable getGradientDrawable() {
        GradientDrawable tvDrawable;
        if (mIndicatorType == IndicatorType.ROUNDED_RECTANGLE) {
            tvDrawable = (GradientDrawable) mContext.getResources().getDrawable(R.drawable.isb_indicator_rounded_corners);
        } else {
            tvDrawable = (GradientDrawable) mContext.getResources().getDrawable(R.drawable.isb_indicator_square_corners);
        }
        tvDrawable.setColor(mIndicatorColor);
        return tvDrawable;
    }

    private int getWindowWidth() {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            return wm.getDefaultDisplay().getWidth();
        }
        return 0;
    }

    private int getIndicatorScreenX() {
        mSeekBar.getLocationOnScreen(mLocation);
        return mLocation[0];
    }

    private void adjustArrow(float touchX) {
        if (mIndicatorType == IndicatorType.CUSTOM || mIndicatorType == IndicatorType.CIRCULAR_BUBBLE) {
            return;
        }
        int indicatorScreenX = getIndicatorScreenX();
        if (indicatorScreenX + touchX < mIndicatorPopW.getContentView().getMeasuredWidth() / 2) {
            setMargin(mArrowView, -(int) (mIndicatorPopW.getContentView().getMeasuredWidth() / 2 - indicatorScreenX - touchX), -1, -1, -1);
        } else if (mWindowWidth - indicatorScreenX - touchX < mIndicatorPopW.getContentView().getMeasuredWidth() / 2) {
            setMargin(mArrowView, (int) (mIndicatorPopW.getContentView().getMeasuredWidth() / 2 - (mWindowWidth - indicatorScreenX - touchX)), -1, -1, -1);
        } else {
            setMargin(mArrowView, 0, 0, 0, 0);
        }
    }

    private void setMargin(View view, int left, int top, int right, int bottom) {
        if (view == null) {
            return;
        }
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            layoutParams.setMargins(left == -1 ? layoutParams.leftMargin : left, top == -1 ? layoutParams.topMargin : top, right == -1 ? layoutParams.rightMargin : right, bottom == -1 ? layoutParams.bottomMargin : bottom);
            view.requestLayout();
        }
    }

    void iniPop() {
        if (mIndicatorPopW != null) {
            return;
        }
        if (mIndicatorType != IndicatorType.NONE && mIndicatorView != null) {
            mIndicatorView.measure(0, 0);
            mIndicatorPopW = new PopupWindow(mIndicatorView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, false);
        }
    }

    View getInsideContentView() {
        return mIndicatorView;
    }

    void setProgressTextView(String text) {
        if (mIndicatorView instanceof CircleBubbleView) {
            ((CircleBubbleView) mIndicatorView).setProgress(text);
        } else if (mProgressTextView != null) {
            mProgressTextView.setText(text);
        }
    }

    void updateIndicatorLocation(int offset) {
        setMargin(mIndicatorView, offset, -1, -1, -1);
    }

    void updateArrowViewLocation(int offset) {
        setMargin(mArrowView, offset, -1, -1, -1);
    }


    /**
     * update the indicator position
     *
     * @param touchX the x location you touch without padding left.
     */
    void update(float touchX) {
        if (!mSeekBar.isEnabled() || !(mSeekBar.getVisibility() == View.VISIBLE)) {
            return;
        }
        refreshProgressText();
        if (mIndicatorPopW != null) {
            mIndicatorPopW.getContentView().measure(0, 0);
            mIndicatorPopW.update(mSeekBar, (int) (touchX - mIndicatorPopW.getContentView().getMeasuredWidth() / 2),
                    -(mSeekBar.getMeasuredHeight() + mIndicatorPopW.getContentView().getMeasuredHeight() - mSeekBar.getPaddingTop() /*- mSeekBar.getTextHeight() */ + mGap), -1, -1);
            adjustArrow(touchX);
        }
    }

    /**
     * call this method to show the indicator.
     *
     * @param touchX the x location you touch, padding left excluded.
     */
    void show(float touchX) {
        if (!mSeekBar.isEnabled() || !(mSeekBar.getVisibility() == View.VISIBLE)) {
            return;
        }
        refreshProgressText();
        if (mIndicatorPopW != null) {
            mIndicatorPopW.getContentView().measure(0, 0);
            mIndicatorPopW.showAsDropDown(mSeekBar, (int) (touchX - mIndicatorPopW.getContentView().getMeasuredWidth() / 2f),
                    -(mSeekBar.getMeasuredHeight() + mIndicatorPopW.getContentView().getMeasuredHeight() - mSeekBar.getPaddingTop() /*- mSeekBar.getTextHeight()*/ + mGap));
            adjustArrow(touchX);
        }
    }

    void refreshProgressText() {
        String tickTextString = mSeekBar.getIndicatorTextString();
        if (mIndicatorView instanceof CircleBubbleView) {
            ((CircleBubbleView) mIndicatorView).setProgress(tickTextString);
        } else if (mProgressTextView != null) {
            mProgressTextView.setText(tickTextString);
        }
    }

    /**
     * call this method hide the indicator
     */
    void hide() {
        if (mIndicatorPopW == null) {
            return;
        }
        mIndicatorPopW.dismiss();
    }

    boolean isShowing() {
        return mIndicatorPopW != null && mIndicatorPopW.isShowing();
    }


    /*----------------------API START-------------------*/

    /**
     * get the indicator content view.
     *
     * @return the view which is inside indicator.
     */
    public View getContentView() {
        return mIndicatorView;
    }

    /**
     * call this method to replace the current indicator with a new indicator view , indicator arrow will be replace ,too.
     *
     * @param customIndicatorView a new content view for indicator.
     */
    public void setContentView(@NonNull View customIndicatorView) {
        this.mIndicatorType = IndicatorType.CUSTOM;
        this.mIndicatorCustomView = customIndicatorView;
        initIndicator();
    }

    /**
     * call this method to replace the current indicator with a new indicator view, indicator arrow will be replace ,too.
     *
     * @param customIndicatorView a new content view for indicator.
     * @param progressTextView    this TextView will show the progress or tick text, must be found in @param customIndicatorView
     */
    public void setContentView(@NonNull View customIndicatorView, TextView progressTextView) {
        this.mProgressTextView = progressTextView;
        this.mIndicatorType = IndicatorType.CUSTOM;
        this.mIndicatorCustomView = customIndicatorView;
        initIndicator();
    }

    /**
     * get the indicator top content view.
     * if indicator type {@link IndicatorType} is CUSTOM or CIRCULAR_BUBBLE, call this method will get a null value.
     *
     * @return the view which is inside indicator's top part, not include arrow
     */
    public View getTopContentView() {
        return mTopContentView;
    }

    /**
     * set the View to the indicator top container, not influence indicator arrow ;
     * if indicator type {@link IndicatorType} is CUSTOM or CIRCULAR_BUBBLE, call this method will be not worked.
     *
     * @param topContentView the view is inside the indicator TOP part, not influence indicator arrow;
     */
    public void setTopContentView(@NonNull View topContentView) {
        setTopContentView(topContentView, null);
    }

    /**
     * set the  View to the indicator top container, and show the changing progress in indicator when seek;
     * not influence indicator arrow;
     * if indicator type is custom , this method will be not work.
     *
     * @param topContentView   the view is inside the indicator TOP part, not influence indicator arrow;
     * @param progressTextView this TextView will show the progress or tick text, must be found in @param topContentView
     */
    public void setTopContentView(@NonNull View topContentView, @Nullable TextView progressTextView) {
        this.mProgressTextView = progressTextView;
        this.mTopContentView.removeAllViews();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            topContentView.setBackground(getGradientDrawable());
        } else {
            topContentView.setBackgroundDrawable(getGradientDrawable());
        }
        this.mTopContentView.addView(topContentView);
    }

    /*----------------------API END-------------------*/

}