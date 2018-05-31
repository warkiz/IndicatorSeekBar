package com.warkiz.indicatorseekbar.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.warkiz.indicatorseekbar.R;
import com.warkiz.widget.IndicatorSeekBar;

/**
 * created by ZhuangGuangquan on  2017/9/6
 */

public class DiscreteFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.discrete, container, false);
        final TextView progressTv = (TextView) root.findViewById(R.id.discrete_progress);
        final TextView positionTv = (TextView) root.findViewById(R.id.discrete_pos);
        final TextView textTv = (TextView) root.findViewById(R.id.discrete_text);
        IndicatorSeekBar discrete = (IndicatorSeekBar) root.findViewById(R.id.discrete);

        discrete.setOnSeekChangeListener(new IndicatorSeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(IndicatorSeekBar seekBar, int progress, float progressFloat, boolean fromUserTouch) {
                progressTv.setText("progress: " + progress);
            }

            @Override
            public void onSectionChanged(IndicatorSeekBar seekBar, int thumbPosOnTick, String tickBelowText, boolean fromUserTouch) {
                positionTv.setText("thumbPosOnTick: " + thumbPosOnTick);
                textTv.setText("tickBelowText: " + tickBelowText);
            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar, int thumbPosOnTick) {
            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });
        return root;
    }

}
