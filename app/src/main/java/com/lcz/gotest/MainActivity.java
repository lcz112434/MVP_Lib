package com.lcz.gotest;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.lcz.lczed_mvpbase.base.BaseActivity;
import com.lcz.lczed_mvpbase.utils.ActivityCollector;
import com.lcz.lczed_mvpbase.utils.GlideUtlis;
import com.lcz.lczed_mvpbase.utils.LogUtils;
import com.lcz.lczed_mvpbase.utils.OnMultiClickListener;
import com.lcz.lczed_mvpbase.utils.ToastUtil;

public class MainActivity extends BaseActivity<MyP> implements MyV {

    Button mBtn1;
    Button mBtn2;
    Button mBtn3;
    Button mBtn4;
    private MyP mMyP;
    private ImageView mImageView;
    private Button mBtn11;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_main);
    }


    @Override
    protected MyP createPresenter() {
        mMyP = new MyP();
        return mMyP;
    }

    @Override
    protected void initView() {
        mBtn11 = findByid(R.id.btn1);
        mBtn11.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                int size = ActivityCollector.activities.size();
                LogUtils.d(size + "");
            }
        });
        mBtn2 = findByid(R.id.btn2);
        mBtn2.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                exitBy2click();
            }
        });
        mImageView = findByid(R.id.iv);
        GlideUtlis.loadCireVIew(this, "https://lc-gold-cdn.xitu.io/f93d893f6bc72c2e8393.png?imageView2/0/w/1280/h/960/format/webp/ignore-error/1", mImageView);
        initTitleBar(true, false, "首页");
    }


    @Override
    protected void initListener() {

     /*   mBtn1.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {

//                startActivity(LoginActivity.class);
                String[] a = {Manifest.permission.CALL_PHONE,
                        Manifest.permission.READ_CALENDAR,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE};
                Boolean aBoolean = GetPermissions(a);
                if (aBoolean) {
                    LogUtils.d("权限通过");
                }
            }
        });*/
    }

    @Override
    protected void initData() {
        mMyP.setView(this);
        mMyP.getData();
    }


    @Override
    public void showData(TextBean data) {
        LogUtils.d(data.getMessage());
    }

    @Override
    public void showErr(String mag) {
        ToastUtil.showShort(mag);
    }


}