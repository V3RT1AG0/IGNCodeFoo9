package com.example.v3rt1ag0.ign;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.viewpager);
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }


    public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter
    {

        private Context mContext;

        public SimpleFragmentPagerAdapter(Context context, FragmentManager fm)
        {
            super(fm);
            mContext = context;
        }

        // This determines the fragment for each tab
        @Override
        public Fragment getItem(int position)
        {
            if (position == 0)
            {
                return new Articles();
            } else if (position == 1)
            {
                return new Videos();
            } else
            {
                return new Videos();
            }
        }

        // This determines the number of tabs
        @Override
        public int getCount()
        {
            return 2;
        }

        // This determines the title for each tab
        @Override
        public CharSequence getPageTitle(int position)
        {
            // Generate title based on item position
            switch (position)
            {
                case 0:
                    return mContext.getString(R.string.articles_fragment_title);
                case 1:
                    return mContext.getString(R.string.videos_fragment_title);
                default:
                    return null;
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
