package com.bwie.chengchuankai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.bwie.chengchuankai.R;
import com.bwie.chengchuankai.adapter.RvAdapter;
import com.bwie.chengchuankai.bean.ShouYeBean;
import com.bwie.chengchuankai.presenter.YueKaoPresenter;
import com.bwie.chengchuankai.presenter.YueKaoPresenterImpl;
import com.bwie.chengchuankai.util.BannerClass;
import com.bwie.chengchuankai.view.YueKaoView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements YueKaoView {
    List<String> list2 = new ArrayList<String>();
    @BindView(R.id.ban)
    Banner mBan;
    @BindView(R.id.rv)
    RecyclerView mRv;
    private RecyclerView rv;
    private RvAdapter adapter;
    private List<ShouYeBean.RetBean.ListBean.ChildListBean> childList;
    private Banner banner;
    private YueKaoPresenter yueKaoPresenter;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		//注释
        banner = (Banner) findViewById(R.id.ban);
        rv = (RecyclerView) findViewById(R.id.rv);
        ButterKnife.bind(this);
        yueKaoPresenter = new YueKaoPresenterImpl(this);
        yueKaoPresenter.guanlian("homePage.do");

    }




    @Override
    public void showData(ShouYeBean shouYeBean) {
        String msg = shouYeBean.getMsg();
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        Log.i("xxx", msg);
        List<ShouYeBean.RetBean.ListBean> list = shouYeBean.getRet().getList();
        for (int i = 0; i < list.size(); i++) {
            childList = list.get(i).getChildList();
        }
        for (int i = 0; i < childList.size(); i++) {
            ShouYeBean.RetBean.ListBean.ChildListBean childListBean = childList.get(i);
            String pic = childListBean.getPic();
            list2.add(pic);
        }
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        RvAdapter adapter = new RvAdapter(MainActivity.this, childList);
        rv.setAdapter(adapter);
        //设置图片加载器
        banner.setImageLoader(new BannerClass());
        banner.setImages(list2);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.start();

        adapter.setOnGoodsListener(new RvAdapter.OnGoodsListener() {
            @Override
            public void onChildItemClick(ShouYeBean.RetBean.ListBean.ChildListBean goodsListBean) {
                Toast.makeText(MainActivity.this, goodsListBean.getAirTime() + "", Toast.LENGTH_SHORT).show();
                String dataId = goodsListBean.getDataId();
                Intent intent = new Intent(MainActivity.this, findActivity.class);
                intent.putExtra("dataId", dataId);
                startActivity(intent);
            }
        });

    }
}
