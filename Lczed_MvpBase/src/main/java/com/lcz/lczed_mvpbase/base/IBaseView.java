package com.lcz.lczed_mvpbase.base;

/**
 * @author: Lczed
 * @date on 2020/11/19 10:33 星期四
 * E-mail: lcz3466601343@163.com
 * Description : 基类View接口
 */
public interface IBaseView {
    /**
     * 显示加载动画
     */
    void showLoading();

    /**
     * 加载成功，隐藏加载
     */
    void showOk();

    /**
     * 显示网络错误，网络异常在 BaseActivity 和 BaseFragment 统一处理
     */
    void showNetError();

    /**
     * 显示数据加载错误
     */
    void showDataError();

    /**
     * 显示数据为空
     */

    void showDataNull();
}