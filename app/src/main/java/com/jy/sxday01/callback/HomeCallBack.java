package com.jy.sxday01.callback;

import com.jy.sxday01.bean.LoginBean;
import com.jy.sxday01.bean.RegisterBean;

public interface HomeCallBack {
    void onLoginFild(String onfild);
    void onSuccess(LoginBean loginBean);

    void onRegisterSuccess(RegisterBean registerBean);

    void onFild(String onfild);
}
