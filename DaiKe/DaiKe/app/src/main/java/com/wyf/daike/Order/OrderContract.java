package com.wyf.daike.Order;

import com.wyf.daike.Base.BasePresenter;
import com.wyf.daike.Base.BaseView;
import com.wyf.daike.Bean.OrderRecoder;

import java.util.List;

/**
 * Created by wyf on 2016/8/22.
 */
public interface OrderContract {

    public interface View extends BaseView<Presenter>
    {
        public void showProgress();
        public void hideProgress();
        public void onLoadDataSuccess(List<OrderRecoder> orderList);
        public void onLoadDataFail(String error);
    }


    public interface  Presenter extends BasePresenter

    {
        public void loadData();

    }


}
