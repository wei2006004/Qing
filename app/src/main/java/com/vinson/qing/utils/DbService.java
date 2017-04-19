package com.vinson.qing.utils;

import com.vinson.qing.bean.ChessData;

/**
 * Created by Vinson on 2017/4/19.
 * e-mail: wei2006004@foxmail.com
 */

public class DbService {

    public static void saveChessData(final ChessData chessData) {
        post(new Runnable() {
            @Override
            public void run() {
                DbUtils.saveChessData(chessData);
            }
        });
    }

    public static void deleteChessData(final ChessData chessData) {
        post(new Runnable() {
            @Override
            public void run() {
                DbUtils.deleteChessData(chessData);
            }
        });
    }

    private static void post(Runnable runnable) {
        BackgroundThread.post(runnable);
    }
}
