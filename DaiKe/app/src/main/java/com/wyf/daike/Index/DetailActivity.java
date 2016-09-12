package com.wyf.daike.Index;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wyf.daike.Bean.DaiKeOrder;
import com.wyf.daike.R;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private ImageView imageUser;
    private TextView textSubject;
    private TextView textClassroom;
    private TextView textPrice;
    private TextView textTakeClassTime;
    private TextView textFaBuTime;
    private TextView textTel;
    private Button btnSubmit;
    private FloatingActionButton fab;
    private DaiKeOrder card;
    private NestedScrollView nestedScrollView;
    private CollapsingToolbarLayout toolbarLayout;
    private ActionBar actionBar;
    private  String id;
    private static final String TAG = "DetailActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();


        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
         id = intent.getStringExtra("id");
        loadDetail(id);
        Log.d("wyf", "onCreate: 数据库id"+id);
    }

    public void loadDetail(String id)
    {
        BmobQuery<DaiKeOrder> bmobQuery = new BmobQuery<DaiKeOrder>();
        bmobQuery.getObject(id, new QueryListener<DaiKeOrder>() {
            @Override
            public void done(DaiKeOrder object, BmobException e) {
                if (e == null) {
                    //Log.d("wyf", "done: " + "详情页获取成功" + object.getCardClassroom() + object.getObjectId() + object.getCardTitle());
                    card = object;
                   // Log.d("wyf", "done: "+card.getCardTitle());
                    fillData(card);
                } else {
                    Snackbar.make(nestedScrollView,"加载出错，请重试！",Snackbar.LENGTH_SHORT).show();
                    Log.d("wyf", "done: " + e.getMessage());
                }
            }
        });
    }

    private void fillData(DaiKeOrder card) {
        textSubject.setText(card.getoSubject());
        textClassroom.setText(card.getoClassroom());
        textPrice.setText(card.getoPrice());
        actionBar.setTitle(card.getoTitle());
        textTakeClassTime.setText(card.getoSchoolTime());
        textFaBuTime.setText(card.getCreatedAt());
        toolbarLayout.setTitle(card.getoTitle());

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        imageUser = (ImageView) findViewById(R.id.imageUser);
        textSubject = (TextView) findViewById(R.id.textSubject);
        textClassroom = (TextView) findViewById(R.id.textClassroom);
        textPrice = (TextView) findViewById(R.id.textPrice);
        textTakeClassTime = (TextView) findViewById(R.id.textTakeClassTime);
        textFaBuTime = (TextView) findViewById(R.id.textFaBuTime);
        textTel = (TextView) findViewById(R.id.textTel);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nested);
        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        btnSubmit.setOnClickListener(this);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:
                helpOthersDaiKe();

                break;
            case R.id.fab:
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
        }
    }

    private void helpOthersDaiKe() {
        DaiKeOrder order = new DaiKeOrder();
        order.setReceiverAccount(BmobUser.getCurrentUser().getUsername());
        order.setoOrderState(1);
        order.update(id, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                Toast.makeText(DetailActivity.this, "代课成功", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
            return true;
        }
        return false;
    }
}
