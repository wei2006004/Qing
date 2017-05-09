package com.vinson.qing.loader;

import com.vinson.qing.bean.ChessData;
import com.vinson.qing.bean.ChessTrack;
import com.vinson.qing.utils.ChessUtils;
import com.vinson.qing.utils.ObserverAdapter;
import com.vinson.qing.utils.ServerConnect;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Override
    public void loadDatas() {
        ChessDataService service = ServerConnect.getRetrofit().create(ChessDataService.class);
        Observable<List<ChessRecord>> observable = service.getChessList();
        observable.map(new Func1<List<ChessRecord>, List<ChessData>>(){
            @Override
            public List<ChessData> call(List<ChessRecord> list) {
                List<ChessData> datas = convertRecordToDatas(list);
                chessDatas.clear();
                chessDatas.addAll(datas);
                return datas;
            }
        }).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new ObserverAdapter<List<ChessData>>() {
            @Override
            public void onCompleted() {
                notifyLoadDone(0, 0, chessDatas);
            }

            @Override
            public void onError(Throwable e) {
                notifyLoadDone(1, 0, chessDatas);
            }
        });
    }

    private List<ChessData> convertRecordToDatas(List<ChessRecord> list) {
        List<ChessData> datas = new ArrayList<>();
        for (ChessRecord record : list) {
            ChessData data = new ChessData();
            data.redPlayer = record.redPlayer;
            data.greenPlayer = record.blackPlayer;
            data.startTime = parseDate(record.date);
            data.endTime = new Date();
            data.id = 0l;
            for (String string: record.getMoveList()) {
                ChessTrack track = ChessUtils.moveResultToTask(string);
                data.addTrack(null, track.fromx, track.fromy, track.tox, track.toy);
            }
            datas.add(data);
        }
        return datas;
    }

    static Date parseDate(String string) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date date = null;
        try {
            date = format.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    @Override
    public List<ChessData> getLastLoadData() {
        return chessDatas;
    }

    private List<ChessData> chessDatas = new ArrayList<>();
}
