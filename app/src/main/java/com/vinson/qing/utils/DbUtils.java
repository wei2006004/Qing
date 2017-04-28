package com.vinson.qing.utils;

import android.content.Context;

import com.vinson.qing.QingApplication;
import com.vinson.qing.bean.ChessData;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinson on 2017/4/16.
 * e-mail: wei2006004@foxmail.com
 */

public class DbUtils {

    public static void saveChessData(ChessData chessData) {
//        try {
//            DbHelper.getInstance(getContext()).getChessDataDao().create(chessData);
//            List<ChessTrack> list = chessData.getTracks();
//            for (ChessTrack track : list) {
//                DbHelper.getInstance(getContext()).getChessTrackDao().create(track);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public static void deleteChessData(ChessData chessData) {
//        try {
//            CloseableIterator<ChessTrack> iterator = chessData.dbTracks.closeableIterator();
//            while (iterator.hasNext()) {
//                DbHelper.getInstance(getContext()).getChessTrackDao().delete(iterator.nextThrow());
//            }
//            iterator.closeQuietly();
//            DbHelper.getInstance(getContext()).getChessDataDao().delete(chessData);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public static List<ChessData> getChessDataList() {
        List<ChessData> list = new ArrayList<>();
//        try {
//            list = DbHelper.getInstance(getContext()).getChessDataDao().queryForAll();
//            for (ChessData data : list) {
//                loadChessTracks(data);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return list;
    }

    private static void loadChessTracks(ChessData data) throws SQLException {
//        CloseableIterator<ChessTrack> iterator = data.dbTracks.closeableIterator();
//        while (iterator.hasNext()) {
//            data.addTrack(iterator.nextThrow());
//        }
//        iterator.closeQuietly();
    }

    private static Context getContext() {
        return QingApplication.getContext();
    }
}
