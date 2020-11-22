package com.lcz.lczed_mvpbase.widget;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.lcz.lczed_mvpbase.R;

public class ViewAttributeUtil {

    /**
     * 设置样式背景
     *
     * @param themeUI
     * @param theme
     * @param paramInt
     */
    public static void applyBgDrawable(IThemeUI themeUI, Resources.Theme theme, int paramInt) {
        TypedArray ta = theme.obtainStyledAttributes(new int[]{paramInt});
        Drawable drawable = ta.getDrawable(0);
        if (null != themeUI) {
            themeUI.getView().setBackgroundDrawable(drawable);
        }
        ta.recycle();
    }

    /**
     * 获取属性值
     *
     * @param attr
     * @param paramInt
     * @return
     */
    public static int getAttributeValue(AttributeSet attr, int paramInt) {
        int value = -1;
        int count = attr.getAttributeCount();
        for (int i = 0; i < count; i++) {
            if (attr.getAttributeNameResource(i) == paramInt) {
                String str = attr.getAttributeValue(i);
                if (null != str && str.startsWith("?")) {
                    value = Integer.valueOf(str.substring(1, str.length())).intValue();
                    return value;
                }
            }
        }
        return value;
    }

    /**
     * 获取背景属性
     *
     * @param attr
     * @return
     */
    public static int getBgAttribute(AttributeSet attr) {
        return getAttributeValue(attr, R.attr.background);
    }


}
