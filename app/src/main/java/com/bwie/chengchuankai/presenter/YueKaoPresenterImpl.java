package com.bwie.chengchuankai.presenter;

import com.bwie.chengchuankai.bean.ShouYeBean;
import com.bwie.chengchuankai.model.YueKaoModel;
import com.bwie.chengchuankai.model.YueKaoModelImpl;
import com.bwie.chengchuankai.view.YueKaoView;

/**
 * Created by C-PC on 2017/11/23.
 */

public class YueKaoPresenterImpl implements YueKaoPresenter{
    YueKaoView yueKaoView;
    private final YueKaoModel yueKaoModel;

    public YueKaoPresenterImpl(YueKaoView yueKaoView) {
        this.yueKaoView = yueKaoView;
        yueKaoModel = new YueKaoModelImpl(this);
    }

    @Override
    public void guanlian(String path) {
        yueKaoModel.getData(path);
    }

    @Override
    public void OngetData(ShouYeBean shouYeBean) {
        yueKaoView.showData(shouYeBean);
    }
}
