package com.bwie.chengchuankai.api;

import com.bwie.chengchuankai.bean.FindBean;
import com.bwie.chengchuankai.bean.ShouYeBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by C-PC on 2017/11/23.
 */

public interface ApiServer {
    /**
     * 首页
     * http://api.svipmovie.com/front/homePageApi/homePage.do
     */
    @GET("homePageApi/homePage.do")
    Observable<ShouYeBean> getParm(@Query("mediaId") String path);

    /**
     * http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=620539e5053a479487b47b25b6d00e3e
     */
  /*  @GET("front/videoDetailApi/videoDetail.do")
    Observable<FindBean> getFind(@Query("mediaId") String mediaId);*/

    @GET("videoDetailApi/videoDetail.do")
    Observable<FindBean> getFind(@Query("mediaId") String mediaId);

}
