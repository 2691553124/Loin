package com.jy.sxday01.presenter;

import com.jy.sxday01.bean.LoginBean;
import com.jy.sxday01.bean.RegisterBean;
import com.jy.sxday01.callback.HomeCallBack;
import com.jy.sxday01.model.ImPHomeModel;
import com.jy.sxday01.view.HomeView;

public class ImPHomePersenter implements HomePresenter, HomeCallBack {
    private ImPHomeModel imPHomeModel;
    private HomeView homeView;

    public ImPHomePersenter(ImPHomeModel imPHomeModel, HomeView homeView) {
        this.imPHomeModel = imPHomeModel;
        this.homeView = homeView;
    }

    @Override
    public void initDataLogin(String username, String password) {
        if (imPHomeModel != null) {
            imPHomeModel.initLogin(username, password, this);
        }
    }

    @Override
    public void initDataRegister(String username, String password, String phone, String verify) {
        if (imPHomeModel != null) {
            imPHomeModel.initRegister(username, password, phone, verify, this);
        }
    }

    @Override
    public void onLoginFild(String onfild) {
        if (homeView != null) {
            homeView.onLoginFild(onfild);
        }
    }

    @Override
    public void onSuccess(LoginBean loginBean) {
        if (homeView != null) {
            homeView.onSuccess(loginBean);
        }
    }

    @Override
    public void onRegisterSuccess(RegisterBean registerBean) {
        if (homeView != null) {
            homeView.onRegisterSuccess(registerBean);
        }
    }

    @Override
    public void onFild(String onfild) {
        if (homeView != null) {
            homeView.onFild(onfild);
        }
    }
}
