package com.vinson.qing.bean;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Administrator on 2017/4/7.
 */

public class DbHelper extends OrmLiteSqliteOpenHelper {
    private static final String DB_NAME = "db_qing";

    private Dao<ChessData, Integer> chatDao;
    private Dao<ChessTrack, Integer> chatMessageDao;

    private DbHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    private static DbHelper sInstance;

    public static DbHelper getInstance(Context context) {
        if (sInstance == null) {
            synchronized (DbHelper.class) {
                if (sInstance == null) sInstance = new DbHelper(context);
            }
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, ChessTrack.class);
            TableUtils.createTable(connectionSource, ChessData.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, ChessTrack.class, true);
            TableUtils.dropTable(connectionSource, ChessData.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized Dao<ChessData, Integer> getChessDataDao() throws SQLException {
        if (chatDao == null) {
            chatDao = getDao(ChessData.class);
        }
        return chatDao;
    }

    public synchronized Dao<ChessTrack, Integer> getChessTrackDao() throws SQLException {
        if (chatMessageDao == null) {
            chatMessageDao = getDao(ChessTrack.class);
        }
        return chatMessageDao;
    }

    @Override
    public void close() {
        super.close();
        chatDao = null;
        chatMessageDao = null;
    }
}
