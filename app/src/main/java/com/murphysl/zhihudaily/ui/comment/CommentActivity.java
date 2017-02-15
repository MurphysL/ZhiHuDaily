package com.murphysl.zhihudaily.ui.comment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.murphysl.zhihudaily.R;
import com.murphysl.zhihudaily.adapter.base.MultiItemTypeAdapter;
import com.murphysl.zhihudaily.bean.CommentsBean;
import com.murphysl.zhihudaily.bean.LongCommentsBean;
import com.murphysl.zhihudaily.bean.ShortCommentsBean;
import com.murphysl.zhihudaily.mvpframe.base.BaseMVPActivity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * CommentActivity
 *
 * 界面排版
 *
 * @author: MurphySL
 * @time: 2017/2/15 11:34
 */


public class CommentActivity extends BaseMVPActivity<CommentModel , CommentPresenter>  implements CommentContract.View{


    private RecyclerView longCommentsRecyclerView;
    private List<CommentsBean> longCommentsList = new ArrayList<>();
    private MultiItemTypeAdapter<CommentsBean> adapter;

    private int longCommentsNum = 0;
    private int shortCommentsNum = 0;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_comments;
    }

    @Override
    protected void initView() {
        longCommentsRecyclerView = (RecyclerView) findViewById(R.id.long_comment);
        longCommentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MultiItemTypeAdapter(this , longCommentsList);
        longCommentsRecyclerView.setAdapter(adapter);

    }

    @Override
    public void onRequestEnd() {
        Logger.i("onRequestEnd");
    }

    @Override
    public void onRequestError(String msg) {
        Logger.w(msg);
    }

    @Override
    public void showLongComments(LongCommentsBean longCommentsBean) {
        if(longCommentsBean == null)
            return;
        for(int i = 0 ;i < longCommentsBean.getComments().size() ; i ++){
            longCommentsNum ++;
            longCommentsList.add(longCommentsBean.getComments().get(i));
            Logger.i(longCommentsBean.toString());
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showShortComments(ShortCommentsBean shortCommentsBean) {

    }
}
