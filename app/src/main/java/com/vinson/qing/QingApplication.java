package com.vinson.qing;

import android.app.Application;
import android.content.Context;

import com.vinson.qing.manual.ChessLocalLoader;

/**
 * Created by Vinson on 2017/4/11.
 * e-mail: wei2006004@foxmail.com
 */

public class QingApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setContext(this);
        ChessLocalLoader.getInstance().loadDatas();
    }

    private static Context sContext;

    public static Context getContext() {
        return sContext;
    }

    private static void setContext(Context context) {
        sContext = context;
    }
}
