package com.vinson.qing.bean;

import android.os.Parcel;
import android.os.Parcelable;

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
public class ChessData implements Parcelable{

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

    protected ChessData(Parcel in) {
        id = in.readInt();
        startTime = in.readLong();
        endTime = in.readLong();
        redPlayer = in.readString();
        greenPlayer = in.readString();
        tracks = in.createTypedArrayList(ChessTrack.CREATOR);
    }

    public static final Creator<ChessData> CREATOR = new Creator<ChessData>() {
        @Override
        public ChessData createFromParcel(Parcel in) {
            return new ChessData(in);
        }

        @Override
        public ChessData[] newArray(int size) {
            return new ChessData[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeLong(startTime);
        dest.writeLong(endTime);
        dest.writeString(redPlayer);
        dest.writeString(greenPlayer);
        dest.writeTypedList(tracks);
    }
}
