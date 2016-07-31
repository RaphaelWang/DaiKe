package com.wyf.daike.Index;

import android.util.Log;
import android.widget.Toast;

import com.wyf.daike.Bean.IndexCard;
import com.wyf.daike.global.MyApplication;
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
    List<IndexCard> cardData;
    private  IndexPresenter presenter;
    IndexModel(IndexPresenter presenter)
    {
        this.presenter = presenter;
        presenter.setModel(this);
    }


    public  void loadData()
    {

        BmobQuery<IndexCard> query = new BmobQuery<IndexCard>();
        query.findObjects(new FindListener<IndexCard>()
        {
            @Override
            public void done(List<IndexCard> object, BmobException e)

            {
                if(e==null)
                {
                    cardData =object;
                    for(int i=0;i<cardData.size();i++)
                    Log.d("wyf", "done: "+cardData.get(i).getCardTitle());
                    presenter.setCardContent(cardData);



                }else{
                   presenter.loadFailed(flag);
                }
            }
        });

    }


}
