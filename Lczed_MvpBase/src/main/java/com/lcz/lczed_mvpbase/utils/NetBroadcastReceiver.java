package com.lcz.lczed_mvpbase.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.lcz.lczed_mvpbase.base.BaseActivity;

public class NetBroadcastReceiver extends BroadcastReceiver {
 
    public NetEvent event= BaseActivity.event;
 
    @Override
    public void onReceive(Context context, Intent intent) {
        // 如果相等的话就说明网络状态发生了变化
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            int netWorkState = NetUtil.getNetWorkState(context);
            // 接口回调传过去状态的类型
            event.onNetChange(netWorkState);
        }
    }
 
    //自定义网络切换接口
    public interface NetEvent {
        public void onNetChange(int netMobile);
    }
 
}