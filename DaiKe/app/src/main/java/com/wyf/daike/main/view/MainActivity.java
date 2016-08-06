package com.wyf.daike.main.view;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
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
import com.wyf.daike.Index.DaiKeListFragment;
import com.wyf.daike.Login.LoginActivity;
import com.wyf.daike.MyInfo.MyInfoActivity;
import com.wyf.daike.R;
import com.wyf.daike.Util.CircleImageView;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private Toolbar toolbar;
    private FrameLayout mainFrameLayout;
    private   FloatingActionButton fab;
    private NavigationView nav_view;
    private DrawerLayout drawer_layout;
    private CircleImageView circleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        initView();
        switchDaiKeList();
    }


    /**
     * 初始化布局
     *
     */
    private void initView() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);






        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        circleImageView  = (CircleImageView) headerView.findViewById(R.id.imageTouXiang);
       // toolbar.setOnClickListener(this);
        //mainFrameLayout = (FrameLayout) findViewById(R.id.mainFrameLayout);
      //  mainFrameLayout.setOnClickListener(this);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        nav_view = (NavigationView) findViewById(R.id.nav_view);
        nav_view.setOnClickListener(this);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer_layout.setOnClickListener(this);
        circleImageView.setOnClickListener(this);
    }


    /***
     *
     * 打开主页的fragment
     */

    public void switchDaiKeList() {
          getSupportFragmentManager().beginTransaction().replace(R.id.mainFrameLayout,new DaiKeListFragment()).commit();
//          getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

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
                }


                getSupportFragmentManager().beginTransaction().replace(R.id.mainFrameLayout,
                        new AddDaiKeFragment()).addToBackStack(null).commit();


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
