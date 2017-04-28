package com.vinson.qing.utils;

import com.vinson.qing.QingApplication;
import com.vinson.qing.bean.ChessData;
import com.vinson.qing.bean.ChessTrack;

import org.greenrobot.greendao.rx.RxDao;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action2;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Vinson on 2017/4/19.
 * e-mail: wei2006004@foxmail.com
 */

public class DbService {

    public static void saveChessData(final ChessData chessData, Observer<Object> callback) {
        RxDao<ChessData, Long> dataDao = QingApplication.getApp().getSession().getChessDataDao().rx();
        Observable<Object> observable = dataDao.insert(chessData)
                .flatMap(new Func1<ChessData, Observable<ChessTrack>>() {
                    @Override
                    public Observable<ChessTrack> call(ChessData chessData) {
                        return Observable.from(chessData.getTempTracks());
                    }
                })
                .map(new Func1<ChessTrack, Object>() {
                    @Override
                    public Object call(ChessTrack track) {
                        return QingApplication.getApp().getSession().insert(track);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        if (callback == null) {
            observable.subscribe();
        } else {
            observable.subscribe(callback);
        }
    }

    public static void getChessDataList(final Action2<List<ChessData>, Integer> action2) {
        final List<ChessData> result = new ArrayList<>();
        RxDao<ChessData, Long> dataDao = QingApplication.getApp().getSession().getChessDataDao().rx();
        dataDao.loadAll().flatMap(new Func1<List<ChessData>, Observable<ChessData>>() {
            @Override
            public Observable<ChessData> call(List<ChessData> list) {
                result.addAll(list);
                return Observable.from(list);
            }
        }).flatMap(new Func1<ChessData, Observable<ChessTrack>>() {
            @Override
            public Observable<ChessTrack> call(ChessData chessData) {
                return Observable.from(chessData.getTracks());
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new ObserverAdapter<ChessTrack>() {
            @Override
            public void onCompleted() {
                action2.call(result, 0);
            }

            @Override
            public void onError(Throwable e) {
                action2.call(result, 1);
            }
        });
    }
}
