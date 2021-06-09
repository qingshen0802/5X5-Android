package com.zenlabs.z5x5.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;


import com.zenlabs.z5x5.Fragment.Intro1Fragment;
import com.zenlabs.z5x5.Fragment.Intro2Fragment;
import com.zenlabs.z5x5.Fragment.Intro3Fragment;
import com.zenlabs.z5x5.Fragment.Intro4Fragment;
import com.zenlabs.z5x5.Fragment.Intro5Fragment;
import com.zenlabs.z5x5.Fragment.Intro6Fragment;
import com.zenlabs.z5x5.Fragment.Intro7Fragment;
import com.zenlabs.z5x5.R;

import me.relex.circleindicator.CircleIndicator;

public class IntroActivity extends FragmentActivity implements ViewPager.OnPageChangeListener {

    private ViewPager fragmentPager;
    private CircleIndicator circleIndicator;
    private MyFragmentPagerAdapter pagerAdapter;
    private Intro3Fragment intro3Fragment;
    private Intro4Fragment intro4Fragment;
    private Intro7Fragment intro7Fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        intro3Fragment = new Intro3Fragment().newInstance();
        intro4Fragment = new Intro4Fragment().newInstance();
        intro7Fragment = new Intro7Fragment().newInstance();

        fragmentPager = (ViewPager) this.findViewById(R.id.intro_pager);
        circleIndicator = (CircleIndicator) this.findViewById(R.id.dot_indicator);

        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());

        fragmentPager.setAdapter(pagerAdapter);
        circleIndicator.setViewPager(fragmentPager);
        fragmentPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                page.setTranslationX(page.getWidth() * -position);

                if(position <= -1.0F || position >= 1.0F) {
                    page.setAlpha(0.0F);
                } else if( position == 0.0F ) {
                    page.setAlpha(1.0F);
                } else {
                    // position is between -1.0F & 0.0F OR 0.0F & 1.0F
                    page.setAlpha(1.0F - Math.abs(position));
                }
            }
        });

        fragmentPager.addOnPageChangeListener(this);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 2) {
            intro3Fragment.onPageSelected();
            intro4Fragment.onPageUnSelected();
            intro7Fragment.onPageUnSelected();
        } else if (position == 3) {
            intro3Fragment.onPageUnSelected();
            intro4Fragment.onPageSelected();
            intro7Fragment.onPageUnSelected();
        } else if (position == 6){
            intro3Fragment.onPageUnSelected();
            intro4Fragment.onPageUnSelected();
            intro7Fragment.onPageSelected();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return Intro1Fragment.newInstance();
                case 1:
                    return Intro2Fragment.newInstance();
                case 2:
                    return intro3Fragment;
                case 3:
                    return intro4Fragment;
                case 4:
                    return Intro5Fragment.newInstance();
                case 5:
                    return Intro6Fragment.newInstance();
                case 6:
                    return intro7Fragment;
            }
            return null;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }
}
