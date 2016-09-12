package com.wyf.daike.Order;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.wyf.daike.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 代课中心，查看自己的代课记录
 */
public class OrderFragment extends Fragment{
    private OrderContract.Presenter presenter;
    private Context mContext;

    private ViewPager viewPager;
    private TabLayout tabLayout;
    public List<Fragment> tabList;
    private ContentPageAdapter pagerAdapter;
    private FragmentManager fm;
    private List<String> tabIndicators;
    private android.support.v7.widget.Toolbar toolbar ;


    public OrderFragment() {
        // Required empty public constructor
    }

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        initView(view);
        //initToggle();
        fm = getChildFragmentManager();

        initViewPager();
        initTabLayout();

      //  if(tabList.get(0)!=null)
            fm.beginTransaction().show(tabList.get(0)).commit();
        return view;
    }

    private void initToggle() {
        DrawerLayout drawer = (DrawerLayout)getActivity().findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (getActivity(), drawer, toolbar,  R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void initView(View view) {

        tabLayout = (TabLayout) view.findViewById(R.id.tabLayoutOrder);
        viewPager = (ViewPager) view.findViewById(R.id.vpOrder);
        toolbar = (android.support.v7.widget.Toolbar) view.findViewById(R.id.toolbarOther);
        toolbar.setTitle("代课记录");
        toolbar.setTitleTextColor(Color.WHITE);

    }

    private void initViewPager() {
        tabList = new ArrayList<>();
        tabList.add(Tab1Fragment.newInstance());
        tabList.add(Tab2Fragment.newInstance());
        pagerAdapter = new ContentPageAdapter(fm);
        pagerAdapter.notifyDataSetChanged();
        viewPager.setAdapter(pagerAdapter);
    }

    private void initTabLayout() {

        tabIndicators = new ArrayList();
        tabIndicators.add("我代课");
        tabIndicators.add("别人给我代课");
        tabLayout.addTab(tabLayout.newTab().setText("我代课"));
        tabLayout.addTab(tabLayout.newTab().setText("别人给我代课"));

        //设置TabLayout左右滑动
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabTextColors(ContextCompat.getColor(mContext, R.color.gray), ContextCompat.getColor(mContext, R.color.colorPrimary));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
        tabLayout.setTabTextColors(ContextCompat.getColor(mContext, R.color.gray), ContextCompat.getColor(mContext, R.color.gray_deep));

        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * ViewPager适配器
     */
    class  ContentPageAdapter extends FragmentPagerAdapter
    {

        public ContentPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return tabList.get(position) ;
        }

        @Override
        public int getCount() {
            return tabList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabIndicators.get(position);
        }
    }



}
