package com.vinson.qing.manual;

import com.vinson.qing.bean.ChessData;
import com.vinson.qing.utils.BackgroundThread;
import com.vinson.qing.utils.DbUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Vinson on 2017/4/18.
 * e-mail: wei2006004@foxmail.com
 */
public class ChessLocalLoader extends Loader<ChessData> {
    private static ChessLocalLoader ourInstance = new ChessLocalLoader();

    public static ChessLocalLoader getInstance() {
        return ourInstance;
    }

    private ChessLocalLoader() {
    }

    @Override
    public void loadDatas() {
        post(new Runnable() {
            @Override
            public void run() {
                List<ChessData> list = DbUtils.getChessDataList();
                chessDatas.clear();
                chessDatas.addAll(list);
                notifyLoadDoneToMainThread(0, 0, chessDatas);
            }
        });
    }

    @Override
    public List<ChessData> getLastLoadData() {
        return chessDatas;
    }

    private List<ChessData> chessDatas = new ArrayList<>();
}
