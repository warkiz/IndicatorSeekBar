package com.warkiz.indicatorseekbar.donation;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        initCreate();
    }

    protected void initCreate() {

    }

    public abstract int getLayoutResId();


}
