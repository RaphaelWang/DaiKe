package com.wyf.daike.AddDaiKe;

import android.hardware.SensorEvent;

import com.wyf.daike.Bean.IndexCard;

import java.text.SimpleDateFormat;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2016/7/30.
 */
public class AddDaiKeModel implements AddDaiKeContract.Model{

    private IndexCard indexCard = new IndexCard();
    private boolean sendState;
    private AddDaiKePresenter presenter;
    AddDaiKeModel(AddDaiKePresenter presenter)
    {
        this.presenter = presenter;
        presenter.setModel(this);
    }


    public void   sendDaiKeInfo(String subject, String classroom, String title, String price,int state) {
        indexCard.setCardClassroom(classroom);
        indexCard.setCardSubect(subject);
        indexCard.setCardTitle(title);
        indexCard.setCardMoney(price);
        indexCard.setState(state);
        indexCard.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null)
                {
                    sendState = true;
                }
            }
        });
    }


    @Override
    public void setData() {
        presenter.setLoadData(sendState);

    }
}
