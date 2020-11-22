package com.lcz.lczed_mvpbase.utils;

import android.util.Log;

/**
 * @author 承泽
 * @date 2020/8/25 13:55
 * @Description:
 */
public class LogUtils {

    private static String getSimpleClassName(String name) {
        int lastIndex = name.lastIndexOf(".");
        return name.substring(lastIndex + 1);
    }


    public static void i(String msg) {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();

        int methodCount = 1;
        int stackOffset = getStackOffset(trace);

        if (methodCount + stackOffset > trace.length) {
            methodCount = trace.length - stackOffset - 1;
        }

        for (int i = methodCount; i > 0; i--) {
            int stackIndex = i + stackOffset;
            if (stackIndex >= trace.length) {
                continue;
            }
            StackTraceElement element = trace[stackIndex];

            StringBuilder builder = new StringBuilder();
            builder.append(getSimpleClassName(element.getClassName()))
                    .append(".")
                    .append(element.getMethodName())
                    .append(" ")
                    .append(" (")
                    .append(element.getFileName())
                    .append(":")
                    .append(element.getLineNumber())
                    .append(")")
                    .append("打印信息：----->")
                    .append(msg);

            Log.i("LCZLOG_I", builder.toString());

        }
    }

    public static void v(String msg) {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();

        int methodCount = 1;
        int stackOffset = getStackOffset(trace);

        if (methodCount + stackOffset > trace.length) {
            methodCount = trace.length - stackOffset - 1;
        }

        for (int i = methodCount; i > 0; i--) {
            int stackIndex = i + stackOffset;
            if (stackIndex >= trace.length) {
                continue;
            }
            StackTraceElement element = trace[stackIndex];

            StringBuilder builder = new StringBuilder();
            builder.append(getSimpleClassName(element.getClassName()))
                    .append(".")
                    .append(element.getMethodName())
                    .append(" ")
                    .append(" (")
                    .append(element.getFileName())
                    .append(":")
                    .append(element.getLineNumber())
                    .append(")")
                    .append("运行日志：----->")
                    .append(msg);

            Log.v("LCZLOG_V", builder.toString());

        }
    }

    public static void w(String msg) {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();

        int methodCount = 1;
        int stackOffset = getStackOffset(trace);

        if (methodCount + stackOffset > trace.length) {
            methodCount = trace.length - stackOffset - 1;
        }

        for (int i = methodCount; i > 0; i--) {
            int stackIndex = i + stackOffset;
            if (stackIndex >= trace.length) {
                continue;
            }
            StackTraceElement element = trace[stackIndex];

            StringBuilder builder = new StringBuilder();
            builder.append(getSimpleClassName(element.getClassName()))
                    .append(".")
                    .append(element.getMethodName())
                    .append(" ")
                    .append(" (")
                    .append(element.getFileName())
                    .append(":")
                    .append(element.getLineNumber())
                    .append(")")
                    .append("打印日志：----->")
                    .append(msg);

            Log.w("LCZLOG_W", builder.toString());

        }
    }

    public static void e(String msg) {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();

        int methodCount = 1;
        int stackOffset = getStackOffset(trace);

        if (methodCount + stackOffset > trace.length) {
            methodCount = trace.length - stackOffset - 1;
        }

        for (int i = methodCount; i > 0; i--) {
            int stackIndex = i + stackOffset;
            if (stackIndex >= trace.length) {
                continue;
            }
            StackTraceElement element = trace[stackIndex];

            StringBuilder builder = new StringBuilder();
            builder.append(getSimpleClassName(element.getClassName()))
                    .append(".")
                    .append(element.getMethodName())
                    .append(" ")
                    .append(" (")
                    .append(element.getFileName())
                    .append(":")
                    .append(element.getLineNumber())
                    .append(")")
                    .append("出现Error：----->")
                    .append(msg);

            Log.e("LCZLOG_E", builder.toString());

        }
    }

    public static void d(String msg) {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();

        int methodCount = 1;
        int stackOffset = getStackOffset(trace);

        if (methodCount + stackOffset > trace.length) {
            methodCount = trace.length - stackOffset - 1;
        }

        for (int i = methodCount; i > 0; i--) {
            int stackIndex = i + stackOffset;
            if (stackIndex >= trace.length) {
                continue;
            }
            StackTraceElement element = trace[stackIndex];

            StringBuilder builder = new StringBuilder();
            builder.append(getSimpleClassName(element.getClassName()))
                    .append(".")
                    .append(element.getMethodName())
                    .append(" ")
                    .append(" (")
                    .append(element.getFileName())
                    .append(":")
                    .append(element.getLineNumber())
                    .append(")")
                    .append("debug日志：----->")
                    .append(msg);

            Log.d("LCZLOG_D", builder.toString());

        }
    }

    private static int getStackOffset(StackTraceElement[] trace) {
        for (int i = 2; i < trace.length; i++) {
            StackTraceElement e = trace[i];
            String name = e.getClassName();
            if (!name.equals(LogUtils.class.getName())) {
                return --i;
            }
        }
        return -1;
    }
} 