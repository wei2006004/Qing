package com.vinson.qing.manual;

import android.content.Context;

import com.vinson.qing.bean.ChessData;
import com.vinson.qing.loader.ChessLocalLoader;
import com.vinson.qing.loader.Loader;

import java.util.List;

/**
 * Created by Vinson on 2017/4/19.
 * e-mail: wei2006004@foxmail.com
 */

public class LocalManualAdapter extends ManualAdapter {

    private Loader<ChessData> loader;

    @Override
    public Loader<ChessData> getLoader() {
        if (loader == null) {
            loader = new ChessLocalLoader();
        }
        return loader;
    }

    public LocalManualAdapter(Context context) {
        super(context);
    }

    @Override
    public void onLoadDone(int result, int type, List<ChessData> list) {
        setChessDatas(list);
    }
}
