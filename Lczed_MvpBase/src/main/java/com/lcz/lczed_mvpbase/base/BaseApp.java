package com.lcz.lczed_mvpbase.base;

import android.app.Application;

/**
 * @author: Lczed
 * @date on 2020/11/19 17:23 星期四
 * E-mail: lcz3466601343@163.com
 * Description :
 */
public class BaseApp extends Application {
    public static BaseApp myApp;

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;

    }
    public static Application getInstance(){
        return myApp;
    }
} 