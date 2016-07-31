package com.wyf.daike.Index;

import com.wyf.daike.BaseView;
import com.wyf.daike.BasePresenter;

import java.util.List;

/**
 * Created by Administrator on 2016/7/30.
 */
public interface IndexContract {

    public interface View extends BaseView<Presenter> {
        public void  hideDialog();
        public void  showDialog();
        public void  loadFailed();
        public void  loadCompleted(List cardData);
    }
    public  interface Presenter extends BasePresenter{

        public void loadData() ;
        public void setCardContent(List cardData);
        public void loadFailed(boolean loadState);
        public void loadMore();
    }
}
