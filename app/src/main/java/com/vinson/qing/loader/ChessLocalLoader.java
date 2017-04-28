package com.vinson.qing.loader;

import com.vinson.qing.bean.ChessData;
import com.vinson.qing.utils.DbService;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action2;

/**
 * Created by Vinson on 2017/4/18.
 * e-mail: wei2006004@foxmail.com
 */
public class ChessLocalLoader extends Loader<ChessData> {

    @Override
    public void loadDatas() {
        DbService.getChessDataList(new Action2<List<ChessData>, Integer>() {
            @Override
            public void call(List<ChessData> list, Integer integer) {
                if (integer == 0) {
                    chessDatas.clear();
                    chessDatas.addAll(list);
                }
                notifyLoadDone(integer, 0, chessDatas);
            }
        });
    }

    @Override
    public List<ChessData> getLastLoadData() {
        return chessDatas;
    }

    private List<ChessData> chessDatas = new ArrayList<>();
}
