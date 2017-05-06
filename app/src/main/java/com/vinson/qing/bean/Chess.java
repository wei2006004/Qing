package com.vinson.qing.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.vinson.qing.R;
import com.vinson.qing.utils.ChessUtils;
import com.vinson.qing.verify.BingVerify;
import com.vinson.qing.verify.ChessVerify;
import com.vinson.qing.verify.JiangVerify;
import com.vinson.qing.verify.JuVerify;
import com.vinson.qing.verify.MaVerify;
import com.vinson.qing.verify.PaoVerify;
import com.vinson.qing.verify.ShiVerify;
import com.vinson.qing.verify.XiangVerify;

/**
 * Created by Vinson on 2017/4/11.
 * e-mail: wei2006004@foxmail.com
 */

public enum Chess implements Parcelable{

    JIANG_R(Chess.TYPE_RED, "帅", new JiangVerify(Chess.TYPE_RED), R.drawable.rb, 'K'),
    XIANG_R(Chess.TYPE_RED, "相", new XiangVerify(Chess.TYPE_RED), R.drawable.rx, 'B'),
    SHI_R(Chess.TYPE_RED, "士", new ShiVerify(Chess.TYPE_RED), R.drawable.rs, 'A'),
    MA_R(Chess.TYPE_RED, "马", new MaVerify(Chess.TYPE_RED), R.drawable.rm, 'N'),
    JU_R(Chess.TYPE_RED, "車", new JuVerify(Chess.TYPE_RED), R.drawable.rj, 'R'),
    PAO_R(Chess.TYPE_RED, "炮", new PaoVerify(Chess.TYPE_RED), R.drawable.rp, 'C'),
    BING_R(Chess.TYPE_RED, "兵", new BingVerify(Chess.TYPE_RED), R.drawable.rz, 'P'),

    JIANG_B(Chess.TYPE_GREEN, "将", new JiangVerify(Chess.TYPE_GREEN), R.drawable.bb,'k'),
    XIANG_B(Chess.TYPE_GREEN, "相", new XiangVerify(Chess.TYPE_GREEN), R.drawable.bx, 'b'),
    SHI_B(Chess.TYPE_GREEN, "士", new ShiVerify(Chess.TYPE_GREEN), R.drawable.bs, 'a'),
    MA_B(Chess.TYPE_GREEN, "马", new MaVerify(Chess.TYPE_GREEN), R.drawable.bm, 'n'),
    JU_B(Chess.TYPE_GREEN, "車", new JuVerify(Chess.TYPE_GREEN), R.drawable.bj, 'r'),
    PAO_B(Chess.TYPE_GREEN, "炮", new PaoVerify(Chess.TYPE_GREEN), R.drawable.bp, 'c'),
    BING_B(Chess.TYPE_GREEN, "卒", new BingVerify(Chess.TYPE_GREEN), R.drawable.bz, 'p');

    public final static int TYPE_RED = 0;
    public final static int TYPE_GREEN = 1;

    private String text;
    private int type;
    private ChessVerify chessVerify;
    private int imageId;
    private char fenChar;

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ChessUtils.chessToInt(this));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Chess> CREATOR = new Creator<Chess>() {
        @Override
        public Chess createFromParcel(Parcel in) {
            return ChessUtils.intToChess(in.readInt());
        }

        @Override
        public Chess[] newArray(int size) {
            return new Chess[size];
        }
    };

    public int getImageId() {
        return imageId;
    }

    Chess(int type, String text, ChessVerify chessVerify, int imageId, char fenChar) {
        this.text = text;
        this.type = type;
        this.chessVerify = chessVerify;
        this.imageId = imageId;
        this.fenChar = fenChar;
    }

    public int getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "[" + getText() + ":" + getType() + "]";
    }

    public ChessVerify getChessVerify() {
        return chessVerify;
    }

    public char getFenChar() {
        return fenChar;
    }
}
