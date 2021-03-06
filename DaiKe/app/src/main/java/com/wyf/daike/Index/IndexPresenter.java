package com.wyf.daike.Index;

import java.util.List;

/**
 * Created by Administrator on 2016/7/30.
 */
public class IndexPresenter  implements IndexContract.Presenter {

    IndexContract.View view;
    private IndexModel model;
    IndexPresenter(IndexContract.View view)
    {
        this.view = view;
        view.setPresenter(this);
        new IndexModel(this);

    }
    void setModel(IndexModel model){
        this.model = model;
    }
    @Override
    public void loadData(int cardNumber) {
        model.loadData(cardNumber);

    }

    @Override
    public void setCardContent(List cardData) {
        view.loadCompleted(cardData);

    }

    @Override
    public void loadFailed(int code) {
        view.loadFailed(code);
    }

    @Override
    public void loadMore() {

    }


    @Override
    public void start() {

    }


}
