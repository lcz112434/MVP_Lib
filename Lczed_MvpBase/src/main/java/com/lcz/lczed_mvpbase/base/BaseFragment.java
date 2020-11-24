package com.lcz.lczed_mvpbase.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.lcz.lczed_mvpbase.utils.ToastUtil;

/**
 * @author: Lczed
 * @date on 2020/11/19 10:33 星期四
 * E-mail: lcz3466601343@163.com
 * Description :
 */
public abstract class BaseFragment<P extends IBasePresenter> extends Fragment implements IBaseView {

    private Activity mActivity;
    protected P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mActivity != null)
            mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        View view = inflater.inflate(setLayout(), null);
        return view;
    }

    /**
     * 记录是否已经创建了,防止重复创建
     */
    boolean viewCreated = false;

    @Override
    public void showLoading() {

    }

    @Override
    public void showOk() {

    }

    @Override
    public void showNetError() {

    }


    @Override
    public void showDataError() {

    }


    protected abstract P createPresenter();

    protected abstract void initListener();

    protected abstract void initView();

    @Override
    public void onResume() {
        super.onResume();
        //判断是不是第一次加载
        if (!viewCreated && !isHidden()) {

            //初始话方法
            initView();
            initData();
            if (presenter == null) {
                presenter = createPresenter();
                presenter.attachView(this);
            }
            initListener();
            viewCreated = true;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //恢复Fragment默认值
        viewCreated = false;
    }

    protected abstract void initData();

    protected abstract int setLayout();

    /**
     * 简化findViewById
     *
     * @param resId
     * @param <T>
     * @return
     */
    protected <T extends View> T findByid(int resId) {
        return (T) getView().findViewById(resId);
    }

    /**
     * 显示Toast消息
     *
     * @param message 消息文本字符串
     */
    public final void showToast(@NonNull String message) {
        if (!TextUtils.isEmpty(message)) ToastUtil.showShort(message);
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    /**
     * 跳转到指定的Activity
     *
     * @param targetActivity 要跳转的目标Activity
     */
    protected final void startActivity(@NonNull Class<?> targetActivity) {
        startActivity(new Intent(getContext(), targetActivity));
    }

    /**
     * 跳转到指定的Activity
     *
     * @param extraName      要传递的值的键名称
     * @param extraValue     要传递的String类型值
     * @param targetActivity 要跳转的目标Activity
     */
    public final void startActivity(@NonNull String extraName, @NonNull String extraValue, @NonNull Class<?> targetActivity) {
        if (TextUtils.isEmpty(extraName))
            throw new NullPointerException("传递的值的键名称为null或空");
        final Intent intent = new Intent(getContext(), targetActivity);
        intent.putExtra(extraName, extraValue);
        startActivity(intent);
    }

    /**
     * 跳转到指定的Activity
     *
     * @param extraName      要传递的值的键名称
     * @param extraValue     要传递的int类型值
     * @param targetActivity 要跳转的目标Activity
     */
    public final void startActivity(@NonNull String extraName, @NonNull int extraValue, @NonNull Class<?> targetActivity) {
        if (TextUtils.isEmpty(extraName))
            throw new NullPointerException("传递的值的键名称为null或空");
        final Intent intent = new Intent(getContext(), targetActivity);
        intent.putExtra(extraName, extraValue);
        startActivity(intent);
    }

    /**
     * 带返回值的跳转
     *
     * @param context
     * @param clazz
     * @param bundle
     * @param requestCode
     */
    protected void toClass(Context context, Class<? extends BaseActivity> clazz, Bundle bundle, int requestCode) {
        Intent intent = new Intent(context, clazz);
        intent.putExtras(bundle);
        getActivity().startActivityForResult(intent, requestCode);
    }
}