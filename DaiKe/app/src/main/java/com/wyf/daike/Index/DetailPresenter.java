package com.wyf.daike.Index;

import android.util.Log;

import com.wyf.daike.Bean.IndexCard;
import com.wyf.daike.global.Config;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

/**
 * Created by Administrator on 2016/8/7.
 */
public class DetailPresenter  {
    IndexContract.LoadDetail loadDetail = null;
    IndexCard indexCard = new IndexCard();
    private DetailFragment detailFragment = new DetailFragment();
    DetailPresenter(IndexContract.LoadDetail loadDetail)
    {
        loadDetail = loadDetail;
    }

    public void loadDetail(String id)

    {
        BmobQuery<IndexCard> bmobQuery = new BmobQuery<IndexCard>();
        bmobQuery.getObject(id, new QueryListener<IndexCard>() {
        @Override
        public void done(IndexCard object,BmobException e) {
            if(e==null){
                Log.d("wyf", "done: "+"详情页获取成功"+object.getCardClassroom()+object.getObjectId()+object.getCardTitle());
                indexCard = object;
                detailFragment.onSuccess(indexCard);
            }else{

                Log.d("wyf","done: "+e.getMessage());


            }
        }
    });

    }
}
