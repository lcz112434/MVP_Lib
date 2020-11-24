package com.lcz.gotest;

import com.lcz.lczed_mvpbase.base.BaseView;

/**
 * @author: Lczed
 * @date on 2020/11/24 12:11 星期二
 * E-mail: lcz3466601343@163.com
 * Description :
 */
interface MyV extends BaseView {


    void showData(TextBean data);

    void showErr(String mag);
}
