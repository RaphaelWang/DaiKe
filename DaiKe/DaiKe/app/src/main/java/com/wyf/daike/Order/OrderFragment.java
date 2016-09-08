package com.wyf.daike.Order;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wyf.daike.Adapter.OrderAdapter;
import com.wyf.daike.Bean.OrderRecoder;
import com.wyf.daike.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 代课中心，查看自己的代课记录
 */
public class OrderFragment extends Fragment implements  OrderContract.View,SwipeRefreshLayout.OnRefreshListener{


    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private OrderContract.Presenter presenter;
    private Context mContext;
    private List<OrderRecoder> orderData ;
    private   OrderAdapter adapter;

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
        initRecyclerView();
        initSwipRefreshLayout();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        new OrderPresenter(this);
        //加载数据
        showProgress();
        presenter.loadData();
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.mSwipeRefreshLayout);

    }

    private void initSwipRefreshLayout()
    {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setProgressViewOffset(true,100,200);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    private void initRecyclerView()
    {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        orderData = new ArrayList<OrderRecoder>();
        adapter = new OrderAdapter(orderData,getContext());
        mRecyclerView.setAdapter(adapter);
    }


    @Override
    public void showProgress() {
        if(!mSwipeRefreshLayout.isRefreshing())
        {
            mSwipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void hideProgress() {
        if(mSwipeRefreshLayout.isRefreshing())
        {
            mSwipeRefreshLayout.setRefreshing(false);
        }

    }

    @Override
    public void onLoadDataSuccess(List<OrderRecoder> orderList) {
        if(!orderList.isEmpty())
        {
            orderData.addAll(orderList);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoadDataFail(String error) {

    }

    @Override
    public void setPresenter(OrderContract.Presenter persenter) {
        presenter = persenter ;
    }

    @Override
    public void onRefresh() {
        if(!mSwipeRefreshLayout.isRefreshing())
        {
            mSwipeRefreshLayout.setRefreshing(true);
        }
        orderData.clear();
        presenter.loadData();
    }
}
