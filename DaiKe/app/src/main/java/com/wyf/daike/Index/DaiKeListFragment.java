package com.wyf.daike.Index;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.wyf.daike.Adapter.IndexAdapter;
import com.wyf.daike.Bean.DaiKeOrder;
import com.wyf.daike.R;
import com.wyf.daike.global.Config;
import com.wyf.daike.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import static android.view.MotionEvent.ACTION_MOVE;

/**
 * A simple {@link Fragment} subclass.
 *
 *  这是首页
 *
 */
public class DaiKeListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,IndexContract.View{

    Context mContext;

    IndexAdapter indexAdapter;
    public RecyclerView mRecyclerView;
    public SwipeRefreshLayout mSwipeRefreshLayout;
    List<DaiKeOrder> cardData;
    private IndexContract.Presenter presenter;
    private   LinearLayoutManager manager;
    private static  int cardTotal=0;
    private boolean loadContentEmpty =true;
    private   MainActivity mainActivity;
    private android.support.v7.widget.Toolbar toolbar;



    public  static DaiKeListFragment newInstance()
    {
        return new DaiKeListFragment();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
        mainActivity = (MainActivity)context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dai_ke_list, container, false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.mRecyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.mSwipeRefreshLayout);

        new IndexPresenter(this);

        init();

        initToolbar(view);
        initToggle();
        showDialog();
        onRefresh();
        return view;
    }

    private void initToolbar(View view) {
        toolbar = (android.support.v7.widget.Toolbar) view.findViewById(R.id.toolbarIndex);
        toolbar.setTitle("首页");


    }

    private void init() {

        mainActivity.setFloatingActionButton(true);
        mainActivity.setTitle("代课");

        //设置SwipRefreshLayout
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setProgressViewOffset(false, 100, 300);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        //设置RecyclerView
        manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnScrollListener(onScrollListener);


        cardData = new ArrayList<DaiKeOrder>();
        indexAdapter =  new IndexAdapter(getActivity().getApplicationContext(),cardData);
        indexAdapter.addItemOnClickListener(onItemClickListener);
        indexAdapter.setShowFooter(false);
        mRecyclerView.setAdapter(indexAdapter);
        new IndexPresenter(this);

    }

    IndexAdapter.OnItemClickListener onItemClickListener =new IndexAdapter.OnItemClickListener() {
        @Override
        public void itemClick(View view, int postiton) {
            DaiKeOrder card = indexAdapter.getItem(postiton);

            Intent intent = new Intent(mContext,DetailActivity.class);
            intent.putExtra("id",card.getObjectId());
            startActivity(intent);

        }
    };

    private void initToggle() {
        DrawerLayout drawer = (DrawerLayout)getActivity().findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (getActivity(), drawer, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }



    /****
     * 监听上拉动作，上拉加载更多,
     *
     */

    private  RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        int lastVisible;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if(indexAdapter.isShowFooter())
                lastVisible = manager.findLastVisibleItemPosition();

        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            if(indexAdapter.isShowFooter()&&(newState==RecyclerView.SCROLL_STATE_IDLE)&&((lastVisible+1) ==
                    indexAdapter.getItemCount()))
            {
                Log.d("wyf", "onScrollStateChanged: is loading more");
                presenter.loadData(cardTotal);
                cardTotal+= Config.ONE_TIME_LOAD_NUMBER;
            }
        }
    };

    public void hideDialog() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public void showDialog() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void loadFailed(int code) {

    }

    /**
     *
     *加载成功装入数据刷新布局
     * @param cardData
     */
    @Override
    public void loadCompleted(List cardData) {

        hideDialog();

        if(cardData.size()==0&&cardTotal!=0)
        {

            indexAdapter.setShowFooter(false);
            indexAdapter.notifyDataSetChanged();
            return;
        }

        if(!cardData.isEmpty())
            this.cardData.addAll(cardData);

        indexAdapter.notifyDataSetChanged();
        indexAdapter.setShowFooter(true);
    }

    /**
     * 监听刷新后
     *
     */

    @Override
    public void onRefresh() {


        cardTotal = 0;

        if(cardData.size()==0)
        {
            indexAdapter.setShowFooter(false);
            indexAdapter.notifyDataSetChanged();
        }
       else {
            cardData.clear();
        }

        indexAdapter.setShowFooter(false);

        presenter.loadData(cardTotal);
        cardTotal =Config.ONE_TIME_LOAD_NUMBER;

    }


    @Override
    public void setPresenter(IndexContract.Presenter persenter) {
            this.presenter=persenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        cardData.clear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cardData.clear();
    }
}
