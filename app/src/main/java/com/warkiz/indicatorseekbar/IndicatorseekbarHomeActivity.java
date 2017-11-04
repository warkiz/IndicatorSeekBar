package com.warkiz.indicatorseekbar;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

/**
 * created by ZhuangGuangquan on 2017/11/3
 */

public class IndicatorSeekBarHomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.web_home_page);
        final TextView remind = (TextView) findViewById(R.id.text);
        final WebView webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl("https://github.com/warkiz/IndicatorSeekBar");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                remind.setVisibility(View.VISIBLE);
                webView.setVisibility(View.GONE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                remind.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
            }
        });
    }

}
