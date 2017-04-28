package com.vinson.qing.loader;

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

    protected void notifyLoadDone(int result, int type, List<T> list) {
        for (LoadListener<T> loadListener : listeners) {
            loadListener.onLoadDone(result, type, list);
        }
    }
}
