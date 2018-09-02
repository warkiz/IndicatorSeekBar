package com.warkiz.indicatorseekbar.fragment;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.warkiz.indicatorseekbar.R;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;

/**
 * created by zhuangguangquan on  2017/9/6
 */

public class ContinuousFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.continuous;
    }

    @Override
    protected void initView(View root) {
        //custom indicator text
        IndicatorSeekBar percent_indicator = root.findViewById(R.id.percent_indicator);
        percent_indicator.setIndicatorTextFormat("${PROGRESS} %");

        //scale
        IndicatorSeekBar scale = root.findViewById(R.id.scale);
        scale.setDecimalScale(4);
        View contentView = scale.getIndicator().getContentView();

        //thumb_drawable
        IndicatorSeekBar thumb_drawable = root.findViewById(R.id.thumb_drawable);
        thumb_drawable.setThumbDrawable(getResources().getDrawable(R.mipmap.ic_launcher));

        //set listener
        IndicatorSeekBar listenerSeekBar = root.findViewById(R.id.listener);
        final TextView states = root.findViewById(R.id.states);
        states.setText("states: ");
        final TextView progress = root.findViewById(R.id.progress);
        progress.setText("progress: " + listenerSeekBar.getProgress());
        final TextView progress_float = root.findViewById(R.id.progress_float);
        progress_float.setText("progress_float: " + listenerSeekBar.getProgressFloat());
        final TextView from_user = root.findViewById(R.id.from_user);
        from_user.setText("from_user: ");
        listenerSeekBar.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {
                states.setText("states: onSeeking");
                progress.setText("progress: " + seekParams.progress);
                progress_float.setText("progress_float: " + seekParams.progressFloat);
                from_user.setText("from_user: " + seekParams.fromUser);
            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {
                states.setText("states: onStart");
                progress.setText("progress: " + seekBar.getProgress());
                progress_float.setText("progress_float: " + seekBar.getProgressFloat());
                from_user.setText("from_user: true");
            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {
                states.setText("states: onStop");
                progress.setText("progress: " + seekBar.getProgress());
                progress_float.setText("progress_float: " + seekBar.getProgressFloat());
                from_user.setText("from_user: false");
            }
        });
    }
}
