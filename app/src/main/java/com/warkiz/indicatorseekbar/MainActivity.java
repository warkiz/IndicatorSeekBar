package com.warkiz.indicatorseekbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.warkiz.indicatorseekbar.fragment.ContinuousFragment;
import com.warkiz.indicatorseekbar.fragment.CustomFragment;
import com.warkiz.indicatorseekbar.fragment.DiscreteFragment;
import com.warkiz.indicatorseekbar.fragment.IndicatorFragment;
import com.warkiz.indicatorseekbar.fragment.JavaBuildFragment;

/**
 * created by ZhuangGuangquan on 2017/9/6
 */


public class MainActivity extends AppCompatActivity
{

    private String[] mType = new String[]{"continuous", "discrete", "custom", "java", "indicator"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews()
    {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        for (String s : mType)
        {
            TextView textView = new TextView(this);
            textView.setText(s);
            tabLayout.newTab().setCustomView(textView);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        if (item.getItemId() == R.id.action_home)
        {
            startActivity(new Intent(MainActivity.this, IndicatorHomeActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class PagerAdapter extends FragmentPagerAdapter
    {

        public PagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            if (position == 0)
            {
                return new ContinuousFragment();
            } else if (position == 1)
            {
                return new DiscreteFragment();
            } else if (position == 2)
            {
                return new CustomFragment();
            } else if (position == 3)
            {
                return new JavaBuildFragment();
            }
            return new IndicatorFragment();
        }

        @Override
        public int getCount()
        {
            return mType.length;
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return mType[position];
        }
    }

}
