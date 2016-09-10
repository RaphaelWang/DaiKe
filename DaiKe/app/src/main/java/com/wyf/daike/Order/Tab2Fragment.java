package com.wyf.daike.Order;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wyf.daike.Adapter.OrderAdapter;
import com.wyf.daike.Bean.DaiKeOrder;
import com.wyf.daike.R;

import java.util.ArrayList;
import java.util.List;

public class Tab2Fragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,OrderContract.View {

    public static Tab2Fragment newInstance() {
        return new Tab2Fragment();
    }
    private OrderContract.Presenter presenter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<DaiKeOrder> orderData ;
    private OrderAdapter adapter;
    private Context mContext;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_tab2, container, false);
        initView(view);
        initRecyclerView();
        initSwipRefreshLayout();

        new OrderPresenter(this);
        //加载数据
        showProgress();
        presenter.loadData();
        return view;
    }


    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerViewTab2);
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
        orderData = new ArrayList<DaiKeOrder>();
        adapter = new OrderAdapter(orderData,getContext());
        mRecyclerView.setAdapter(adapter);
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


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext =  context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
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
    public void onLoadDataSuccess(List<DaiKeOrder> orderList) {
        if(!orderList.isEmpty())
        {
            orderData.addAll(orderList);
            adapter.notifyDataSetChanged();

        }
    }

    @Override
    public void onLoadDataFail(String error) {
        Toast.makeText(mContext,error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(OrderContract.Presenter persenter) {
        presenter = persenter ;
    }

}
