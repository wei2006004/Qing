package com.vinson.qing.bean;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

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

    public void addTrack(ChessTrack track) {
        tracks.add(track);
    }

    public List<ChessTrack> getTracks() {
        return tracks;
    }

    public void setTracks(List<ChessTrack> tracks) {
        this.tracks = tracks;
    }
}
