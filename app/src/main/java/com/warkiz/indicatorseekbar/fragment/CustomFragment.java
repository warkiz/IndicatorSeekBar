package com.warkiz.indicatorseekbar.fragment;

import android.view.View;
import android.widget.TextView;

import com.warkiz.indicatorseekbar.R;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;

/**
 * created by zhuangguangquan on  2017/9/6
 */

public class CustomFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.custom;
    }

    @Override
    protected void initView(View root) {
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
        final TextView thumb_position = root.findViewById(R.id.thumb_position);
        thumb_position.setText("thumb_position: ");
        final TextView tick_text = root.findViewById(R.id.tick_text);
        tick_text.setText("tick_text: ");
        listenerSeekBar.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {
                if (seekParams.fromUser) {
                    states.setText("states: onSeeking");
                } else {
                    states.setText("states: onStop");
                }
                progress.setText("progress: " + seekParams.progress);
                progress_float.setText("progress_float: " + seekParams.progressFloat);
                from_user.setText("from_user: " + seekParams.fromUser);
                thumb_position.setText("thumb_position: " + seekParams.thumbPosition);
                tick_text.setText("tick_text: " + seekParams.tickText);
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
