package com.warkiz.widget;

/**
 * created by zhuangguangquan on  2018/6/3
 * <p>
 * A callback that notifies clients when the progress level has been
 * changed. This includes changes that were initiated by the user through a
 * touch track or thumb as well as changes that were initiated programmatically.
 */
public interface OnSeekChangeListener {
    /**
     * Notification that the progress level has changed.
     * <p>
     * Clients can use the fromUser parameter to distinguish user-initiated changes from
     * those that occurred programmatically, also, if the seek bar type is discrete series,
     * clients can use the thumbPosition parameter to check the thumb position on ticks and
     * tick text parameter to get the tick text which located at current thumb below.
     *
     * @param seekParams the params info about the seeking bar
     */
    void onSeeking(SeekParams seekParams);

    /**
     * Notification that the user has started a touch gesture. Clients may want to use this
     * to disable advancing the seek bar.
     *
     * @param seekBar The SeekBar in which the touch gesture began
     */
    void onStartTrackingTouch(IndicatorSeekBar seekBar);

    /**
     * Notification that the user has finished a touch gesture. Clients may want to use this
     * to re-enable advancing the seek bar.
     *
     * @param seekBar The SeekBar in which the touch gesture began
     */
    void onStopTrackingTouch(IndicatorSeekBar seekBar);
}