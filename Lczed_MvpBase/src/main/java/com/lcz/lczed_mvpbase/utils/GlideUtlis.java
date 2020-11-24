package com.lcz.lczed_mvpbase.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.lcz.lczed_mvpbase.R;

/**
 * @author: Lczed
 * @date on 2020/11/24 14:41 星期二
 * E-mail: lcz3466601343@163.com
 * Description :
 */
public class GlideUtlis {
    /*
    1.默认加载
     * */
    public static void loadImageView(Context context, String Url, ImageView view) {
        Glide.with(context).load(Url).into(view);
    }

    public static void loadImageView(Context context, int srcid, ImageView view) {
        Glide.with(context).load(srcid).into(view);
    }

    public static void loadImageView(Context context, Drawable Url, ImageView view) {
        Glide.with(context).load(Url).into(view);
    }

    public static void loadImageView(Context context, Bitmap Url, ImageView view) {
        Glide.with(context).load(Url).into(view);
    }

    //2.加载指定大小
    public static void loadImageViewSize(Context mContext, String path, int width, int height, ImageView mImageView) {
        Glide.with(mContext).load(path).override(width, height).into(mImageView);
    }

    //3.设置加载中以及加载失败图片
    public static void loadImageViewLoding(Context mContext, String path, ImageView mImageView, int lodingImage, int errorImageView) {
        Glide.with(mContext).load(path).placeholder(lodingImage).error(errorImageView).into(mImageView);
    }

    //4.设置加载中以及加载失败图片并且指定大小
    public static void loadImageViewLodingSize(Context mContext, String path, int width, int height, ImageView mImageView, int lodingImage, int errorImageView) {
        Glide.with(mContext).load(path).override(width, height).placeholder(lodingImage).error(errorImageView).into(mImageView);
    }

    //5.设置跳过内存缓存
    public static void loadImageViewCache(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).skipMemoryCache(true).into(mImageView);
    }

    /*7.设置缓存策略
        策略分为：

        all:缓存源资源和转换后的资源
        none:不作任何磁盘缓存
        source:缓存源资源
        result：缓存转换后的资源*/
    public static void loadImageViewDiskCache(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).diskCacheStrategy(DiskCacheStrategy.ALL).into(mImageView);
    }


    //8.设置缩略图支持
    public static void loadImageViewThumbnail(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).thumbnail(0.1f).into(mImageView);
    }

    //9.设置占位图
    public static void loadImageViewDynamicGif(Context mContext, String path, int img, ImageView mImageView) {
        Glide.with(mContext)
                .load(path)
                .placeholder(img)
                .into(mImageView);

    }

    public static void loadCireVIew(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(mImageView);
    }

} 