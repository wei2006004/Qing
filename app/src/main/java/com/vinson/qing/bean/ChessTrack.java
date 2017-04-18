package com.vinson.qing.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Vinson on 2017/4/16.
 * e-mail: wei2006004@foxmail.com
 */

@DatabaseTable(tableName = "chess_track")
public class ChessTrack {
    @DatabaseField(generatedId = true)
    public int id;

    @DatabaseField(foreign=true, foreignAutoRefresh=true)
    public ChessData chessData;

    @DatabaseField
    public int fromx;

    @DatabaseField
    public int fromy;

    @DatabaseField
    public int tox;

    @DatabaseField
    public int toy;

    @DatabaseField
    public int chessId;

    public ChessTrack(ChessData chessData, int fromx, int fromy, int tox, int toy) {
        this.chessData = chessData;
        this.fromx = fromx;
        this.fromy = fromy;
        this.tox = tox;
        this.toy = toy;
    }

    public ChessTrack() {
    }
}
