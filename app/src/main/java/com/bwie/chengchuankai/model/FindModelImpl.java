package com.bwie.chengchuankai.model;

import android.util.Log;

import com.bwie.chengchuankai.api.Api;
import com.bwie.chengchuankai.api.ApiServer;
import com.bwie.chengchuankai.bean.FindBean;
import com.bwie.chengchuankai.net.RetrofitUtils;
import com.bwie.chengchuankai.presenter.FindPresenter;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by C-PC on 2017/11/23.
 */

public class FindModelImpl implements FindModel {
    FindPresenter findPresenter;

    public FindModelImpl(FindPresenter findPresenter) {
        this.findPresenter = findPresenter;
    }


    @Override
    public void getData(String dataId) {
        ApiServer apiService = RetrofitUtils.getInstance().getApiService(Api.FIND, ApiServer.class);
        Observable<FindBean> observable = apiService.getFind(dataId);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("qqq",e.getMessage());
                    }

                    @Override
                    public void onNext(FindBean findBean) {
                        findPresenter.OngetData(findBean);
                    }
                });
    }
}
