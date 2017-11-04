package com.warkiz.indicatorseekbar.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.warkiz.indicatorseekbar.R;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.IndicatorSeekBarType;
import com.warkiz.widget.IndicatorType;
import com.warkiz.widget.TickType;

/**
 * created by ZhuangGuangquan on  2017/9/6
 */

public class JavaBuildFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.java_build, container, false);
        final LinearLayout content = (LinearLayout) root.findViewById(R.id.java_build);

        TextView textView1 = getTextView();
        textView1.setText("continuous");
        content.addView(textView1);
        //CONTINUOUS
        IndicatorSeekBar indicatorSeekBar1 = new IndicatorSeekBar.Builder(getContext()).setSeekBarType(IndicatorSeekBarType.CONTINUOUS).setMax(88).setMin(2).setProgress(33).setBackgroundTrackSize(2).setBackgroundTrackColor(Color.parseColor("#FF0000")).setProgressTrackSize(4).setProgressTrackColor(Color.parseColor("#FF0000")).setThumbColor(Color.parseColor("#FF0000")).showIndicator(true).setIndicatorType(IndicatorType.ROUNDED_CORNERS).setIndicatorColor(Color.parseColor("#FF0000")).build();
        content.addView(indicatorSeekBar1);

        TextView textView2 = getTextView();
        textView2.setText("continuous_texts_ends");
        content.addView(textView2);
        //CONTINUOUS_TEXTS_ENDS
        IndicatorSeekBar indicatorSeekBar2 = new IndicatorSeekBar.Builder(getContext()).setThumbDrawable(R.mipmap.ic_launcher).setSeekBarType(IndicatorSeekBarType.CONTINUOUS_TEXTS_ENDS).setMax(45).setMin(3).setLeftEndText("LEFT").setRightEndText("RIGHT").setProgress(33).setBackgroundTrackSize(1).setBackgroundTrackColor(Color.parseColor("#FF0000")).setProgressTrackSize(3).setProgressTrackColor(Color.parseColor("#FF0000")).showIndicator(true).setIndicatorType(IndicatorType.SQUARE_CORNERS).setIndicatorColor(Color.parseColor("#FF0000")).build();
        content.addView(indicatorSeekBar2);

        TextView textView3 = getTextView();
        textView3.setText("discrete_ticks");
        content.addView(textView3);
        //DISCRETE_TICKS
        IndicatorSeekBar indicatorSeekBar3 = new IndicatorSeekBar.Builder(getContext()).setMax(200).setMin(0).setSeekBarType(IndicatorSeekBarType.DISCRETE_TICKS).setTickType(TickType.OVAL).setTickColor(Color.parseColor("#0000FF")).setTickSize(8).setTickNum(8).setProgress(67).setBackgroundTrackSize(2).setBackgroundTrackColor(Color.parseColor("#666666")).setProgressTrackSize(3).setProgressTrackColor(Color.parseColor("#0000FF")).showIndicator(true).setIndicatorType(IndicatorType.SQUARE_CORNERS).setIndicatorColor(Color.parseColor("#0000FF")).build();
        content.addView(indicatorSeekBar3);

        TextView textView4 = getTextView();
        textView4.setText("discrete_ticks_texts");
        content.addView(textView4);
        //DISCRETE_TICKS_TEXTS
        IndicatorSeekBar indicatorSeekBar4 = new IndicatorSeekBar.Builder(getContext()).setTextArray(R.array.tick_below_text_length_5).setSeekBarType(IndicatorSeekBarType.DISCRETE_TICKS_TEXTS).setTickType(TickType.REC).setTickColor(Color.parseColor("#FFFFFF")).setTextSize(13).setTextColor(Color.parseColor("#0000FF")).setBackgroundTrackColor(Color.parseColor("#800000FF")).setProgressTrackColor(Color.parseColor("#0000FF")).setProgressTrackSize(3).setBackgroundTrackSize(2).showIndicator(true).setIndicatorType(IndicatorType.ROUNDED_CORNERS).setIndicatorColor(Color.parseColor("#0000FF")).setIndicatorTextSize(20).setIndicatorTextColor(Color.parseColor("#FFFFFF")).build();
        content.addView(indicatorSeekBar4);

        TextView textView5 = getTextView();
        textView5.setText("discrete_ticks_texts_ends");
        content.addView(textView5);
        //DISCRETE_TICKS_TEXTS_ENDS
        IndicatorSeekBar indicatorSeekBar5 = new IndicatorSeekBar.Builder(getContext()).setSeekBarType(IndicatorSeekBarType.DISCRETE_TICKS_TEXTS_ENDS).setLeftEndText("LAST").setIndicatorCustomLayout(R.layout.custom_indicator_blue).setRightEndText("NEXT").setTickDrawable(getResources().getDrawable(R.drawable.thumb_rec_0_corner)).setThumbDrawable(R.drawable.thumb_rec_0_corner_big).setTextColor(Color.parseColor("#0000FF")).showIndicator(true).build();
        content.addView(indicatorSeekBar5);
        return root;
    }

    @NonNull
    private TextView getTextView() {
        TextView textView = new TextView(getContext());
        int padding = dp2px(getContext(), 16);
        textView.setPadding(padding, padding, padding, 0);
        return textView;
    }

    public int dp2px(Context context, float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }

}
