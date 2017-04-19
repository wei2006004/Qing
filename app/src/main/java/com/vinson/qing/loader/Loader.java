package com.vinson.qing.loader;

import com.vinson.qing.utils.BackgroundThread;
import com.vinson.qing.utils.ThreadUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinson on 2017/4/19.
 * e-mail: wei2006004@foxmail.com
 */

public abstract class Loader<T> {
    public interface LoadListener<T> {
        void onLoadDone(int result, int type, List<T> list);
    }

    public void addLoadListener(LoadListener<T> loadListener) {
        listeners.add(loadListener);
    }

    public void removeLoadListener(LoadListener<T> loadListener) {
        listeners.add(loadListener);
    }

    private List<LoadListener<T>> listeners = new ArrayList<>();

    public abstract void loadDatas();

    public abstract List<T> getLastLoadData();

    protected static void post(Runnable runnable) {
        BackgroundThread.post(runnable);
    }

    protected void notifyLoadDone(int result, int type, List<T> list) {
        for (LoadListener<T> loadListener : listeners) {
            loadListener.onLoadDone(result, type, list);
        }
    }

    protected void notifyLoadDoneToMainThread(final int result, final int type, final List<T> list) {
        ThreadUtils.postToMainThread(new Runnable() {
            @Override
            public void run() {
                notifyLoadDone(result, type, list);
            }
        });
    }
}
