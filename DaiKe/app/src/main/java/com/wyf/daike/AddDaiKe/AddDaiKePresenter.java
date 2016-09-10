package com.wyf.daike.AddDaiKe;

import com.wyf.daike.Index.IndexContract;

/**
 * Created by Administrator on 2016/7/30.
 */
public class AddDaiKePresenter implements AddDaiKeContract.Presenter {


    private  AddDaiKeModel model ;
    private  AddDaiKeContract.View view;
    boolean sendState;

    AddDaiKePresenter(AddDaiKeContract.View view)
    {
        this.view = view;
        view.setPresenter(this);

        new AddDaiKeModel(this);
    }

    void setModel(AddDaiKeModel model)
    {
        this.model = model;

    }

    @Override
    public void sendDaiKeInfo(String subject, String classroom, String title, String price,int state) {
        model.sendDaiKeInfo( subject,  classroom,  title,  price,state);

    }


    @Override
    public void start() {

    }
    void setLoadData(boolean sendState)
    {
        this.sendState = sendState;
        view.setSendState(sendState);
    }
}
