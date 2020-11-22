package com.lcz.lczed_mvpbase.widget;

import android.content.res.Resources;
import android.view.View;

public interface IThemeUI {

    //获取界面容器
    View getView();
    //设置样式
    void setTheme(Resources.Theme themeid);

}
