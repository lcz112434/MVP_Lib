package com.lcz.gotest;

import com.lcz.lczed_mvpbase.base.BaseFragment;


public class BlankFragment extends BaseFragment<MyP> {


    @Override
    protected MyP createPresenter() {
        return null;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setLayout() {
        return 0;
    }


    @Override
    public void showDataNull() {

    }
}