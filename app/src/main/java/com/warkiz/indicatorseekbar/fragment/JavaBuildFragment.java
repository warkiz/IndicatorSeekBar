package com.warkiz.indicatorseekbar.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import androidx.annotation.NonNull;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.warkiz.indicatorseekbar.R;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.IndicatorStayLayout;
import com.warkiz.widget.IndicatorType;
import com.warkiz.widget.TickMarkType;

/**
 * created by zhuangguangquan on  2017/9/6
 */

public class JavaBuildFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.java_build;
    }

    @Override
    protected void initView(View root) {
        super.initView(root);
        final LinearLayout content = (LinearLayout) root.findViewById(R.id.java_build);

        TextView textView1 = getTextView();
        textView1.setText("1. continuous");
        content.addView(textView1);
        //CONTINUOUS
        IndicatorSeekBar continuous = IndicatorSeekBar
                .with(getContext())
                .max(200)
                .min(10)
                .progress(33)
                .indicatorColor(getResources().getColor(R.color.color_blue, null))
                .indicatorTextColor(Color.parseColor("#ffffff"))
                .showIndicatorType(IndicatorType.CIRCULAR_BUBBLE)
                .thumbColorStateList(getResources().getColorStateList(R.color.selector_thumb_color, null))
                .thumbSize(14)
                .trackProgressColor(getResources().getColor(R.color.colorAccent, null))
                .trackProgressSize(4)
                .trackBackgroundColor(getResources().getColor(R.color.color_gray, null))
                .trackBackgroundSize(2)
                .showThumbText(true)
                .thumbTextColor(getResources().getColor(R.color.color_gray, null))
                .build();

        content.addView(continuous);

        TextView textView2 = getTextView();
        textView2.setText("2. continuous_texts_ends");
        content.addView(textView2);
        //CONTINUOUS_TEXTS_ENDS
        IndicatorSeekBar continuous2TickTexts = IndicatorSeekBar
                .with(getContext())
                .max(100)
                .min(10)
                .progress(33)
                .tickCount(2)
                .showTickMarksType(TickMarkType.NONE)
                .showTickTexts(true)
                .indicatorColor(getResources().getColor(R.color.color_gray, null))
                .indicatorTextColor(Color.parseColor("#ffffff"))
                .showIndicatorType(IndicatorType.RECTANGLE)
                .thumbDrawable(getResources().getDrawable(R.drawable.selector_thumb_drawable, null))
                .thumbSize(18)
                .trackProgressColor(getResources().getColor(R.color.colorAccent, null))
                .trackProgressSize(4)
                .trackBackgroundColor(getResources().getColor(R.color.color_gray, null))
                .trackBackgroundSize(2)
                .build();

        IndicatorStayLayout continuousStayLayout = new IndicatorStayLayout(getContext());
        continuousStayLayout.attachTo(continuous2TickTexts);
        content.addView(continuousStayLayout);

        TextView textView22 = getTextView();
        textView22.setText("3. continuous_texts_ends_custom_ripple_thumb");
        content.addView(textView22);
        //CONTINUOUS_TEXTS_ENDS
        IndicatorSeekBar continuous_texts_ends_custom_thumb = IndicatorSeekBar
                .with(getContext())
                .max(100)
                .min(30)
                .progress(33)
                .tickCount(2)
                .showTickMarksType(TickMarkType.NONE)
                .showTickTexts(true)
                .indicatorColor(getResources().getColor(R.color.color_blue, null))
                .indicatorTextColor(Color.parseColor("#ffffff"))
                .showIndicatorType(IndicatorType.CIRCULAR_BUBBLE)
                .thumbDrawable(getResources().getDrawable(R./*mipmap.ic_launcher*/drawable.selector_thumb_ripple_drawable, null))
                .thumbSize(26)
                .trackProgressColor(getResources().getColor(R.color.colorAccent, null))
                .trackProgressSize(4)
                .trackBackgroundColor(getResources().getColor(R.color.color_gray, null))
                .trackBackgroundSize(2)
                .build();
        content.addView(continuous_texts_ends_custom_thumb);

        TextView textView3 = getTextView();
        textView3.setText("4. continuous_texts_ends_custom");
        content.addView(textView3);
        IndicatorSeekBar continuous2TickTexts1 = IndicatorSeekBar
                .with(getContext())
                .max(90)
                .min(10)
                .progress(33)
                .tickCount(2)
                .showTickMarksType(TickMarkType.NONE)
                .showTickTexts(true)
                .tickTextsArray(R.array.last_next_length_2)
                .indicatorColor(getResources().getColor(R.color.color_blue))
                .indicatorTextColor(Color.parseColor("#ffffff"))
                .showIndicatorType(IndicatorType.CIRCULAR_BUBBLE)
                .thumbColor(Color.parseColor("#ff0000"))
                .thumbSize(14)
                .trackProgressColor(getResources().getColor(R.color.colorAccent, null))
                .trackProgressSize(4)
                .trackBackgroundColor(getResources().getColor(R.color.color_gray, null))
                .trackBackgroundSize(2)
                .build();
        content.addView(continuous2TickTexts1);

        TextView textView4 = getTextView();
        textView4.setText("5. discrete_ticks");
        content.addView(textView4);
        //DISCRETE_TICKS
        IndicatorSeekBar discrete_ticks = IndicatorSeekBar
                .with(getContext())
                .max(50)
                .min(10)
                .progress(33)
                .tickCount(7)
                .tickMarksSize(15)
                .tickMarksDrawable(getResources().getDrawable(R.drawable.selector_tick_marks_drawable, null))
                .indicatorColor(getResources().getColor(R.color.color_blue))
                .indicatorTextColor(Color.parseColor("#ffffff"))
                .showIndicatorType(IndicatorType.ROUNDED_RECTANGLE)
                .thumbColor(Color.parseColor("#ff0000"))
                .thumbSize(14)
                .trackProgressColor(getResources().getColor(R.color.colorAccent, null))
                .trackProgressSize(4)
                .trackBackgroundColor(getResources().getColor(R.color.color_gray, null))
                .trackBackgroundSize(2)
                .build();
        IndicatorStayLayout indicatorStayLayout = new IndicatorStayLayout(getContext());
        indicatorStayLayout.attachTo(discrete_ticks);
        content.addView(indicatorStayLayout);

        TextView textView5 = getTextView();
        textView5.setText("6. discrete_ticks_texts");
        content.addView(textView5);
        //DISCRETE_TICKS_TEXTS
        IndicatorSeekBar discrete_ticks_texts = IndicatorSeekBar
                .with(getContext())
                .max(110)
                .min(10)
                .progress(53)
                .tickCount(7)
                .showTickMarksType(TickMarkType.OVAL)
                .tickMarksColor(getResources().getColor(R.color.color_blue, null))
                .tickMarksSize(13)//dp
                .showTickTexts(true)
                .tickTextsColor(getResources().getColor(R.color.color_pink, null))
                .tickTextsSize(13)//sp
                .tickTextsTypeFace(Typeface.MONOSPACE)
                .showIndicatorType(IndicatorType.ROUNDED_RECTANGLE)
                .indicatorColor(getResources().getColor(R.color.color_blue, null))
                .indicatorTextColor(Color.parseColor("#ffffff"))
                .indicatorTextSize(13)//sp
                .thumbColor(getResources().getColor(R.color.colorAccent, null))
                .thumbSize(14)//dp
                .trackProgressColor(getResources().getColor(R.color.colorAccent, null))
                .trackProgressSize(4)//dp
                .trackBackgroundColor(getResources().getColor(R.color.color_gray, null))
                .trackBackgroundSize(2)//dp
                .build();
        content.addView(discrete_ticks_texts);

        TextView textView6 = getTextView();
        textView6.setText("7. discrete_ticks_texts_custom");
        content.addView(textView6);
        String[] array = {"A", "B", "C", "D", "E", "F", "G"};
        IndicatorSeekBar discrete_ticks_texts1 = IndicatorSeekBar
                .with(getContext())
                .max(200)
                .min(10)
                .progress(83)
                .tickCount(7)
                .showTickMarksType(TickMarkType.SQUARE)
                .tickTextsArray(array)
                .showTickTexts(true)
                .tickTextsColorStateList(getResources().getColorStateList(R.color.selector_tick_texts_3_color, null))
                .indicatorColor(getResources().getColor(R.color.color_blue, null))
                .indicatorTextColor(Color.parseColor("#ffffff"))
                .showIndicatorType(IndicatorType.RECTANGLE)
                .thumbColor(Color.parseColor("#ff0000"))
                .thumbSize(14)
                .trackProgressColor(getResources().getColor(R.color.colorAccent, null))
                .trackProgressSize(4)
                .trackBackgroundColor(getResources().getColor(R.color.color_gray, null))
                .trackBackgroundSize(2)
                .build();
        content.addView(discrete_ticks_texts1);

        TextView textView7 = getTextView();
        textView7.setText("8. discrete_ticks_texts_ends");
        content.addView(textView7);

        String[] array_ends = {"A", "", "", "", "", "", "G"};
        IndicatorSeekBar discrete_ticks_texts_ends = IndicatorSeekBar
                .with(getContext())
                .max(100)
                .min(10)
                .progress(83)
                .tickCount(7)
                .showTickMarksType(TickMarkType.OVAL)
                .tickMarksColor(getResources().getColorStateList(R.color.selector_tick_marks_color, null))
                .tickTextsArray(array_ends)
                .showTickTexts(true)
                .showIndicatorType(IndicatorType.CIRCULAR_BUBBLE)
                .tickTextsColorStateList(getResources().getColorStateList(R.color.selector_tick_texts_3_color, null))
                .indicatorColor(getResources().getColor(R.color.color_blue, null))
                .indicatorTextColor(Color.parseColor("#ffffff"))
                .thumbColor(Color.parseColor("#ff0000"))
                .thumbSize(14)
                .trackProgressColor(getResources().getColor(R.color.color_blue, null))
                .trackProgressSize(4)
                .trackBackgroundColor(getResources().getColor(R.color.color_pink, null))
                .trackBackgroundSize(2)
                .build();

        IndicatorStayLayout stayLayout = new IndicatorStayLayout(getContext());
        stayLayout.attachTo(discrete_ticks_texts_ends);
        content.addView(stayLayout);

        TextView textView8 = getTextView();
        content.addView(textView8);
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
