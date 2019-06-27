package com.jy.sxday01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jy.sxday01.api.HomeServer;
import com.jy.sxday01.bean.LoginBean;
import com.jy.sxday01.bean.RegisterBean;
import com.jy.sxday01.bean.VeriFyBean;
import com.jy.sxday01.model.ImPHomeModel;
import com.jy.sxday01.presenter.ImPHomePersenter;
import com.jy.sxday01.view.HomeView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, HomeView {

    /**
     * 输入账号
     */
    private EditText mBtRegisterName;
    /**
     * 输入密码
     */
    private EditText mBtRegisterPsw;
    /**
     * 输入手机号
     */
    private EditText mBtRegisterPhone;
    /**
     * 输入验证码
     */
    private EditText mBtRegisterVerify;
    /**
     * 注册
     */
    private Button mBtRegister;
    /**
     * 确定密码
     */
    private EditText mBtRegisterPsws;
    private ImPHomePersenter imPHomePersenter;
    private String phone;
    private String psw;
    private String psws;
    private String verify;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        mBtRegisterName = (EditText) findViewById(R.id.bt_register_name);
        mBtRegisterPsw = (EditText) findViewById(R.id.bt_register_psw);
        mBtRegisterPhone = (EditText) findViewById(R.id.bt_register_phone);
        mBtRegisterVerify = (EditText) findViewById(R.id.bt_register_verify);
        mBtRegisterPsws = (EditText) findViewById(R.id.bt_register_psws);
        initData();
        mBtRegister = (Button) findViewById(R.id.bt_register);
        mBtRegister.setOnClickListener(this);




    }

    private void initData() {
        imPHomePersenter = new ImPHomePersenter(new ImPHomeModel(), this);
        initDatas();

    }

    private void initDatas() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(HomeServer.besurl).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        HomeServer homeServer = retrofit.create(HomeServer.class);
        Observable<VeriFyBean> veriFyBeanObservable = homeServer.getinitDataVerify();
        veriFyBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VeriFyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VeriFyBean veriFyBean) {
                        String data = veriFyBean.getData();
                        mBtRegisterVerify.setText(data);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("e", "onError: "+e.getMessage() );
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_register:
                name = mBtRegisterName.getText().toString();
                phone = mBtRegisterPhone.getText().toString();
                psw = mBtRegisterPsw.getText().toString();
                psws = mBtRegisterPsws.getText().toString();
                verify = mBtRegisterVerify.getText().toString();

                    if (psw.equals(psws)) {
                        imPHomePersenter.initDataRegister(name, psw, phone, verify);
                        Intent intent = new Intent();
                        intent.putExtra("name", name);
                        intent.putExtra("psw", psw);
                        setResult(100, intent);
                        finish();
                    }


                break;
        }
    }

    @Override
    public void onLoginFild(String onfild) {

    }

    @Override
    public void onSuccess(LoginBean loginBean) {

    }

    @Override
    public void onRegisterSuccess(RegisterBean registerBean) {

    }

    @Override
    public void onFild(String onfild) {

    }
}
