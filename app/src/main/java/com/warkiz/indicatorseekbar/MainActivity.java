package com.warkiz.indicatorseekbar;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.warkiz.indicatorseekbar.fragment.ContinuousFragment;
import com.warkiz.indicatorseekbar.fragment.CustomFragment;
import com.warkiz.indicatorseekbar.fragment.DiscreteFragment;
import com.warkiz.indicatorseekbar.fragment.IndicatorFragment;
import com.warkiz.indicatorseekbar.fragment.JavaBuildFragment;

/**
 * created by zhuangguangquan on 2017/9/6
 */

public class MainActivity extends AppCompatActivity {

    private static String[] sType = new String[]{"continuous", "discrete", "custom", "java", "indicator"};
    private ContinuousFragment mContinuousFragment;
    private DiscreteFragment mDiscreteFragment;
    private CustomFragment mCustomFragment;
    private Fragment mJavaBuildFragment;
    private IndicatorFragment mIndicatorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        for (String s : sType) {
            TextView textView = new TextView(this);
            textView.setText(s);
            tabLayout.newTab().setCustomView(textView);
        }

    }

    private class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                if (mContinuousFragment == null) {
                    mContinuousFragment = new ContinuousFragment();
                }
                return mContinuousFragment;
            } else if (position == 1) {
                if (mDiscreteFragment == null) {
                    mDiscreteFragment = new DiscreteFragment();
                }
                return mDiscreteFragment;
            } else if (position == 2) {
                if (mCustomFragment == null) {
                    mCustomFragment = new CustomFragment();
                }
                return mCustomFragment;
            } else if (position == 3) {
                if (mJavaBuildFragment == null) {
                    mJavaBuildFragment = new JavaBuildFragment();
                }
                return mJavaBuildFragment;
            }

            if (mIndicatorFragment == null) {
                mIndicatorFragment = new IndicatorFragment();
            }
            return mIndicatorFragment;
        }

        @Override
        public int getCount() {
            return sType.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return sType[position];
        }
    }

}
