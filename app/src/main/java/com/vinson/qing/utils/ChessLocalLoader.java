package com.vinson.qing.utils;

import com.vinson.qing.bean.ChessData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Vinson on 2017/4/18.
 * e-mail: wei2006004@foxmail.com
 */
public class ChessLocalLoader {
    private static ChessLocalLoader ourInstance = new ChessLocalLoader();

    public static ChessLocalLoader getInstance() {
        return ourInstance;
    }

    private AtomicBoolean isDataLoad = new AtomicBoolean(false);

    private ChessLocalLoader() {
    }

    public List<ChessData> getChessDatas() {
        return chessDatas;
    }

    public boolean isDataLoaded() {
        return isDataLoad.get();
    }

    public void loadDatas() {
        BackgroundThread.post(new Runnable() {
            @Override
            public void run() {
                List<ChessData> list = DbUtils.getChessDataList();
                chessDatas.clear();
                chessDatas.addAll(list);
                isDataLoad.set(true);
            }
        });
    }

    public void addChessData(final ChessData chessData) {
        chessDatas.add(0, chessData);
        BackgroundThread.post(new Runnable() {
            @Override
            public void run() {
                DbUtils.saveChessData(chessData);
            }
        });
    }

    public void deleteChessData(final ChessData chessData) {
        chessDatas.remove(chessData);
        BackgroundThread.post(new Runnable() {
            @Override
            public void run() {
                DbUtils.deleteChessData(chessData);
            }
        });
    }

    private List<ChessData> chessDatas = new ArrayList<>();
}
