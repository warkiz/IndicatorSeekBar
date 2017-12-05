package com.warkiz.widget;


/**
 * created by ZhuangGuangquan on 2017/9/11
 */
public interface IndicatorSeekBarType {
    /**
     * seek bar slides smoothly.
     */
    int CONTINUOUS = 0;
    /**
     * seek bar with end side text slides smoothly.
     */
    int CONTINUOUS_TEXTS_ENDS = 1;
    /**
     * seek bar with tick marks will  slide with a block length.
     */
    int DISCRETE_TICKS = 2;
    /**
     * seek bar with tick marks and texts below marks will slide with a block length.
     */
    int DISCRETE_TICKS_TEXTS = 3;
    /**
     * seek bar with tick marks and texts below marks of both end sides will  slide with a block length.
     */
    int DISCRETE_TICKS_TEXTS_ENDS = 4;
}