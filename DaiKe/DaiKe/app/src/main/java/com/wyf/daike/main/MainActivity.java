package com.wyf.daike.main;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TabHost;

import com.wyf.daike.AddDaiKe.AddDaiKeFragment;
import com.wyf.daike.CityPicker.CityPickerActivity;
import com.wyf.daike.Index.DaiKeListFragment;
import com.wyf.daike.Login.LoginActivity;
import com.wyf.daike.MyInfo.MyInfoActivity;
import com.wyf.daike.Order.OrderContract;
import com.wyf.daike.Order.OrderFragment;
import com.wyf.daike.R;
import com.wyf.daike.Util.CircleImageView;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private Toolbar toolbar;
    private android.support.v7.app.ActionBar actionBar;
    private FrameLayout mainFrameLayout;
    private   FloatingActionButton fab;
    private NavigationView nav_view;
    private DrawerLayout drawer_layout;
    private CircleImageView circleImageView;
    private android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
    private List<Fragment> fragmentsList;
    public enum FRAGMENTENUM {DaiKeListFragment,OrderFragment,AddDaiKeFragment}
    private AddDaiKeFragment addDaiKeFragment;
    private DaiKeListFragment daiKeListFragment;
    private OrderFragment orderFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initActionBar();
        mkdir();
        currentFragment(FRAGMENTENUM.DaiKeListFragment);
    }

    /**
     * 设置ActionBar
     */
    private void initActionBar() {
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
    }

    /**
     * 初始化布局
     */
    private void initView() {
      initToggle();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        circleImageView  = (CircleImageView) headerView.findViewById(R.id.imageTouXiang);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        nav_view = (NavigationView) findViewById(R.id.nav_view);
        nav_view.setOnClickListener(this);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer_layout.setOnClickListener(this);
        circleImageView.setOnClickListener(this);
    }

    private void initToggle() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    /**
     * 创建应用文件夹
     */
    private void mkdir() {
       String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/DaiKe";
        File file = new File(path);
        if(!file.exists())
        {
            file.mkdir();
        }
    }

    /**
     * 显示当前正在展示的Fragment
     * @param frag 需要打开的fragment
     */

    public void currentFragment(FRAGMENTENUM frag)
    {
        hideFragment();
       switch (frag)
       {
           case AddDaiKeFragment:
               if(null==addDaiKeFragment)
               {
                   addDaiKeFragment = AddDaiKeFragment.newInstance();
               }
               if (!addDaiKeFragment.isAdded())
                    fm.beginTransaction().add(R.id.mainFrameLayout,addDaiKeFragment,"AddDaiKeFragment").addToBackStack("AddDaiKeFragment").commit();
               fm.beginTransaction().show(addDaiKeFragment).commit();
               actionBar.setTitle("增加信息");
               actionBar.setDisplayHomeAsUpEnabled(true);
               actionBar.setDisplayShowHomeEnabled(true);


               fab.setVisibility(View.GONE);

               break;
           case OrderFragment:
               if(null == orderFragment)
               {
                   orderFragment = OrderFragment.newInstance();
               }
               if(!orderFragment.isAdded())
                    fm.beginTransaction().add(R.id.mainFrameLayout,orderFragment,"OrderFragment").commit();
               fm.beginTransaction().show(orderFragment).commit();
               actionBar.setTitle("订单中心");
               fab.setVisibility(View.GONE);
               break;
           case DaiKeListFragment:
               actionBar.setDisplayHomeAsUpEnabled(false);
               actionBar.setDisplayShowHomeEnabled(false);
//                initToggle();

               if(null == daiKeListFragment)
               {
                   daiKeListFragment = DaiKeListFragment.newInstance();
               }
               if(!daiKeListFragment.isAdded())
                    fm.beginTransaction().add(R.id.mainFrameLayout,daiKeListFragment,"DaiKeListFragment").commit();
               fm.beginTransaction().show(daiKeListFragment).commit();
               actionBar.setTitle("代课");
               fab.setVisibility(View.VISIBLE);
               break;
       }
    }

    private void hideFragment( ) {

        fragmentsList = fm.getFragments();
        if(null != fragmentsList)
        {
            for(Fragment f:fragmentsList)
            {
                if(f.isVisible())
                {
                    fm.beginTransaction().hide(f).commit();
                }
            }
        }
    }


    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Log.d("wyf", "onBackPressed:Fragemt数量"+getSupportFragmentManager().getFragments().size());
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == android.R.id.home)
        {
            currentFragment(FRAGMENTENUM.DaiKeListFragment);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            currentFragment(FRAGMENTENUM.DaiKeListFragment);
        } else if (id == R.id.nav_gallery) {
            currentFragment(FRAGMENTENUM.OrderFragment);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
            startActivity(new Intent(this, CityPickerActivity.class));

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                BmobUser myUser =  BmobUser.getCurrentUser();
                if (myUser== null) {
                    startActivity(new Intent(this, LoginActivity.class));
                    Snackbar.make(v,"请先登录",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                currentFragment(FRAGMENTENUM.AddDaiKeFragment);
                break;
            case R.id.imageTouXiang:
                startActivity(new Intent(this, MyInfoActivity.class));
                break;
        }
    }

    public void  setFloatingActionButton(boolean state)
    {
        if(state)
        {
            fab.setVisibility(View.VISIBLE);
        }
        else {
            fab.setVisibility(View.GONE);
        }

    }

}
