package com.wyf.daike.Index;

import android.util.Log;

import com.wyf.daike.Bean.DaiKeOrder;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

/**
 * Created by Administrator on 2016/8/7.
 */
@Deprecated
public class DetailPresenter  {
    IndexContract.LoadDetail loadDetail = null;
    DaiKeOrder indexCard = new DaiKeOrder();
    private DetailFragment detailFragment = new DetailFragment();
    DetailPresenter(IndexContract.LoadDetail loadDetail)
    {
        loadDetail = loadDetail;
    }

    public void loadDetail(String id)

    {
        BmobQuery<DaiKeOrder> bmobQuery = new BmobQuery<DaiKeOrder>();
        bmobQuery.getObject(id, new QueryListener<DaiKeOrder>() {
        @Override
        public void done(DaiKeOrder object,BmobException e) {
            if(e==null){
               // Log.d("wyf", "done: "+"详情页获取成功"+object.getCardClassroom()+object.getObjectId()+object.getCardTitle());
                indexCard = object;
                detailFragment.onSuccess(indexCard);
            }else{

                Log.d("wyf","done: "+e.getMessage());


            }
        }
    });

    }
}
