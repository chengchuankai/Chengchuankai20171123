package com.bwie.chengchuankai.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwie.chengchuankai.R;
import com.bwie.chengchuankai.bean.ShouYeBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by C-PC on 2017/11/23.
 */

public class RvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
   // private List<ShouYeBean.RetBean.ListBean> list;
    List<ShouYeBean.RetBean.ListBean.ChildListBean> list;

    private OnGoodsListener onGoodsListener;

    public interface OnGoodsListener {
        public void onChildItemClick(ShouYeBean.RetBean.ListBean.ChildListBean goodsListBean);
    }

    public void setOnGoodsListener(OnGoodsListener onGoodsListener) {
        this.onGoodsListener = onGoodsListener;
    }

    public RvAdapter(Context context, List<ShouYeBean.RetBean.ListBean.ChildListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item, parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyviewHolder myviewHolder= (MyviewHolder) holder;
        String pic = list.get(position).getPic();
        String title = list.get(position).getTitle();
        Uri uri=Uri.parse(pic);
        myviewHolder.sdv.setImageURI(uri);
        myviewHolder.tv.setText(title);
        myviewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onGoodsListener.onChildItemClick(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyviewHolder extends RecyclerView.ViewHolder {

        private final TextView tv;
        private final SimpleDraweeView sdv;
        private final LinearLayout ll;

        public MyviewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            sdv = itemView.findViewById(R.id.sdv);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
