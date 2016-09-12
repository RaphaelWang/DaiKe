package com.wyf.daike.AddDaiKe;

import com.wyf.daike.Base.BasePresenter;
import com.wyf.daike.Base.BaseView;

import java.util.List;

import cn.bmob.v3.datatype.BmobFile;


/**
 * Created by Administrator on 2016/7/30.
 */
public interface AddDaiKeContract {


    interface  View extends BaseView<Presenter>
    {
        public void  showLoading();

        public void   setSendState(boolean sendStaete);


    }
    interface Presenter extends BasePresenter
    {
        void sendDaiKeInfo(String subject, String classroom, String title,
                           String price, String schooltime, String promulgater , String imgTouXiang, int state);
        //void setModel(Model);
        
    }
    interface Model{

        void  setData();
    }
}
