package com.warkiz.indicatorseekbar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.IndicatorSeekBarType;
import com.warkiz.widget.IndicatorType;
import com.warkiz.widget.TickType;

/**
 * created by ZhuangGuangquan on 2017/9/6
 */


public class MainActivity extends AppCompatActivity {

    public static int dp2px(Context context, float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView continuousTv = (TextView) findViewById(R.id.continuous_progress);
        final TextView discreteTv = (TextView) findViewById(R.id.discrete_progress);
        final TextView discreteBlockTv = (TextView) findViewById(R.id.discrete_block);
        final LinearLayout javaBuildContainer = (LinearLayout) findViewById(R.id.java_build);

        IndicatorSeekBar continuous = (IndicatorSeekBar) findViewById(R.id.continuous);
        continuous.setOnSeekChangeListener(new IndicatorSeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(IndicatorSeekBar seekBar, int progress, float progressFloat, boolean fromUserTouch) {
                continuousTv.setText("progress:" + progress + ";  progressFloat:" + progressFloat + ";  fromUser:" + fromUserTouch);
            }

            @Override
            public void onSectionChanged(IndicatorSeekBar seekBar, int thumbPosOnTick, String tickBelowText, boolean fromUserTouch) {
            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar, int thumbPosOnTick) {
            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });

        IndicatorSeekBar discrete = (IndicatorSeekBar) findViewById(R.id.discrete);
        discrete.setOnSeekChangeListener(new IndicatorSeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(IndicatorSeekBar seekBar, int progress, float progressFloat, boolean fromUserTouch) {
                discreteTv.setText("progress: " + progress);
            }

            @Override
            public void onSectionChanged(IndicatorSeekBar seekBar, int thumbPosOnTick, String tickBelowText, boolean fromUserTouch) {
                discreteBlockTv.setText("thumbPosOnTick: " + thumbPosOnTick + " ;  tickBelowText: " + tickBelowText);
            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar, int thumbPosOnTick) {
            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });

        //java_build
        TextView textView1 = getTextView();
        textView1.setText("continuous");
        javaBuildContainer.addView(textView1);
        //CONTINUOUS
        IndicatorSeekBar indicatorSeekBar1 = new IndicatorSeekBar.Builder(this).setSeekBarType(IndicatorSeekBarType.CONTINUOUS).setMax(88).setMin(2).setProgress(33).setBackgroundTrackSize(2).setBackgroundTrackColor(Color.parseColor("#FF0000")).setProgressTrackSize(4).setProgressTrackColor(Color.parseColor("#FF0000")).setThumbColor(Color.parseColor("#FF0000")).showIndicator(true).setIndicatorType(IndicatorType.ROUNDED_CORNERS).setIndicatorColor(Color.parseColor("#FF0000")).build();
        javaBuildContainer.addView(indicatorSeekBar1);

        TextView textView2 = getTextView();
        textView2.setText("continuous_texts_ends");
        javaBuildContainer.addView(textView2);
        //CONTINUOUS_TEXTS_ENDS
        IndicatorSeekBar indicatorSeekBar2 = new IndicatorSeekBar.Builder(this).setThumbDrawable(R.mipmap.ic_launcher).setSeekBarType(IndicatorSeekBarType.CONTINUOUS_TEXTS_ENDS).setMax(45).setMin(3).setLeftEndText("LEFT").setRightEndText("RIGHT").setProgress(33).setBackgroundTrackSize(1).setBackgroundTrackColor(Color.parseColor("#FF0000")).setProgressTrackSize(3).setProgressTrackColor(Color.parseColor("#FF0000")).showIndicator(true).setIndicatorType(IndicatorType.SQUARE_CORNERS).setIndicatorColor(Color.parseColor("#FF0000")).build();
        javaBuildContainer.addView(indicatorSeekBar2);

        TextView textView3 = getTextView();
        textView3.setText("discrete_ticks");
        javaBuildContainer.addView(textView3);
        //DISCRETE_TICKS
        IndicatorSeekBar indicatorSeekBar3 = new IndicatorSeekBar.Builder(this).setMax(200).setMin(0).setSeekBarType(IndicatorSeekBarType.DISCRETE_TICKS).setTickType(TickType.OVAL).setTickColor(Color.parseColor("#0000FF")).setTickSize(8).setTickNum(8).setProgress(67).setBackgroundTrackSize(2).setBackgroundTrackColor(Color.parseColor("#666666")).setProgressTrackSize(3).setProgressTrackColor(Color.parseColor("#0000FF")).showIndicator(true).setIndicatorType(IndicatorType.SQUARE_CORNERS).setIndicatorColor(Color.parseColor("#0000FF")).build();
        javaBuildContainer.addView(indicatorSeekBar3);

        TextView textView4 = getTextView();
        textView4.setText("discrete_ticks_texts");
        javaBuildContainer.addView(textView4);
        //DISCRETE_TICKS_TEXTS
        IndicatorSeekBar indicatorSeekBar4 = new IndicatorSeekBar.Builder(this).setTextArray(R.array.tick_below_text_length_5).setSeekBarType(IndicatorSeekBarType.DISCRETE_TICKS_TEXTS).setTickType(TickType.REC).setTickColor(Color.parseColor("#FFFFFF")).setTextSize(13).setTextColor(Color.parseColor("#0000FF")).setBackgroundTrackColor(Color.parseColor("#800000FF")).setProgressTrackColor(Color.parseColor("#0000FF")).setProgressTrackSize(3).setBackgroundTrackSize(2).showIndicator(true).setIndicatorType(IndicatorType.ROUNDED_CORNERS).setIndicatorColor(Color.parseColor("#0000FF")).setIndicatorTextSize(20).setIndicatorTextColor(Color.parseColor("#FFFFFF")).build();
        javaBuildContainer.addView(indicatorSeekBar4);

        TextView textView5 = getTextView();
        textView5.setText("discrete_ticks_texts_ends");
        javaBuildContainer.addView(textView5);
        //DISCRETE_TICKS_TEXTS_ENDS
        IndicatorSeekBar indicatorSeekBar5 = new IndicatorSeekBar.Builder(this).setSeekBarType(IndicatorSeekBarType.DISCRETE_TICKS_TEXTS_ENDS).setLeftEndText("LAST").setIndicatorCustomLayout(R.layout.custom_indicator_blue).setRightEndText("NEXT").setTickDrawable(getResources().getDrawable(R.drawable.thumb_rec_0_corner)).setThumbDrawable(R.drawable.thumb_rec_0_corner_big).setTextColor(Color.parseColor("#0000FF")).showIndicator(true).build();
        javaBuildContainer.addView(indicatorSeekBar5);

    }

    @NonNull
    private TextView getTextView() {
        TextView textView = new TextView(this);
        int padding = dp2px(this, 16);
        textView.setPadding(padding, padding, padding, 0);
        return textView;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_home) {
            startActivity(new Intent(MainActivity.this, IndicatorSeekBarHomeActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
