package com.lcz.lczed_mvpbase.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

public class ThemeRelativeLayout extends RelativeLayout implements IThemeUI {
    private int attr_bg = -1;

    public ThemeRelativeLayout(Context context) {
        super(context);
    }

    public ThemeRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attr_bg = ViewAttributeUtil.getBgAttribute(attrs);
    }

    public ThemeRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attr_bg = ViewAttributeUtil.getBgAttribute(attrs);
    }

    public ThemeRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.attr_bg = ViewAttributeUtil.getBgAttribute(attrs);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setTheme(Resources.Theme themeid) {
        if(attr_bg != -1){
            ViewAttributeUtil.applyBgDrawable(this,themeid,attr_bg);
        }
    }
}
