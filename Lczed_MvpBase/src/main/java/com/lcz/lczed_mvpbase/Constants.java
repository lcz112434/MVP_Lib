package com.lcz.lczed_mvpbase;

import android.os.Environment;

import com.lcz.lczed_mvpbase.base.BaseApp;

import java.io.File;

/**
 * Created by asus on 2019/3/5.
 */

public interface Constants {

    //网络缓存的地址
    String PATH_DATA = BaseApp.getInstance().getCacheDir().getAbsolutePath() +
            File.separator + "data";

    String PATH_CACHE = PATH_DATA + "/NetCache";
}
