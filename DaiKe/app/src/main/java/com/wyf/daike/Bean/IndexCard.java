package com.wyf.daike.Bean;


import java.text.SimpleDateFormat;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/7/24.
 */
public class IndexCard extends BmobObject{
    String cardImageUrl;
    String cardTitle;
    String cardMoney;
    String cardSubect;
    String cardClassroom;
    String time;
    String name;
    String telephone;


    public void setCardClassroom(String cardClassroom) {
        this.cardClassroom = cardClassroom;
    }

    public void setCardImageUrl(String cardImageUrl) {
        this.cardImageUrl = cardImageUrl;
    }

    public void setCardMoney(String cardMoney) {
        this.cardMoney = cardMoney;
    }

    public void setCardSubect(String cardSubect) {
        this.cardSubect = cardSubect;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public String getCardClassroom() {
        return cardClassroom;
    }

    public String getCardImageUrl() {
        return cardImageUrl;
    }

    public String getCardMoney() {
        return cardMoney;
    }

    public String getCardSubect() {
        return cardSubect;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }
}

