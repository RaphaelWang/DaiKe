package com.wyf.daike.Order;

import android.util.Log;

import com.wyf.daike.Bean.DaiKeOrder;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by wyf on 2016/8/22.
 */
public class OrderPresenter implements OrderContract.Presenter {
    OrderContract.View view ;
    private static final String TAG = "OrderPresenter";
    OrderPresenter(OrderContract.View view)
    {
        this.view = view;
        view.setPresenter(this);

    }

    @Override
    public void loadData() {
        BmobQuery<DaiKeOrder> query = new BmobQuery<DaiKeOrder>();
//        query.addQueryKeys(BmobUser.getCurrentUser().getUsername());
        query.findObjects(new FindListener<DaiKeOrder>() {
            @Override
            public void done(List<DaiKeOrder> list, BmobException e) {
                if(e==null)
                {
                    view.onLoadDataSuccess(list);
                    view.hideProgress();
                    Log.d(TAG, "done:"+list.size());
                }else {
                    view.hideProgress();
                    view.onLoadDataFail(e.toString());
                    Log.d(TAG, "done: "+e.toString());
                }
            }
        });

    }

    @Override
    public void start() {

    }
}
