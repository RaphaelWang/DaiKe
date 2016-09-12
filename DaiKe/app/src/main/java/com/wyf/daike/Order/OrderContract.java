package com.wyf.daike.Order;

import com.wyf.daike.Base.BasePresenter;
import com.wyf.daike.Base.BaseView;
import com.wyf.daike.Bean.DaiKeOrder;

import java.util.List;

/**
 * Created by wyf on 2016/8/22.
 */
public interface OrderContract {

     interface View extends BaseView<Presenter>
    {
         void showProgress();
         void hideProgress();
         void onLoadDataSuccess(List<DaiKeOrder> orderList);
         void onLoadDataFail(String error);
    }


     interface  Presenter extends BasePresenter

    {
         void loadData();
         void loadOtherDaiMyLession();

    }


}
