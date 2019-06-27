package com.jy.sxday01.api;

import com.jy.sxday01.bean.LoginBean;
import com.jy.sxday01.bean.RegisterBean;
import com.jy.sxday01.bean.VeriFyBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HomeServer {
    //    http://yun918.cn/study/public/index.php/register
//  http://yun918.cn/study/public/index.php/login
    String loginurl = "http://yun918.cn/";
    String register = "http://yun918.cn/";
    @POST("study/public/index.php/login")
    Observable<LoginBean> getDataLogin(@Query("username")String username, @Query("password")String password);
    @POST("study/public/index.php/register")
    Observable<RegisterBean> getDataRegister(@Query("username")String username,@Query("password")String password,@Query("phone")String phone,@Query("verify")String verify);
    String besurl = "http://yun918.cn/study/public/index.php/";
    @GET("verify")
    Observable<VeriFyBean> getinitDataVerify();
}
