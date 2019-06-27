package com.jy.sxday01.presenter;

import com.jy.sxday01.callback.HomeCallBack;

public interface HomePresenter {
    void initDataLogin(String username, String password);
    void initDataRegister(String username, String password, String phone, String verify);
}
