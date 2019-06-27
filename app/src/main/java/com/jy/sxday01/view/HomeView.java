package com.jy.sxday01.view;

import com.jy.sxday01.bean.LoginBean;
import com.jy.sxday01.bean.RegisterBean;

public interface HomeView {
    void onLoginFild(String onfild);
    void onSuccess(LoginBean loginBean);

    void onRegisterSuccess(RegisterBean registerBean);

    void onFild(String onfild);
}
