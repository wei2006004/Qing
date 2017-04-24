package com.vinson.qing.bean;

import com.vinson.qing.R;
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

public enum Chess {

    JIANG_R(Chess.TYPE_RED, "帅", new JiangVerify(Chess.TYPE_RED), R.drawable.rb),
    XIANG_R(Chess.TYPE_RED, "相", new XiangVerify(Chess.TYPE_RED), R.drawable.rx),
    SHI_R(Chess.TYPE_RED, "士", new ShiVerify(Chess.TYPE_RED), R.drawable.rs),
    MA_R(Chess.TYPE_RED, "马", new MaVerify(Chess.TYPE_RED), R.drawable.rm),
    JU_R(Chess.TYPE_RED, "車", new JuVerify(Chess.TYPE_RED), R.drawable.rj),
    PAO_R(Chess.TYPE_RED, "炮", new PaoVerify(Chess.TYPE_RED), R.drawable.rp),
    BING_R(Chess.TYPE_RED, "兵", new BingVerify(Chess.TYPE_RED), R.drawable.rz),

    JIANG_B(Chess.TYPE_GREEN, "将", new JiangVerify(Chess.TYPE_GREEN), R.drawable.bb),
    XIANG_B(Chess.TYPE_GREEN, "相", new XiangVerify(Chess.TYPE_GREEN), R.drawable.bx),
    SHI_B(Chess.TYPE_GREEN, "士", new ShiVerify(Chess.TYPE_GREEN), R.drawable.bs),
    MA_B(Chess.TYPE_GREEN, "马", new MaVerify(Chess.TYPE_GREEN), R.drawable.bm),
    JU_B(Chess.TYPE_GREEN, "車", new JuVerify(Chess.TYPE_GREEN), R.drawable.bj),
    PAO_B(Chess.TYPE_GREEN, "炮", new PaoVerify(Chess.TYPE_GREEN), R.drawable.bp),
    BING_B(Chess.TYPE_GREEN, "卒", new BingVerify(Chess.TYPE_GREEN), R.drawable.bz);

    public final static int TYPE_RED = 0;
    public final static int TYPE_GREEN = 1;

    private String text;
    private int type;
    private ChessVerify chessVerify;
    private int imageId;

    public int getImageId() {
        return imageId;
    }

    Chess(int type, String text, ChessVerify chessVerify, int imageId) {
        this.text = text;
        this.type = type;
        this.chessVerify = chessVerify;
        this.imageId = imageId;
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
}
