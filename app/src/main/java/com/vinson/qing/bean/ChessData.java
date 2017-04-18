package com.vinson.qing.bean;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.vinson.qing.utils.ChessUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinson on 2017/4/16.
 * e-mail: wei2006004@foxmail.com
 */

@DatabaseTable(tableName = "chess_data")
public class ChessData {

    @DatabaseField(generatedId = true)
    public int id;

    @ForeignCollectionField
    public ForeignCollection<ChessTrack> dbTracks;

    @DatabaseField
    public long startTime;

    @DatabaseField
    public long endTime;

    @DatabaseField
    public String redPlayer;

    @DatabaseField
    public String greenPlayer;

    private List<ChessTrack> tracks = new ArrayList<>();

    public ChessData() {
    }

    public void addTrack(Chess chess, int fromx, int fromy, int tox, int toy) {
        ChessTrack track = new ChessTrack();
        track.chessData = this;
        track.chessId = ChessUtils.chessToInt(chess);
        track.fromx = fromx;
        track.fromy = fromy;
        track.tox = tox;
        track.toy = toy;
        tracks.add(track);
    }

    public void addTrack(ChessTrack track) {
        track.chessData = this;
        tracks.add(track);
    }

    public List<ChessTrack> getTracks() {
        return tracks;
    }

    public void setTracks(List<ChessTrack> tracks) {
        this.tracks = tracks;
    }
}
