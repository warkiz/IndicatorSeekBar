package com.warkiz.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import androidx.annotation.ArrayRes;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import android.view.View;

/**
 * created by zhuangguangquan on 2018/6/3
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

public class Builder {
    final Context context;
    //seek bar
    float max = 100;
    float min = 0;
    float progress = 0;
    boolean progressValueFloat = false;
    boolean seekSmoothly = false;
    boolean r2l = false;
    boolean userSeekable = true;
    boolean onlyThumbDraggable = false;
    boolean clearPadding = false;
    //indicator
    int showIndicatorType = IndicatorType.ROUNDED_RECTANGLE;
    int indicatorColor = Color.parseColor("#FF4081");
    int indicatorTextColor = Color.parseColor("#FFFFFF");
    int indicatorTextSize = 0;
    View indicatorContentView = null;
    View indicatorTopContentView = null;
    //track
    int trackBackgroundSize = 0;
    int trackBackgroundColor = Color.parseColor("#D7D7D7");
    int trackProgressSize = 0;
    int trackProgressColor = Color.parseColor("#FF4081");
    boolean trackRoundedCorners = false;
    //thumbText
    int thumbTextColor = Color.parseColor("#FF4081");
    boolean showThumbText = false;
    //thumb
    int thumbSize = 0;
    int thumbColor = Color.parseColor("#FF4081");
    ColorStateList thumbColorStateList = null;
    Drawable thumbDrawable = null;
    //tickTexts
    boolean showTickText;
    int tickTextsColor = Color.parseColor("#FF4081");
    int tickTextsSize = 0;
    String[] tickTextsCustomArray = null;
    Typeface tickTextsTypeFace = Typeface.DEFAULT;
    ColorStateList tickTextsColorStateList = null;
    //tickMarks
    int tickCount = 0;
    int showTickMarksType = TickMarkType.NONE;
    int tickMarksColor = Color.parseColor("#FF4081");
    int tickMarksSize = 0;
    Drawable tickMarksDrawable = null;
    boolean tickMarksEndsHide = false;
    boolean tickMarksSweptHide = false;
    ColorStateList tickMarksColorStateList = null;

    Builder(Context context) {
        this.context = context;
        this.indicatorTextSize = SizeUtils.sp2px(context, 14);
        this.trackBackgroundSize = SizeUtils.dp2px(context, 2);
        this.trackProgressSize = SizeUtils.dp2px(context, 2);
        this.tickMarksSize = SizeUtils.dp2px(context, 10);
        this.tickTextsSize = SizeUtils.sp2px(context, 13);
        this.thumbSize = SizeUtils.dp2px(context, 14);
    }

    /**
     * call this to new an IndicatorSeekBar
     *
     * @return IndicatorSeekBar
     */
    public IndicatorSeekBar build() {
        return new IndicatorSeekBar(this);
    }

    /**
     * Set the upper limit of this seek bar's range.
     *
     * @param max the max range
     * @return Builder
     */
    public Builder max(float max) {
        this.max = max;
        return this;
    }

    /**
     * Set the  lower limit of this seek bar's range.
     *
     * @param min the min range
     * @return Builder
     */
    public Builder min(float min) {
        this.min = min;
        return this;
    }

    /**
     * Sets the current progress to the specified value.
     *
     * @param progress the current level of seek bar
     * @return Builder
     */
    public Builder progress(float progress) {
        this.progress = progress;
        return this;
    }

    /**
     * make the progress in float type. default in int type.
     *
     * @param isFloatProgress true for float progress,default false.
     * @return Builder
     */
    public Builder progressValueFloat(boolean isFloatProgress) {
        this.progressValueFloat = isFloatProgress;
        return this;
    }

    /**
     * seek continuously or discrete
     *
     * @param seekSmoothly true for seek continuously ignore having tickMarks.
     * @return Builder
     */
    public Builder seekSmoothly(boolean seekSmoothly) {
        this.seekSmoothly = seekSmoothly;
        return this;
    }

    /**
     * right to left,compat local problem.
     *
     * @param r2l true for local which read text from right to left
     * @return Builder
     */
    public Builder r2l(boolean r2l) {
        this.r2l = r2l;
        return this;
    }

    /**
     * seek bar has a default padding left and right(16 dp) , call this method to set both to zero.
     *
     * @param clearPadding true to clear the default padding, false to keep.
     * @return Builder
     */
    public Builder clearPadding(boolean clearPadding) {
        this.clearPadding = clearPadding;
        return this;
    }

    /**
     * prevent user from touching to seek or not
     *
     * @param userSeekable true user can seek.
     * @return Builder
     */
    public Builder userSeekable(boolean userSeekable) {
        this.userSeekable = userSeekable;
        return this;
    }

    /**
     * user change the thumb's location by touching thumb,touching track will not worked.
     *
     * @param onlyThumbDraggable true for seeking only by drag thumb. default false;
     * @return Builder
     */
    public Builder onlyThumbDraggable(boolean onlyThumbDraggable) {
        this.onlyThumbDraggable = onlyThumbDraggable;
        return this;
    }

    /**
     * call this method to show different shape of indicator.
     *
     * @param showIndicatorType see{@link IndicatorType}
     *                          IndicatorType.NONE;
     *                          IndicatorType.CIRCULAR_BUBBLE;
     *                          IndicatorType.ROUNDED_RECTANGLE;
     *                          IndicatorType.RECTANGLE;
     *                          IndicatorType.CUSTOM;
     * @return Builder
     */
    public Builder showIndicatorType(int showIndicatorType) {
        this.showIndicatorType = showIndicatorType;
        return this;
    }

    /**
     * set the seek bar's indicator's color. have no influence on custom indicator.
     *
     * @param indicatorColor colorInt
     * @return Builder
     */
    public Builder indicatorColor(@ColorInt int indicatorColor) {
        this.indicatorColor = indicatorColor;
        return this;
    }

    /**
     * set the color for indicator text . have no influence on custom tickDrawable.
     *
     * @param indicatorTextColor ColorInt
     * @return Builder
     */
    public Builder indicatorTextColor(@ColorInt int indicatorTextColor) {
        this.indicatorTextColor = indicatorTextColor;
        return this;
    }

    /**
     * change the size for indicator text.have no influence on custom indicator.
     *
     * @param indicatorTextSize The scaled pixel size.
     * @return Builder
     */
    public Builder indicatorTextSize(int indicatorTextSize) {
        this.indicatorTextSize = SizeUtils.sp2px(context, indicatorTextSize);
        return this;
    }

    /**
     * set the seek bar's indicator's custom indicator view. only effect on custom indicator type.
     *
     * @param indicatorContentView the custom indicator view
     * @return Builder
     */
    public Builder indicatorContentView(@NonNull View indicatorContentView) {
        this.indicatorContentView = indicatorContentView;
        return this;
    }

    /**
     * set the seek bar's indicator's custom indicator layout identify. only effect on custom indicator type.
     *
     * @param layoutId the custom indicator layout identify
     * @return Builder
     */
    public Builder indicatorContentViewLayoutId(@LayoutRes int layoutId) {
        this.indicatorContentView = View.inflate(context, layoutId, null);
        return this;
    }

    /**
     * set the seek bar's indicator's custom top content view.
     * no effect on custom and circular_bubble indicator type.
     *
     * @param topContentView the custom indicator top content view
     * @return Builder
     */
    public Builder indicatorTopContentView(View topContentView) {
        this.indicatorTopContentView = topContentView;
        return this;
    }

    /**
     * set the seek bar's indicator's custom top content view layout identify.
     * no effect on custom and circular_bubble indicator type.
     *
     * @param layoutId the custom view for indicator top content layout identify.
     * @return Builder
     */
    public Builder indicatorTopContentViewLayoutId(@LayoutRes int layoutId) {
        this.indicatorTopContentView = View.inflate(context, layoutId, null);
        return this;
    }


    /**
     * set the seek bar's background track's Stroke Width
     *
     * @param trackBackgroundSize The dp size.
     * @return Builder
     */
    public Builder trackBackgroundSize(int trackBackgroundSize) {
        this.trackBackgroundSize = SizeUtils.dp2px(context, trackBackgroundSize);
        return this;
    }

    /**
     * set the seek bar's background track's color.
     *
     * @param trackBackgroundColor colorInt
     * @return Builder
     */
    public Builder trackBackgroundColor(@ColorInt int trackBackgroundColor) {
        this.trackBackgroundColor = trackBackgroundColor;
        return this;
    }

    /**
     * set the seek bar's progress track's Stroke Width
     *
     * @param trackProgressSize The dp size.
     * @return Builder
     */
    public Builder trackProgressSize(int trackProgressSize) {
        this.trackProgressSize = SizeUtils.dp2px(context, trackProgressSize);
        return this;
    }

    /**
     * set the seek bar's progress track's color.
     *
     * @param trackProgressColor colorInt
     * @return Builder
     */
    public Builder trackProgressColor(@ColorInt int trackProgressColor) {
        this.trackProgressColor = trackProgressColor;
        return this;
    }

    /**
     * call this method to show the seek bar's ends to square corners.default rounded corners.
     *
     * @param trackRoundedCorners false to show square corners.
     * @return Builder
     */
    public Builder trackRoundedCorners(boolean trackRoundedCorners) {
        this.trackRoundedCorners = trackRoundedCorners;
        return this;
    }

    /**
     * set the seek bar's thumb's text color.
     *
     * @param thumbTextColor colorInt
     * @return Builder
     */
    public Builder thumbTextColor(@ColorInt int thumbTextColor) {
        this.thumbTextColor = thumbTextColor;
        return this;
    }

    /**
     * call this method to show the text below thumb or not
     *
     * @param showThumbText show the text below thumb or not
     * @return Builder
     */
    public Builder showThumbText(boolean showThumbText) {
        this.showThumbText = showThumbText;
        return this;
    }

    /**
     * set the seek bar's thumb's color.
     *
     * @param thumbColor colorInt
     * @return Builder
     */
    public Builder thumbColor(@ColorInt int thumbColor) {
        this.thumbColor = thumbColor;
        return this;
    }

    /**
     * set the seek bar's thumb's selector color.
     *
     * @param thumbColorStateList color selector
     * @return Builder
     * selector format like:
     */
    //<?xml version="1.0" encoding="utf-8"?>
    //<selector xmlns:android="http://schemas.android.com/apk/res/android">
    //<item android:color="@color/colorAccent" android:state_pressed="true" />  <!--this color is for thumb which is at pressing status-->
    //<item android:color="@color/color_blue" />                                <!--for thumb which is at normal status-->
    //</selector>
    public Builder thumbColorStateList(@NonNull ColorStateList thumbColorStateList) {
        this.thumbColorStateList = thumbColorStateList;
        return this;
    }

    /**
     * set the seek bar's thumb's Width.will be limited in 30dp.
     *
     * @param thumbSize The dp size.
     * @return Builder
     */
    public Builder thumbSize(int thumbSize) {
        this.thumbSize = SizeUtils.dp2px(context, thumbSize);
        return this;
    }

    /**
     * set a new thumb drawable.
     *
     * @param thumbDrawable the drawable for thumb.
     * @return Builder
     */
    public Builder thumbDrawable(@NonNull Drawable thumbDrawable) {
        this.thumbDrawable = thumbDrawable;
        return this;
    }

    /**
     * call this method to custom the thumb showing drawable by selector Drawable.
     *
     * @param thumbStateListDrawable the drawable show as Thumb.
     * @return Builder
     * <p>
     * selector format:
     */
    //<?xml version="1.0" encoding="utf-8"?>
    //<selector xmlns:android="http://schemas.android.com/apk/res/android">
    //<item android:drawable="Your drawableA" android:state_pressed="true" />  <!--this drawable is for thumb when pressing-->
    //<item android:drawable="Your drawableB" />  < !--for thumb when normal-->
    //</selector>
    public Builder thumbDrawable(@NonNull StateListDrawable thumbStateListDrawable) {
        this.thumbDrawable = thumbStateListDrawable;
        return this;
    }


    /**
     * show the tick texts or not
     *
     * @param showTickText show the text below track or not.
     * @return Builder
     */
    public Builder showTickTexts(boolean showTickText) {
        this.showTickText = showTickText;
        return this;
    }

    /**
     * set the color for text below/above seek bar's tickText.
     *
     * @param tickTextsColor ColorInt
     * @return Builder
     */
    public Builder tickTextsColor(@ColorInt int tickTextsColor) {
        this.tickTextsColor = tickTextsColor;
        return this;
    }

    /**
     * set the selector color for text below/above seek bar's tickText.
     *
     * @param tickTextsColorStateList ColorInt
     * @return Builder
     * selector format like:
     */
    //<?xml version="1.0" encoding="utf-8"?>
    //<selector xmlns:android="http://schemas.android.com/apk/res/android">
    //<item android:color="@color/colorAccent" android:state_selected="true" />  <!--this color is for texts those are at left side of thumb-->
    //<item android:color="@color/color_blue" android:state_hovered="true" />     <!--for thumb below text-->
    //<item android:color="@color/color_gray" />                                 <!--for texts those are at right side of thumb-->
    //</selector>
    public Builder tickTextsColorStateList(@NonNull ColorStateList tickTextsColorStateList) {
        this.tickTextsColorStateList = tickTextsColorStateList;
        return this;
    }

    /**
     * set the size for tickText which below/above seek bar's tick .
     *
     * @param tickTextsSize The scaled pixel size.
     * @return Builder
     */
    public Builder tickTextsSize(int tickTextsSize) {
        this.tickTextsSize = SizeUtils.sp2px(context, tickTextsSize);
        return this;
    }

    /**
     * call this method to replace the seek bar's tickMarks' below/above tick texts.
     *
     * @param tickTextsArray the length should same as tickCount.
     * @return Builder
     */
    public Builder tickTextsArray(String[] tickTextsArray) {
        this.tickTextsCustomArray = tickTextsArray;
        return this;
    }


    /**
     * call this method to replace the seek bar's tickMarks' below/above tick texts.
     *
     * @param tickTextsArray the length should same as tickNum.
     * @return Builder
     */
    public Builder tickTextsArray(@ArrayRes int tickTextsArray) {
        this.tickTextsCustomArray = context.getResources().getStringArray(tickTextsArray);
        return this;
    }

    /**
     * set the tick text's / thumb text textTypeface .
     *
     * @param tickTextsTypeFace The text textTypeface.
     * @return Builder
     */
    public Builder tickTextsTypeFace(Typeface tickTextsTypeFace) {
        this.tickTextsTypeFace = tickTextsTypeFace;
        return this;
    }

    /**
     * set the tickMarks number.
     *
     * @param tickCount the tickMarks count show on seek bar.
     *                  if you want the seek bar's block size is N , this tickCount should be N+1.
     * @return Builder
     */
    public Builder tickCount(int tickCount) {
        this.tickCount = tickCount;
        return this;
    }

    /**
     * call this method to show different tickMark shape.
     *
     * @param tickMarksType see{@link TickMarkType}
     *                      TickMarkType.NONE;
     *                      TickMarkType.OVAL;
     *                      TickMarkType.SQUARE;
     *                      TickMarkType.DIVIDER;
     * @return Builder
     */
    public Builder showTickMarksType(int tickMarksType) {
        this.showTickMarksType = tickMarksType;
        return this;
    }

    /**
     * set the seek bar's tick's color.
     *
     * @param tickMarksColor colorInt
     * @return Builder
     */
    public Builder tickMarksColor(@ColorInt int tickMarksColor) {
        this.tickMarksColor = tickMarksColor;
        return this;
    }

    /**
     * set the seek bar's tick's color.
     *
     * @param tickMarksColorStateList colorInt
     * @return Builder
     * selector format like:
     */
    //<?xml version="1.0" encoding="utf-8"?>
    //<selector xmlns:android="http://schemas.android.com/apk/res/android">
    //<item android:color="@color/colorAccent" android:state_selected="true" />  <!--this color is for marks those are at left side of thumb-->
    //<item android:color="@color/color_gray" />                                 <!--for marks those are at right side of thumb-->
    //</selector>
    public Builder tickMarksColor(@NonNull ColorStateList tickMarksColorStateList) {
        this.tickMarksColorStateList = tickMarksColorStateList;
        return this;
    }

    /**
     * set the seek bar's tick width , if tick type is divider, call this method will be not worked(tick type is divider,has a regular value 2dp).
     *
     * @param tickMarksSize the dp size.
     * @return Builder
     */
    public Builder tickMarksSize(int tickMarksSize) {
        this.tickMarksSize = SizeUtils.dp2px(context, tickMarksSize);
        return this;
    }

    /**
     * call this method to custom the tick showing drawable.
     *
     * @param tickMarksDrawable the drawable show as tickMark.
     * @return Builder
     */
    public Builder tickMarksDrawable(@NonNull Drawable tickMarksDrawable) {
        this.tickMarksDrawable = tickMarksDrawable;
        return this;
    }

    /**
     * call this method to custom the tick showing drawable by selector.
     *
     * @param tickMarksStateListDrawable the StateListDrawable show as tickMark.
     * @return Builder
     * selector format like :
     */
    //<?xml version="1.0" encoding="utf-8"?>
    //<selector xmlns:android="http://schemas.android.com/apk/res/android">
    //<item android:drawable="@mipmap/ic_launcher_round" android:state_pressed="true" />  <!--this drawable is for thumb when pressing-->
    //<item android:drawable="@mipmap/ic_launcher" />  <!--for thumb when normal-->
    //</selector>
    public Builder tickMarksDrawable(@NonNull StateListDrawable tickMarksStateListDrawable) {
        this.tickMarksDrawable = tickMarksStateListDrawable;
        return this;
    }

    /**
     * call this method to hide the tickMarks which show in the both ends sides of seek bar.
     *
     * @param tickMarksEndsHide true for hide.
     * @return Builder
     */
    public Builder tickMarksEndsHide(boolean tickMarksEndsHide) {
        this.tickMarksEndsHide = tickMarksEndsHide;
        return this;
    }

    /**
     * call this method to hide the tickMarks on seekBar's thumb left;
     *
     * @param tickMarksSweptHide true for hide.
     * @return Builder
     */
    public Builder tickMarksSweptHide(boolean tickMarksSweptHide) {
        this.tickMarksSweptHide = tickMarksSweptHide;
        return this;
    }

}