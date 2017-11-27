package com.bwie.chengchuankai.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bwie.chengchuankai.R;
import com.bwie.chengchuankai.bean.FindBean;
import com.bwie.chengchuankai.presenter.FindPresenter;
import com.bwie.chengchuankai.presenter.FindPresenterImpl;
import com.bwie.chengchuankai.view.FindView;

import cn.jzvd.JZVideoPlayerStandard;
import tv.danmaku.ijk.media.widget.media.AndroidMediaController;
import tv.danmaku.ijk.media.widget.media.IjkVideoView;

public class findActivity extends AppCompatActivity implements FindView {

    private JZVideoPlayerStandard mVideoplayer;
    private String hdurl;
    private IjkVideoView ijkVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        ijkVideoView = (IjkVideoView) findViewById(R.id.ijkPlayer);
        //initView();
        String url = "http://movie.vods1.cnlive.com/3/vod/2017/0703/3_691153a0add84d0ea7e0a619a517203d/ff8080815bf6b453015d083ec65a38b6_400.m3u8";
        Intent intent = getIntent();
        String dataId = intent.getStringExtra("dataId");
        FindPresenter findPresenter = new FindPresenterImpl(this);
        findPresenter.guanlian(dataId);
        //mVideoplayer.setUp(smoothURL, JZVideoPlayer.SCREEN_LAYOUT_NORMAL,"电影");
        Button button = (Button) findViewById(R.id.fullscreen);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOrientation(getResources().getConfiguration().orientation);
            }
        });

    }
    private void setOrientation(int orientation) {
        if (orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    public void showData(FindBean findBean) {
        String msg = findBean.getMsg();
        Log.i("zzz",msg);
        Toast.makeText(findActivity.this, msg + "ooo", Toast.LENGTH_SHORT).show();
        String smoothURL = findBean.getRet().getSmoothURL();
        //hdurl = findBean.getRet().getHDURL();
        AndroidMediaController controller = new AndroidMediaController(this, false);
        ijkVideoView.setMediaController(controller);
        //String url = "https://wdl.wallstreetcn.com/41aae4d2-390a-48ff-9230-ee865552e72d";
        // String url = "http://o6wf52jln.bkt.clouddn.com/演员.mp3";
        ijkVideoView.setVideoURI(Uri.parse(smoothURL));
        ijkVideoView.start();
    }

 /*   private void initView() {
        mVideoplayer = (JZVideoPlayerStandard) findViewById(R.id.videoplayer);
    }*/
}
