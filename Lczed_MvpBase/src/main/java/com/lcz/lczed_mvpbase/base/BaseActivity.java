package com.lcz.lczed_mvpbase.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Looper;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.gyf.immersionbar.ImmersionBar;
import com.lcz.lczed_mvpbase.R;
import com.lcz.lczed_mvpbase.utils.ActivityCollector;
import com.lcz.lczed_mvpbase.utils.LogUtils;
import com.lcz.lczed_mvpbase.utils.NetBroadcastReceiver;
import com.lcz.lczed_mvpbase.utils.NetUtil;
import com.lcz.lczed_mvpbase.utils.OnMultiClickListener;
import com.lcz.lczed_mvpbase.utils.SystemUtils;
import com.lcz.lczed_mvpbase.utils.ToastUtil;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author: Lczed
 * @date on 2020/11/19 10:33 星期四
 * E-mail: lcz3466601343@163.com
 * Description :BaseActivity 基类
 */
public abstract class BaseActivity<P extends IBasePresenter> extends BasePermissionActivity implements IBaseView {

    protected AppCompatActivity activityCompat;

    protected Context context;


    // 是否允许全屏
    private boolean mAllowFullScreen = false;

    //对应的P层
    protected P presenter;

    //butterknife引用类
    private Unbinder unbinder;

    //是否允许旋转屏幕
    private boolean isAllowScreenRoate = true;


    LinearLayout mRootBaseView;//根布局
    View titleView;//include的titleBar
    TextView tvLeft;//titleBar左边的返回键
    TextView tvMiddle;//titleBar的标题

    View mStateLayout;//include的state_layout
    RelativeLayout ll_page_state_nonetwork;//stateLayout网络错误的布局
    LinearLayout ll_page_state_empty;//stateLayout无数据的布局
    RelativeLayout ll_page_state_logding;//stateLayout加载中的布局
    RelativeLayout ll_page_state_error;//stateLayout加载异常的布局


    TextView btReload;//无网络重新加载的布局
    TextView tv_error_loading;//加载失败重新加载的布局

    public NetBroadcastReceiver netBroadcastReceiver;
    public static NetBroadcastReceiver.NetEvent event;
    private int netMobile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注意：这里的setContentView必须有super才可以，需要走父类方法

        super.setContentView(R.layout.activity_base);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设置竖屏模式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

//        initNet();
        initBaseView();
        initLayout();
        ActivityCollector.addActivity(this);

        //设置屏幕是否可旋转
        if (!isAllowScreenRoate) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        //是否可以全屏
        if (mAllowFullScreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        //透明状态栏
        SetTransparentBar();
        activityCompat = this;
        context = this;
        //判断是否有网络，如果无网络则提示对应页面
        if (!SystemUtils.checkNetWork()) {
            initTitleBar(true, false, "WanAndroid");
            showNetError();
        } else {
            initCreae();
        }

    }

    public void initCreae() {
        ButterKnife.bind(this);
        initView();
        presenter = createPresenter();
        presenter.attachView(this);
        initData();
        initListener();
    }


    protected abstract void initListener();

    /**
     * 页面布局绑定
     */
    protected abstract void initLayout();


    private void initBaseView() {
        mRootBaseView = (LinearLayout) findViewById(R.id.activity_base_root);
        titleView = findViewById(R.id.activity_base_title_bar);
        tvLeft = (TextView) findViewById(R.id.title_bart_tv_left);
        tvMiddle = (TextView) findViewById(R.id.title_bart_tv_middle);
        mStateLayout = findViewById(R.id.activity_base_state_layout);
        btReload = (TextView) findViewById(R.id.nowork_tv);
        tv_error_loading = (TextView) findViewById(R.id.tv_error_loading);
        ll_page_state_empty = (LinearLayout) findViewById(R.id.rlv_nobody);
        ll_page_state_nonetwork = (RelativeLayout) findViewById(R.id.rlv_nonetwork);
        ll_page_state_logding = (RelativeLayout) findViewById(R.id.rlv_loding);
        ll_page_state_error = (RelativeLayout) findViewById(R.id.rlv_error);


    }

    /**
     * @param titleBarIsShow titleBar是否显示
     * @param tvLeftIsShow   返回键是否显示
     * @param middleText     中间的文字
     */
    public void initTitleBar(boolean titleBarIsShow, boolean tvLeftIsShow,
                             String middleText) {
        if (titleBarIsShow == true) {
            if (!tvLeftIsShow) {
                tvLeft.setVisibility(View.GONE);
            } else {
                tvLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
            }
            tvMiddle.setText(middleText);
        } else {
            titleView.setVisibility(View.GONE);
        }
    }

    //定时器
    private CountDownTimer mTimer;

    /**
     * 切换页面的布局
     *
     * @param pageState 页面状态 NORMAL  EMPTY  ERROR LODING
     */
    public void changePageState(PageState pageState) {
        switch (pageState) {
            case NORMAL:
                if (mStateLayout.getVisibility() == View.VISIBLE) {
                    mStateLayout.setVisibility(View.GONE);
                }
                break;
            case NoWork:
                caseNoNetWork();
                break;
            case EMPTY:
                caseEMPTY();
                break;
            case LODING:
                caseLoding();
                break;
            case ERROR:
                caseERROR();
                break;
        }
    }

    private void caseERROR() {
        if (mStateLayout.getVisibility() == View.GONE) {
            mStateLayout.setVisibility(View.VISIBLE);
            ll_page_state_nonetwork.setVisibility(View.GONE);
            ll_page_state_error.setVisibility(View.VISIBLE);
            ll_page_state_empty.setVisibility(View.GONE);
            ll_page_state_logding.setVisibility(View.GONE);
            tv_error_loading.setOnClickListener(new OnMultiClickListener() {
                @Override
                public void onMultiClick(View v) {
                    if (SystemUtils.checkNetWork()) {
                        LogUtils.d("有网络");
                        showOk();
                        initCreae();
                    } else {
                        showLoading();
                        onTimeing();
                    }
                }
            });
        } else {
            mStateLayout.setVisibility(View.VISIBLE);
            ll_page_state_nonetwork.setVisibility(View.GONE);
            ll_page_state_error.setVisibility(View.VISIBLE);
            ll_page_state_empty.setVisibility(View.GONE);
            ll_page_state_logding.setVisibility(View.GONE);
            tv_error_loading.setOnClickListener(new OnMultiClickListener() {
                @Override
                public void onMultiClick(View v) {
                    if (SystemUtils.checkNetWork()) {
                        LogUtils.d("有网络");
                        showOk();
                        initCreae();
                    } else {
                        showLoading();
                        onTimeing();
                    }
                }
            });
        }
    }

    private void caseLoding() {
        if (mStateLayout.getVisibility() == View.GONE) {
            mStateLayout.setVisibility(View.VISIBLE);
            ll_page_state_nonetwork.setVisibility(View.GONE);
            ll_page_state_error.setVisibility(View.GONE);
            ll_page_state_empty.setVisibility(View.GONE);
            ll_page_state_logding.setVisibility(View.VISIBLE);
        } else {
            mStateLayout.setVisibility(View.VISIBLE);
            ll_page_state_nonetwork.setVisibility(View.GONE);
            ll_page_state_error.setVisibility(View.GONE);
            ll_page_state_empty.setVisibility(View.GONE);
            ll_page_state_logding.setVisibility(View.VISIBLE);
        }
    }

    private void caseEMPTY() {
        if (mStateLayout.getVisibility() == View.GONE) {
            mStateLayout.setVisibility(View.VISIBLE);
            ll_page_state_nonetwork.setVisibility(View.GONE);
            ll_page_state_logding.setVisibility(View.GONE);
            ll_page_state_error.setVisibility(View.GONE);
            ll_page_state_empty.setVisibility(View.VISIBLE);
        } else {
            ll_page_state_nonetwork.setVisibility(View.GONE);
            ll_page_state_logding.setVisibility(View.GONE);
            ll_page_state_error.setVisibility(View.GONE);
            ll_page_state_empty.setVisibility(View.VISIBLE);
        }
    }

    private void caseNoNetWork() {
        if (mStateLayout.getVisibility() == View.GONE) {
            mStateLayout.setVisibility(View.VISIBLE);
            ll_page_state_nonetwork.setVisibility(View.VISIBLE);
            ll_page_state_logding.setVisibility(View.GONE);
            ll_page_state_error.setVisibility(View.GONE);
            btReload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showToast("重新加载中");
                    if (SystemUtils.checkNetWork()) {
                        LogUtils.d("有网络");
                        showOk();
                        initCreae();
                    } else {
                        showLoading();
                        onTimeing();
                    }
                }
            });
            ll_page_state_empty.setVisibility(View.GONE);
        } else {
            mStateLayout.setVisibility(View.VISIBLE);
            ll_page_state_nonetwork.setVisibility(View.VISIBLE);
            ll_page_state_logding.setVisibility(View.GONE);
            ll_page_state_error.setVisibility(View.GONE);
            btReload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showToast("重新加载中");
                    if (SystemUtils.checkNetWork()) {
                        LogUtils.d("有网络");
                        showOk();
                        initCreae();
                    } else {
                        showLoading();
                        onTimeing();
                    }
                }
            });
            ll_page_state_empty.setVisibility(View.GONE);
        }
    }

    private void onTimeing() {

        mTimer = new CountDownTimer((long) (5 * 1000), 1000) {
            int a = 1;

            @Override
            public void onTick(long millisUntilFinished) {
                if (SystemUtils.checkNetWork()) {
                    LogUtils.d("有网络");
                    showOk();
                    initCreae();
                    a = 0;
                } else {
                    a = 1;
                }
            }

            @Override
            public void onFinish() {
                if (a == 1) {
                    showDataError();
                    ToastUtil.showShort("加载失败");
                } else {
                    ToastUtil.showShort("加载完成");
                }
            }
        };
        mTimer.start();
    }


    public enum PageState {
        /**
         * 数据内容显示正常
         */
        NORMAL,
        /**
         * 数据为空
         */
        EMPTY,
        /**
         * 无网络
         */
        NoWork,
        /**
         * 数据加载中
         */
        LODING,
        /**
         * 数据失败
         */
        ERROR
    }


    protected abstract P createPresenter();

    private void SetTransparentBar() {
        ImmersionBar.with(this)
                .transparentStatusBar()
                .statusBarDarkFont(true)
                .init();//透明状态栏，不写默认透明色
    }

    /**
     * 页面数据初始化
     */
    protected abstract void initData();

    /**
     * 页面控件初始化
     */
    protected abstract void initView();

    @Override
    protected void onResume() {
        super.onResume();
        //P层绑定V层
        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    /*
    /**
     * 必须重写setContentView注意不能够添加这行代码 目的将当前界面的布局添加到BaseActivity中去
     * super.setContentView(R.layout.activity_base);
     */
    @Override
    public void setContentView(int layoutResID) {
        View view = getLayoutInflater().inflate(layoutResID, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        if (null != mRootBaseView) {
            mRootBaseView.addView(view, lp);
        }
    }

    protected <T extends View> T findByid(int resId) {
        return (T) findViewById(resId);
    }

    //封装Toast对象
    private static Toast toast;

    /**
     * 显示提示  toast
     *
     * @param msg 提示信息
     */
    @SuppressLint("ShowToast")
    public void showToast(String msg) {
        try {
            if (null == toast) {
                toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            } else {
                toast.setText(msg);
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    toast.show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            //解决在子线程中调用Toast的异常情况处理
            Looper.prepare();
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            Looper.loop();
        }
    }

    /**
     * 数据加载中
     */
    @Override
    public void showLoading() {
        changePageState(PageState.LODING);
    }

    /**
     * 数据加载失败
     */

    public void showLoadEorror() {
        changePageState(PageState.LODING);
    }

    /**
     * 数据加载完成，页面隐藏
     */
    @Override
    public void showOk() {
        changePageState(PageState.NORMAL);
    }


    /**
     * 是否允许屏幕旋转
     *
     * @param allowScreenRoate true or false
     */
    public void setAllowScreenRoate(boolean allowScreenRoate) {
        isAllowScreenRoate = allowScreenRoate;
    }

    /**
     * 设置无网络状态
     */
    @Override
    public void showNetError() {
        changePageState(PageState.NoWork);
    }


    /**
     * 数据加载失败
     */
    @Override
    public void showDataError() {
        changePageState(PageState.ERROR);
    }

    /**
     * 数据为空
     */
    @Override
    public void showDataNull() {
        changePageState(PageState.EMPTY);
    }

    /**
     * 是否允许全屏
     *
     * @param allowFullScreen
     */
    public void setAllowFullScreen(boolean allowFullScreen) {
        this.mAllowFullScreen = allowFullScreen;
    }


    /**
     * 添加 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * 添加 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void addFragment(int containerViewId, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        // 设置tag，不然下面 findFragmentByTag(tag)找不到
        fragmentTransaction.add(containerViewId, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    /**
     * 替换 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void replaceFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 替换 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void replaceFragment(int containerViewId, Fragment fragment, String tag) {
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            // 设置tag
            fragmentTransaction.replace(containerViewId, fragment, tag);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            // 这里要设置tag，上面也要设置tag
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();
        } else {
            // 存在则弹出在它上面的所有fragment，并显示对应fragment
            getSupportFragmentManager().popBackStack(tag, 0);
        }
    }

    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(BaseActivity.this, clz));
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * [含有Bundle通过Class打开编辑界面]
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (ActivityCollector.activities.size() > 0) {
            ActivityCollector.finishAll();
        }
//        ImmersionBar.with(this).destoy();  //不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
//        unregisterReceiver(netBroadcastReceiver);
    }

    private boolean isExit = false; // 是否退出按钮的转态标记

    /**
     * 双击退出程序
     */
    protected void exitBy2click() {
        Timer eExit = null;
        if (isExit == false) {
            isExit = true;
            Toast.makeText(BaseActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            eExit = new Timer();
            eExit.schedule(new TimerTask() {

                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

    //返回键返回事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
 /* private void initNet() {
         event = this;
         //实例化IntentFilter对象
         IntentFilter filter = new IntentFilter();
         filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
         filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
         filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
         filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
         netBroadcastReceiver = new NetBroadcastReceiver();
         registerReceiver(netBroadcastReceiver, filter);
         inspectNet();
     }

    public boolean inspectNet() {
        this.netMobile = NetUtil.getNetWorkState(BaseActivity.this);
        return isNetConnect();
    } */

    /**
     * 判断有无网络 。
     *
     * @return true 有网, false 没有网络.

    public boolean isNetConnect() {
    if (netMobile == -1) {
    return false;
    }
    return true;
    }
     */
}