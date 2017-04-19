package com.vinson.qing.loader;

import com.vinson.qing.bean.ChessData;
import com.vinson.qing.utils.DbUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinson on 2017/4/18.
 * e-mail: wei2006004@foxmail.com
 */
public class ChessLocalLoader extends Loader<ChessData> {

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
