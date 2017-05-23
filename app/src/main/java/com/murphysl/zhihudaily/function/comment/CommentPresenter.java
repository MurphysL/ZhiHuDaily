package com.murphysl.zhihudaily.function.comment;

import com.murphysl.zhihudaily.bean.LongCommentsBean;
import com.murphysl.zhihudaily.bean.ShortCommentsBean;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * CommentPresenter
 *
 * @author: MurphySL
 * @time: 2017/2/15 11:31
 */


public class CommentPresenter extends CommentContract.Presenter {
    @Override
    void getLongComments(int id) {
        rx.add(model.getLongComments(id).subscribe(
                new Consumer<LongCommentsBean>() {
                    @Override
                    public void accept(LongCommentsBean longCommentsBean) throws Exception {
                        view.showLongComments(longCommentsBean);
                    }
                },
                new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.onRequestError(throwable.toString());
                    }
                },
                new Action() {
                    @Override
                    public void run() throws Exception {
                        view.onRequestEnd();
                    }
                }
        ));
    }

    @Override
    void getShortComments(int id) {
        rx.add(model.getShortComments(id).subscribe(
                new Consumer<ShortCommentsBean>() {
                    @Override
                    public void accept(ShortCommentsBean shortCommentsBean) throws Exception {
                        view.showShortComments(shortCommentsBean);
                    }
                },
                new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.onRequestError(throwable.toString());
                    }
                },
                new Action() {
                    @Override
                    public void run() throws Exception {
                        view.onRequestEnd();
                    }
                }
        ));
    }
}
