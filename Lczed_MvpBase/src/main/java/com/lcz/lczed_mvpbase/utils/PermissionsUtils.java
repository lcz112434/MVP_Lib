package com.lcz.lczed_mvpbase.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.lcz.lczed_mvpbase.base.BasePermissionActivity;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.ExplainReasonCallback;
import com.permissionx.guolindev.callback.ForwardToSettingsCallback;
import com.permissionx.guolindev.callback.RequestCallback;
import com.permissionx.guolindev.request.ExplainScope;
import com.permissionx.guolindev.request.ForwardScope;

import java.util.List;

/**
 * @author: Lczed
 * @date on 2020/11/23 15:18 星期一
 * E-mail: lcz3466601343@163.com
 * Description :
 */
public class PermissionsUtils {
    private Boolean isok = true;

    //获取单个权限
    protected Boolean GetPermissions(FragmentActivity activity, String permission) {
        PermissionX.init(activity)//Fragment传this，Activity传Activity对象。
                //权限列表 可手动添加
                .permissions(permission)
                //用户拒绝权限，需要重新申请权限
                .onExplainRequestReason(new ExplainReasonCallback() {
                    @Override
                    public void onExplainReason(ExplainScope scope, List<String> deniedList) {
                        scope.showRequestReasonDialog(deniedList, "即将重新申请的权限是程序必须依赖的权限", "我已明白", "取消");
                    }
                })
                //用户强制拒绝权限，但是权限是必要的，需要去程序设置中打开。
                .onForwardToSettings(new ForwardToSettingsCallback() {
                    @Override
                    public void onForwardToSettings(ForwardScope scope, List<String> deniedList) {
                        scope.showForwardToSettingsDialog(deniedList, "您需要去应用程序设置当中手动开启权限", "我已明白", "取消");
                    }
                })
                //用户权限授权结果
                .request(new RequestCallback() {
                    @Override
                    public void onResult(boolean allGranted, List<String> grantedList, List<String> deniedList) {
                        if (allGranted) {
                            LogUtils.d("权限授权成功");
                            isok = true;
                        } else {
                            ToastUtil.showShort("权限不通过!");
                            //权限不通过，无法使用
                            isok = false;
                        }
                    }
                });
        return isok;
    } //获取单个权限

    protected Boolean GetPermissions(Fragment activity, String permission) {
        PermissionX.init(activity)//Fragment传this，Activity传Activity对象。
                //权限列表 可手动添加
                .permissions(permission)
                //用户拒绝权限，需要重新申请权限
                .onExplainRequestReason(new ExplainReasonCallback() {
                    @Override
                    public void onExplainReason(ExplainScope scope, List<String> deniedList) {
                        scope.showRequestReasonDialog(deniedList, "即将重新申请的权限是程序必须依赖的权限", "我已明白", "取消");
                    }
                })
                //用户强制拒绝权限，但是权限是必要的，需要去程序设置中打开。
                .onForwardToSettings(new ForwardToSettingsCallback() {
                    @Override
                    public void onForwardToSettings(ForwardScope scope, List<String> deniedList) {
                        scope.showForwardToSettingsDialog(deniedList, "您需要去应用程序设置当中手动开启权限", "我已明白", "取消");
                    }
                })
                //用户权限授权结果
                .request(new RequestCallback() {
                    @Override
                    public void onResult(boolean allGranted, List<String> grantedList, List<String> deniedList) {
                        if (allGranted) {
                            LogUtils.d("权限授权成功");
                            isok = true;
                        } else {
                            ToastUtil.showShort("权限不通过!");
                            //权限不通过，无法使用
                            isok = false;
                        }
                    }
                });
        return isok;
    }
} 