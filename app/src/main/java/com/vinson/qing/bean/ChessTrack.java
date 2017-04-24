package com.vinson.qing.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.vinson.qing.utils.ChessUtils;

/**
 * Created by Vinson on 2017/4/16.
 * e-mail: wei2006004@foxmail.com
 */

@DatabaseTable(tableName = "chess_track")
public class ChessTrack implements Parcelable{
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

    public Chess getChess() {
        return ChessUtils.intToChess(chessId);
    }

    public ChessTrack(ChessData chessData, int fromx, int fromy, int tox, int toy, int chessId) {
        this.chessData = chessData;
        this.fromx = fromx;
        this.fromy = fromy;
        this.tox = tox;
        this.toy = toy;
        this.chessId = chessId;
    }

    public ChessTrack() {
    }

    protected ChessTrack(Parcel in) {
        id = in.readInt();
        fromx = in.readInt();
        fromy = in.readInt();
        tox = in.readInt();
        toy = in.readInt();
        chessId = in.readInt();
    }

    public static final Creator<ChessTrack> CREATOR = new Creator<ChessTrack>() {
        @Override
        public ChessTrack createFromParcel(Parcel in) {
            return new ChessTrack(in);
        }

        @Override
        public ChessTrack[] newArray(int size) {
            return new ChessTrack[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(fromx);
        dest.writeInt(fromy);
        dest.writeInt(tox);
        dest.writeInt(toy);
        dest.writeInt(chessId);
    }
}
