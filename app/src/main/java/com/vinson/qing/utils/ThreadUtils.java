package com.vinson.qing.utils;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by Vinson on 2017/4/19.
 * e-mail: wei2006004@foxmail.com
 */

public class ThreadUtils {

    private static Handler mainHandler;

    static {
        mainHandler = new Handler(Looper.getMainLooper());
    }

    public static void postToMainThread(Runnable runnable) {
        mainHandler.post(runnable);
    }
}
