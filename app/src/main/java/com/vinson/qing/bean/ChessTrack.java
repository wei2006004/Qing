package com.vinson.qing.bean;


import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;

/**
 * Created by Vinson on 2017/4/16.
 * e-mail: wei2006004@foxmail.com
 */

@Entity(indexes = {
        @Index("index ASC")
})
public class ChessTrack implements Parcelable{
    @Id(autoincrement = true)
    public Long id;

    public long dataId;

    public int fromx;

    public int fromy;
    
    public int tox;

    public int toy;

    public int index;

    @Convert(converter = ChessConverter.class, columnType = Integer.class)
    public Chess chess;

    @Generated(hash = 1007825287)
    public ChessTrack(Long id, long dataId, int fromx, int fromy, int tox, int toy,
            int index, Chess chess) {
        this.id = id;
        this.dataId = dataId;
        this.fromx = fromx;
        this.fromy = fromy;
        this.tox = tox;
        this.toy = toy;
        this.index = index;
        this.chess = chess;
    }

    @Generated(hash = 1593646543)
    public ChessTrack() {
    }

    protected ChessTrack(Parcel in) {
        id = in.readLong();
        dataId = in.readLong();
        fromx = in.readInt();
        fromy = in.readInt();
        tox = in.readInt();
        toy = in.readInt();
        index = in.readInt();
//        chess = in.readParcelable(Chess.class.getClassLoader());
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getDataId() {
        return this.dataId;
    }

    public void setDataId(long dataId) {
        this.dataId = dataId;
    }

    public int getFromx() {
        return this.fromx;
    }

    public void setFromx(int fromx) {
        this.fromx = fromx;
    }

    public int getFromy() {
        return this.fromy;
    }

    public void setFromy(int fromy) {
        this.fromy = fromy;
    }

    public int getTox() {
        return this.tox;
    }

    public void setTox(int tox) {
        this.tox = tox;
    }

    public int getToy() {
        return this.toy;
    }

    public void setToy(int toy) {
        this.toy = toy;
    }

    public Chess getChess() {
        return this.chess;
    }

    public void setChess(Chess chess) {
        this.chess = chess;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(dataId);
        dest.writeInt(fromx);
        dest.writeInt(fromy);
        dest.writeInt(tox);
        dest.writeInt(toy);
        dest.writeInt(index);
//        dest.writeParcelable(chess, flags);
    }
}
