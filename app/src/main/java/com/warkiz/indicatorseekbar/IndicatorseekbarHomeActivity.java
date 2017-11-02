package com.warkiz.indicatorseekbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
/**
 * created by ZhuangGuangquan on 2017/11/3
 */

public class IndicatorSeekBarHomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView webView = new WebView(this);
        webView.loadUrl("https://github.com/warkiz/IndicatorSeekBar");
        setContentView(webView);
    }
}
