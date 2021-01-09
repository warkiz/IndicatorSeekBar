package com.warkiz.indicatorseekbar.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.warkiz.indicatorseekbar.R;

/**
 * created by zhuangguangquan on  2018/6/3
 */

public abstract class BaseFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(getLayoutId(), container, false);
        TextView textView = root.findViewById(R.id.source_code);
        if (textView != null) {
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/warkiz/IndicatorSeekBar")));
                }
            });
        }
        initView(root);
        return root;
    }

    protected abstract int getLayoutId();

    protected void initView(View root) {
    }

}
