package com.murphysl.zhihudaily.ui.comment;

import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.murphysl.zhihudaily.R;
import com.murphysl.zhihudaily.adapter.base.MultiItemTypeAdapter;
import com.murphysl.zhihudaily.adapter.delegate.CommentsDelegate;
import com.murphysl.zhihudaily.bean.CommentsBean;
import com.murphysl.zhihudaily.bean.LongCommentsBean;
import com.murphysl.zhihudaily.bean.ShortCommentsBean;
import com.murphysl.zhihudaily.config.Constants;
import com.murphysl.zhihudaily.mvpframe.base.BaseMVPActivity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * CommentActivity
 *
 * 界面排版
 * RecyclerView 与 ScrollView 滑动冲突
 * 点击短屏后向上滑动
 *
 * @author: MurphySL
 * @time: 2017/2/15 11:34
 */


public class CommentActivity extends BaseMVPActivity<CommentModel , CommentPresenter>  implements CommentContract.View{

    private CardView shortCommentsHead;
    private RelativeLayout empty;
    private TextView longCommentsNumTv;
    private TextView shortommentsNumTv;

    private RecyclerView longCommentsRecyclerView;
    private List<CommentsBean> longCommentsList = new ArrayList<>();
    private MultiItemTypeAdapter<CommentsBean> longCommentAdapter;
    private RecyclerView shortCommentsRecyclerView;
    private List<CommentsBean> shortCommentsList = new ArrayList<>(0);
    private MultiItemTypeAdapter<CommentsBean> shortCommentAdapter;

    private int longCommentsNum = 0;
    private int shortCommentsNum = 0;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_comments;
    }

    @Override
    protected void initView() {
        shortCommentsHead = (CardView) findViewById(R.id.short_comment_head);
        empty = (RelativeLayout) findViewById(R.id.long_comment_empty);
        longCommentsNumTv = (TextView) findViewById(R.id.long_comment_num);
        shortommentsNumTv = (TextView) findViewById(R.id.short_comment_num);
        longCommentsRecyclerView = (RecyclerView) findViewById(R.id.long_comments);
        longCommentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        longCommentAdapter = new MultiItemTypeAdapter(this , longCommentsList);
        longCommentAdapter.addItemViewDelegate(new CommentsDelegate());
        longCommentsRecyclerView.setAdapter(longCommentAdapter);
        shortCommentsRecyclerView = (RecyclerView) findViewById(R.id.short_comments);
        shortCommentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        shortCommentAdapter = new MultiItemTypeAdapter<>(this , shortCommentsList);
        shortCommentAdapter.addItemViewDelegate(new CommentsDelegate());
        shortCommentsRecyclerView.setAdapter(shortCommentAdapter);

        shortCommentsHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shortCommentsRecyclerView.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    protected void initData() {
        int newsId = getIntent().getIntExtra(Constants.NEWS_ID_COMMENTS , 0);
        presenter.getLongComments(newsId);
        presenter.getShortComments(newsId);
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
        if(longCommentsBean.getComments().size() != 0)
            empty.setVisibility(View.GONE);
        for(int i = 0 ;i < longCommentsBean.getComments().size() ; i ++){
            longCommentsNum ++;
            longCommentsList.add(longCommentsBean.getComments().get(i));
            Logger.i(longCommentsBean.toString());
        }
        longCommentsNumTv.setText(longCommentsNum + "");
        longCommentAdapter.notifyDataSetChanged();
    }

    @Override
    public void showShortComments(ShortCommentsBean shortCommentsBean) {
        if(shortCommentsBean == null)
            return;
        for(int i = 0 ;i < shortCommentsBean.getComments().size() ; i ++){
            shortCommentsNum ++;
            shortCommentsList.add(shortCommentsBean.getComments().get(i));
            Logger.i(shortCommentsBean.getComments().get(i).toString());
        }
        shortommentsNumTv.setText(shortCommentsNum + "");
        shortCommentAdapter.notifyDataSetChanged();

    }
}
