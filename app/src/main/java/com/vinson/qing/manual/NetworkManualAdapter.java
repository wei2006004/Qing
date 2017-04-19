package com.vinson.qing.manual;

import android.content.Context;

import com.vinson.qing.bean.ChessData;
import com.vinson.qing.loader.ChessNetworkLoader;
import com.vinson.qing.loader.Loader;

import java.util.List;

/**
 * Created by Vinson on 2017/4/19.
 * e-mail: wei2006004@foxmail.com
 */

public class NetworkManualAdapter extends ManualAdapter {
    public NetworkManualAdapter(Context context) {
        super(context);
    }

    @Override
    public Loader<ChessData> getLoader() {
        return ChessNetworkLoader.getInstance();
    }

    @Override
    public void onLoadDone(int result, int type, List<ChessData> list) {

    }
}
