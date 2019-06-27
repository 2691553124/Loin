package com.jy.sxday01.model;

import com.jy.sxday01.callback.HomeCallBack;

public interface HomeModel {
    void initLogin(String username,String password,HomeCallBack homeCallBack);
    void initRegister(String username, String password, String phone, String verify, HomeCallBack homeCallBack);
}
