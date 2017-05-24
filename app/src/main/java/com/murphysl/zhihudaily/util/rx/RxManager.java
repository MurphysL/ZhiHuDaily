package com.murphysl.zhihudaily.util.rx;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * RxManager
 *
 * @author: MurphySL
 * @time: 2017/1/19 17:30
 */


public class RxManager {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void add (Disposable m){
        compositeDisposable.add(m);
    }

    public void clear() {
        compositeDisposable.clear();
    }

}
