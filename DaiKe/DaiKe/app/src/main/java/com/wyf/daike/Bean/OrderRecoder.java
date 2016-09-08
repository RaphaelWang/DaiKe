package com.wyf.daike.Bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by wyf on 2016/8/22.
 */
public class OrderRecoder extends BmobObject{
    //发布表的ID
    String publishId;
    //发布人
    String publishPerson;
    //接收人
    String receiverPerson;
    int state;
    String title;

    public String getPublishId() {
        return publishId;
    }

    public String getPublishPerson() {
        return publishPerson;
    }

    public String getReceiverPerson() {
        return receiverPerson;
    }

    public void setPublishId(String publishId) {
        this.publishId = publishId;
    }

    public void setPublishPerson(String publishPerson) {
        this.publishPerson = publishPerson;
    }

    public void setReceiverPerson(String receiverPerson) {
        this.receiverPerson = receiverPerson;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getState() {
        return state;
    }

    public String getTitle() {
        return title;
    }
}
