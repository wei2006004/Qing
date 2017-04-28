package com.vinson.qing;

import android.app.Application;
import android.content.Context;

import com.vinson.qing.bean.DaoMaster;
import com.vinson.qing.bean.DaoSession;

/**
 * Created by Vinson on 2017/4/11.
 * e-mail: wei2006004@foxmail.com
 */

public class QingApplication extends Application {

    public DaoSession getSession() {
        return daoSession;
    }

    DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        setApp(this);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "qing-db");
        daoSession = new DaoMaster(helper.getWritableDb()).newSession();
    }

    private static QingApplication sApp;

    public static Context getContext() {
        return sApp;
    }

    public static QingApplication getApp() {
        return sApp;
    }

    private static void setApp(QingApplication context) {
        sApp = context;
    }
}
