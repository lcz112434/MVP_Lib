package com.lcz.lczed_mvpbase.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;
/**
 * @author: Lczed
 * @date on 2020/11/19 15:24 星期四
 * E-mail: lcz3466601343@163.com
 * Description : Actitvity管理器
 */
public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<>();

    //添加Activity
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    //移除Activity
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    //移除全部Activity
    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

}