package com.wyf.daike.Index;

import android.util.Log;

import com.wyf.daike.Bean.DaiKeOrder;
import com.wyf.daike.global.Config;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2016/7/24.
 */
public class IndexModel {

    /**
     *
     * flag=true 数据请求成功
     */
    boolean flag ;
    List<DaiKeOrder> cardData;
    private  IndexPresenter presenter;
    IndexModel(IndexPresenter presenter)
    {
        this.presenter = presenter;
        presenter.setModel(this);
    }


    public  void loadData(int cardNumber)
    {

        BmobQuery<DaiKeOrder> query = new BmobQuery<DaiKeOrder>();
        query.setLimit(Config.ONE_TIME_LOAD_NUMBER);
        query.setSkip(cardNumber);
        query.findObjects(new FindListener<DaiKeOrder>()
        {
            @Override
            public void done(List<DaiKeOrder> object, BmobException e)

            {
                if(e==null)
                {
                    cardData =object;
                    for(int i=0;i<cardData.size();i++)
                    Log.d("wyf", "done: "+"成功下载"+cardData.size()+"条数据");
                    presenter.setCardContent(cardData);
                }
                else{
                   presenter.loadFailed(e.getErrorCode());
                    Log.d("wyf", "done: "+"数据下载失败");
                }
            }
        });

    }


}
