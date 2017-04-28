package com.vinson.qing.utils;

import rx.Observer;

/**
 * Created by Vinson on 2017/4/28.
 * e-mail: wei2006004@foxmail.com
 */

public class ObserverAdapter<T> implements Observer<T> {
    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onNext(T t) {
    }
}
