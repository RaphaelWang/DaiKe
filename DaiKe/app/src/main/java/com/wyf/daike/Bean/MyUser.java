package com.wyf.daike.Bean;

import cn.bmob.v3.BmobUser;

/**
 * Created by wyf on 2016/8/4.
 */
public class MyUser extends BmobUser {
    String name;
    String telephone;

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
