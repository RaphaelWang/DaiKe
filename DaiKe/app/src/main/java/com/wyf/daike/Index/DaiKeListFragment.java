package com.wyf.daike.Index;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wyf.daike.Adapter.IndexAdapter;
import com.wyf.daike.Bean.IndexCard;
import com.wyf.daike.R;

import java.util.ArrayList;
import java.util.List;

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

        Log.d("TAG", "onCreateView: ");
        init();
        showDialog();
        onRefresh();
        return view;
    }

    private void init() {


        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setProgressViewOffset(false, 100, 300);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);


        mRecyclerView.setHasFixedSize(true);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        new IndexPresenter(this);
    }


    public void hideDialog() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public void showDialog() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void loadFailed() {
        hideDialog();
        Toast.makeText(mContext, "加载失败", Toast.LENGTH_SHORT).show();

    }

    /**
     *
     *加载成功装入数据刷新布局
     * @param cardData
     */
    @Override
    public void loadCompleted(List cardData) {
        this.cardData = cardData;
        hideDialog();
        indexAdapter =  new IndexAdapter(getActivity().getApplicationContext(),cardData);
        mRecyclerView.setAdapter(indexAdapter);
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
