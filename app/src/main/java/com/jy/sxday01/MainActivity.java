package com.jy.sxday01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jy.sxday01.bean.LoginBean;
import com.jy.sxday01.bean.RegisterBean;
import com.jy.sxday01.model.ImPHomeModel;
import com.jy.sxday01.presenter.ImPHomePersenter;
import com.jy.sxday01.view.HomeView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, HomeView {

    /**
     * 请输入账号
     */
    private EditText mEdName;
    /**
     * 请输入与密码
     */
    private EditText mEdPsw;
    /**
     * 登录
     */
    private Button mBtLogin;
    /**
     * 注册
     */
    private Button mBtRetion;
    private ImPHomePersenter imPHomePersenter;
    private ArrayList<LoginBean> list;
    private String a ="哈哈";
    // 秦浩雄  H1811B
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mEdName = (EditText) findViewById(R.id.ed_name);
        mEdPsw = (EditText) findViewById(R.id.ed_psw);
        mBtLogin = (Button) findViewById(R.id.bt_login);
        mBtLogin.setOnClickListener(this);
        initData();
        list = new ArrayList<>();
        mBtRetion = (Button) findViewById(R.id.bt_retion);
        mBtRetion.setOnClickListener(this);


    }

    private void initData() {
        imPHomePersenter = new ImPHomePersenter(new ImPHomeModel(), this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_login:
                imPHomePersenter.initDataLogin(mEdName.getText().toString(),mEdPsw.getText().toString());
                Intent intent1 = new Intent(MainActivity.this,SanActivity.class);
                startActivity(intent1);
                break;
            case R.id.bt_retion:
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivityForResult(intent,200);
                break;
        }
    }

    @Override
    public void onLoginFild(String onfild) {

    }

    @Override
    public void onSuccess(LoginBean loginBean) {
        Toast.makeText(this, loginBean.getRet(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, loginBean.getCode()+"", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegisterSuccess(RegisterBean registerBean) {

    }

    @Override
    public void onFild(String onfild) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==200&&resultCode==100) {
            String name = data.getStringExtra("name");
            String psw = data.getStringExtra("psw");
            mEdName.setText(name);
            mEdPsw.setText(psw);
        }

    }
}
