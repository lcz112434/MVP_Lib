package com.lcz.gotest;

import com.lcz.lczed_mvpbase.base.IBasePresenter;
import com.lcz.lczed_mvpbase.utils.BaseObserver;
import com.lcz.lczed_mvpbase.utils.HttpUtils;
import com.lcz.lczed_mvpbase.utils.RxUtils;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

/**
 * @author: lichengze
 * @date on 2020/11/21 12:00 星期六
 * E-mail: lcz3466601343@163.com
 * Description :
 */
public class MyP extends IBasePresenter<MyV> {

    MyV mView;

    @Override
    protected void getData() {
        ClassApi classApi = HttpUtils.getInstance().getApiserver(ClassApi.url, ClassApi.class);
        Observable<TextBean> max = classApi.getMax();
        max.compose(RxUtils.<TextBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<TextBean>() {

                    @Override
                    public void onNext(TextBean classMaxBean) {
                        mView.showData(classMaxBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mView.showErr(e.getMessage());
                    }
                });

    }

    @Override
    protected void setView(MyV view) {
        mView = view;
    }


}