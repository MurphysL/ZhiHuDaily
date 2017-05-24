package com.murphysl.zhihudaily.function.comment;

import com.murphysl.zhihudaily.data.bean.LongCommentsBean;
import com.murphysl.zhihudaily.data.bean.ShortCommentsBean;
import com.murphysl.zhihudaily.base.mvp.BaseModel;
import com.murphysl.zhihudaily.base.mvp.BasePresenter;
import com.murphysl.zhihudaily.base.mvp.BaseView;

import io.reactivex.Observable;

/**
 * CommentContract
 *
 * @author: MurphySL
 * @time: 2017/2/15 10:56
 */


public interface CommentContract {

    interface View extends BaseView{
        void showLongComments(LongCommentsBean longCommentsBean);
        void showShortComments(ShortCommentsBean shortCommentsBean);
    }

    interface Model extends BaseModel{
        Observable<LongCommentsBean> getLongComments(int id);
        Observable<ShortCommentsBean> getShortComments(int id);
    }

    abstract class Presenter extends BasePresenter<Model , View>{
        abstract void getLongComments(int id);
        abstract void getShortComments(int id);
    }
}
