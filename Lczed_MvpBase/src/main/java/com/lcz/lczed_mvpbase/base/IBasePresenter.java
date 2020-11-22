package com.lcz.lczed_mvpbase.base;

import java.lang.ref.WeakReference;

/**
 * @author: Lczed
 * @date on 2020/11/19 10:35 星期四
 * E-mail: lcz3466601343@163.com
 * Description :
 */
public abstract class IBasePresenter<V extends IBaseView> {
    private WeakReference<V> vWeakReference;

    /**
     * 绑定View
     * Presenter 如果持有 Activity 的强引用，在请求结束之前 Activity 被销毁了，
     * 那么由于网络请求还没有返回，导致 Presenter 一直持有 Activity 对象，使得
     * Activity 无法被回收，此时就容易发生内存泄漏，解决这个问题需要通过弱引用来解决
     *
     * @param view 主UI
     */
    void attachView(V view) {
        vWeakReference = new WeakReference<>(view);
    }


    //取消关联
    void detachView() {
        if (vWeakReference != null) {
            vWeakReference.clear();
        }
    }

    /**
     * @return 返回当前的View实例
     */
    protected V getView() {
        return vWeakReference.get();
    }
} 