package com.wyf.daike.Bean;

import java.io.ObjectInputStream;
import java.util.List;
import java.util.Set;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Administrator on 2016/9/9.
 */
public class DaiKeOrder extends BmobObject{
    String oTitle;
    String oSchoolTime;  //上课时间
    String oSubject;
    String oClassroom;
    String oPrice;
    String userImgUrl; //发布人头像
    String usrName;
    String oPromulgatorAccount;  //发布人
    String ReceiverAccount;  // 代替上课的
    int oOrderState;   //交易状态  0：已发布  1：正在代课中   2：交易成功  3异常
//    String objectId;
//
//    public String getObjectId() {
//        return objectId;
//    }
//
//    public void setObjectId(String objectId) {
//        this.objectId = objectId;
//    }




    public void setoClassroom(String oClassroom) {
        this.oClassroom = oClassroom;
    }

    public void setoOrderState(int oOrderState) {
        this.oOrderState = oOrderState;
    }

    public void setoPrice(String oPrice) {
        this.oPrice = oPrice;
    }

    public void setoPromulgatorAccount(String oPromulgatorAccount) {
        this.oPromulgatorAccount = oPromulgatorAccount;
    }

    public void setUserImgUrl(String userImgUrl) {
        this.userImgUrl = userImgUrl;
    }

    public String getUserImgUrl() {
        return userImgUrl;
    }

    public void setoSchoolTime(String oSchoolTime) {
        this.oSchoolTime = oSchoolTime;
    }

    public void setoSubject(String oSubject) {
        this.oSubject = oSubject;
    }

    public void setoTitle(String oTitle) {
        this.oTitle = oTitle;
    }

    public void setReceiverAccount(String receiverAccount) {
        ReceiverAccount = receiverAccount;
    }

    public String getoClassroom() {
        return oClassroom;
    }

    public int getoOrderState() {
        return oOrderState;
    }

    public String getoPrice() {
        return oPrice;
    }

    public String getoPromulgatorAccount() {
        return oPromulgatorAccount;
    }


    public String getoSchoolTime() {
        return oSchoolTime;
    }

    public String getoSubject() {
        return oSubject;
    }

    public String getoTitle() {
        return oTitle;
    }

    public String getReceiverAccount() {
        return ReceiverAccount;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getUsrName() {
        return usrName;
    }


}
