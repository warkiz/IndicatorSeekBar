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


public class ContinuousFragment extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.continuous, container, false);
        final TextView textView = (TextView) root.findViewById(R.id.continuous_progress);
        IndicatorSeekBar continuous = (IndicatorSeekBar) root.findViewById(R.id.continuous);
        continuous.setOnSeekChangeListener(new IndicatorSeekBar.OnSeekBarChangeListener()
        {

            @Override
            public void onProgressChanged(IndicatorSeekBar seekBar, int progress, float progressFloat, boolean fromUserTouch)
            {
                textView.setText("progress:" + progress + ";  progressFloat:" + progressFloat + ";  fromUser:" + fromUserTouch);
            }

            @Override
            public void onSectionChanged(IndicatorSeekBar seekBar, int thumbPosOnTick, String tickBelowText, boolean fromUserTouch)
            {
            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar, int thumbPosOnTick)
            {
            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar)
            {

            }
        });
        return root;
    }

}
