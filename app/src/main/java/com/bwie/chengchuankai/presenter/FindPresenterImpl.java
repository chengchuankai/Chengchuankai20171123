package com.bwie.chengchuankai.presenter;

import com.bwie.chengchuankai.bean.FindBean;
import com.bwie.chengchuankai.model.FindModel;
import com.bwie.chengchuankai.model.FindModelImpl;
import com.bwie.chengchuankai.view.FindView;

/**
 * Created by C-PC on 2017/11/23.
 */

public class FindPresenterImpl implements FindPresenter {
    FindView findView;
    private final FindModel findModel;

    public FindPresenterImpl(FindView findView) {
        this.findView = findView;
        findModel = new FindModelImpl(this);
    }

    @Override
    public void guanlian(String dataId) {
        findModel.getData(dataId);
    }

    @Override
    public void OngetData(FindBean findBean) {
        findView.showData(findBean);
    }
}
