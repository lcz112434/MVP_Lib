package com.lcz.lczed_mvpbase.utils;

import android.content.Context;
import android.widget.Toast;

import com.lcz.lczed_mvpbase.base.BaseApp;


/**
 * Toast工具类
 */
public class ToastUtil {
    private static Toast toast;
    /**
     *
     * @param msg
     */
    public static void showShort(String msg){
        //避免内存泄漏的一个方法,用到上下文的地方,能用application的就application
        Toast.makeText(BaseApp.getInstance(),msg,Toast.LENGTH_SHORT).show();
    }
    public static void showLong(String msg){
        //避免内存泄漏的一个方法,用到上下文的地方,能用application的就application
        Toast.makeText(BaseApp.getInstance(),msg,Toast.LENGTH_LONG).show();
    }


}
