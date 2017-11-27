package com.bwie.chengchuankai.model;

import com.bwie.chengchuankai.api.Api;
import com.bwie.chengchuankai.api.ApiServer;
import com.bwie.chengchuankai.bean.ShouYeBean;
import com.bwie.chengchuankai.net.RetrofitUtils;
import com.bwie.chengchuankai.presenter.YueKaoPresenter;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by C-PC on 2017/11/23.
 */

public class YueKaoModelImpl implements YueKaoModel {
    YueKaoPresenter yueKaoPresenter;

    public YueKaoModelImpl(YueKaoPresenter yueKaoPresenter) {
        this.yueKaoPresenter = yueKaoPresenter;
    }

    @Override
    public void getData(String path) {
        ApiServer apiServer = RetrofitUtils.getInstance().getApiService(Api.SHOUYE, ApiServer.class);
        Observable<ShouYeBean> observable = apiServer.getParm(path);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShouYeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ShouYeBean shouYeBean) {
                        yueKaoPresenter.OngetData(shouYeBean);
                    }
                });
    }
}
