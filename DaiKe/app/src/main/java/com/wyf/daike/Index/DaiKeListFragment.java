package com.wyf.daike.Index;


import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.net.ConnectivityManagerCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.Toast;

import com.wyf.daike.Adapter.IndexAdapter;
import com.wyf.daike.Bean.IndexCard;
import com.wyf.daike.R;
import com.wyf.daike.global.MyApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Manifest;

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
    List<IndexCard> cardData;
    private IndexContract.Presenter presenter;
    private   LinearLayoutManager manager;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dai_ke_list, container, false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.mRecyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.mSwipeRefreshLayout);

        new IndexPresenter(this);


        init();
        showDialog();
        onRefresh();
        return view;
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void init() {

        //设置SwipRefreshLayout
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setProgressViewOffset(false, 100, 300);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        //设置RecyclerView
         manager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnScrollListener(onScrollListener);
        cardData = new ArrayList<IndexCard>();
        indexAdapter =  new IndexAdapter(getActivity().getApplicationContext(),cardData);
        mRecyclerView.setAdapter(indexAdapter);
        new IndexPresenter(this);
    }

    /****
     * 下拉加载更多的
     *
     */

    private  RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        int lastVisible;
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            if((newState==RecyclerView.SCROLL_STATE_IDLE)&&(lastVisible+1)==indexAdapter.getItemCount())
            {
                presenter.loadData();
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisible = manager.findLastVisibleItemPosition();

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

        this.cardData.addAll(cardData);
        indexAdapter.notifyDataSetChanged();
    }




    /**
     * 监听刷新后
     *
     */

    @Override
    public void onRefresh() {
        presenter.loadData();
    }


    @Override
    public void setPresenter(IndexContract.Presenter persenter) {
            this.presenter=persenter;
    }
}
