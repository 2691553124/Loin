package com.jy.sxday01.model;

import com.jy.sxday01.api.HomeServer;
import com.jy.sxday01.bean.LoginBean;
import com.jy.sxday01.bean.RegisterBean;
import com.jy.sxday01.callback.HomeCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImPHomeModel implements HomeModel{


    @Override
    public void initLogin(String username, String password, final HomeCallBack homeCallBack) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(HomeServer.loginurl).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        HomeServer homeServer = retrofit.create(HomeServer.class);
        Observable<LoginBean> dataLogin = homeServer.getDataLogin(username, password);
        dataLogin.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                    homeCallBack.onSuccess(loginBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        homeCallBack.onLoginFild("失败"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @Override
    public void initRegister(String username, String password, String phone, String verify, final HomeCallBack homeCallBack) {
        Retrofit retrofit = new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(HomeServer.register)
                .build();
        HomeServer homeServer = retrofit.create(HomeServer.class);
        Observable<RegisterBean> dataRegister = homeServer.getDataRegister(username, password, phone, verify);
        dataRegister.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                    homeCallBack.onRegisterSuccess(registerBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                    homeCallBack.onFild("失败"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }
}
