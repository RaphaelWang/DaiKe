package com.wyf.daike.AddDaiKe;

import com.wyf.daike.Bean.DaiKeOrder;

import java.util.List;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2016/7/30.
 */
public class AddDaiKeModel implements AddDaiKeContract.Model{

    private DaiKeOrder indexCard = new DaiKeOrder();
    private boolean sendState;
    private AddDaiKePresenter presenter;
    AddDaiKeModel(AddDaiKePresenter presenter)
    {
        this.presenter = presenter;
        presenter.setModel(this);
    }


    public void   sendDaiKeInfo(String subject, String classroom, String title,
                                String price, String schooltime, String promulgater,String img, int state) {
        indexCard.setoClassroom(classroom);
        indexCard.setoSubject(subject);
        indexCard.setoTitle(title);
        indexCard.setoPrice(price);
        indexCard.setoOrderState(state);
        indexCard.setoSchoolTime(schooltime);
        indexCard.setoPromulgatorAccount(promulgater);

        indexCard.setUserImgUrl(img);
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
