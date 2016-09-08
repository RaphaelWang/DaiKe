package com.wyf.daike.Adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.VelocityTrackerCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/8/14.
 */
public class WelcomeViewPageAdapter extends PagerAdapter {

    private ImageView img[];

    public WelcomeViewPageAdapter(ImageView img[])
    {
        this.img = img;
    }
    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(img[position]);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
       container.addView(img[position]);
        return  img[position];
    }
}
