package com.lcz.lczed_mvpbase.utils;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by asus on 2019/3/4.
 */

public abstract class BaseObserver<T> implements Observer<T> {
    private final String TAG = getClass().getName();
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete: ");
    }

    @Override
    public void onSubscribe(Disposable d) {

        compositeDisposable.add(d);
    }
    public void deletDisposable(){
        compositeDisposable.clear();
    }
}
