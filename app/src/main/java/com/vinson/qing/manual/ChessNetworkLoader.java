package com.vinson.qing.manual;

import com.vinson.qing.bean.ChessData;

import java.util.List;

/**
 * Created by Vinson on 2017/4/19.
 * e-mail: wei2006004@foxmail.com
 */

public class ChessNetworkLoader extends Loader<ChessData> {

    private static ChessNetworkLoader ourInstance = new ChessNetworkLoader();

    public static ChessNetworkLoader getInstance() {
        return ourInstance;
    }

    private ChessNetworkLoader() {
    }

    @Override
    public void loadDatas() {
    }

    @Override
    public List<ChessData> getLastLoadData() {
        return null;
    }
}
