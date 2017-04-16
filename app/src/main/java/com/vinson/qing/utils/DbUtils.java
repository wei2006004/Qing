package com.vinson.qing.utils;

import android.content.Context;

import com.vinson.qing.QingApplication;
import com.vinson.qing.bean.ChessData;
import com.vinson.qing.bean.DbHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinson on 2017/4/16.
 * e-mail: wei2006004@foxmail.com
 */

public class DbUtils {

    public static void saveChessData(ChessData chessData) {
        try {
            DbHelper.getInstance(getContext()).getChessDataDao().create(chessData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<ChessData> getChessDataList() {
        List<ChessData> list = new ArrayList<>();
        try {
            list = DbHelper.getInstance(getContext()).getChessDataDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static Context getContext() {
        return QingApplication.getContext();
    }
}
