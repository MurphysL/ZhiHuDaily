package com.murphysl.zhihudaily.mvpframe.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.murphysl.zhihudaily.R;
import com.murphysl.zhihudaily.bean.LatestNewsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * TestAdapter
 *
 * @author: MurphySL
 * @time: 2017/1/26 22:56
 */


public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyHolder> {

    private Context context;
    private List<LatestNewsBean.StoriesBean> list = new ArrayList<>();

    public TestAdapter(Context context , List<LatestNewsBean.StoriesBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.support_simple_spinner_dropdown_item , parent , false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.textView.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        private TextView textView ;

        public MyHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.title_main);
        }
    }
}
