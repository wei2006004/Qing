package com.vinson.qing.loader;

import com.vinson.qing.bean.ChessData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Vinson on 2017/4/19.
 * e-mail: wei2006004@foxmail.com
 */

public class ChessNetworkLoader extends Loader<ChessData> {

    private Retrofit retrofit;

    public ChessNetworkLoader() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.60.104.60:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Override
    public void loadDatas() {
        ChessDataService service = retrofit.create(ChessDataService.class);
        Observable<List<ChessData>> observable = service.getChessList();
        observable.map(new Func1<List<ChessData>, List<ChessData>>(){
            @Override
            public List<ChessData> call(List<ChessData> list) {
                chessDatas.clear();
                chessDatas.addAll(list);
                return list;
            }
        }).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<List<ChessData>>() {
            @Override
            public void call(List<ChessData> list) {
                notifyLoadDone(0, 0, chessDatas);
            }
        });
    }

    @Override
    public List<ChessData> getLastLoadData() {
        return chessDatas;
    }

    private List<ChessData> chessDatas = new ArrayList<>();
}
