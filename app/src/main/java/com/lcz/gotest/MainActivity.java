package com.lcz.gotest;

import android.widget.Button;

import com.lcz.lczed_mvpbase.base.BaseActivity;

public class MainActivity extends BaseActivity<MyP> {

    Button mBtn1;
    Button mBtn2;
    Button mBtn3;
    Button mBtn4;

    @Override
    protected MyP createPresenter() {
        return new MyP();
    }

    @Override
    protected void initView() {
        super.initView();
        initLayout(R.layout.activity_main);

        initTitleBar(true, true, "这是标题");

    }

    @Override
    protected void initData() {
        super.initData();

    }
}