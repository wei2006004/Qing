package com.vinson.qing.bean;

/**
 * Created by Vinson on 2017/4/11.
 * e-mail: wei2006004@foxmail.com
 */

public enum Chess {

    JIANG_R(Chess.TYPE_RED, "帅"),
    XIANG_R(Chess.TYPE_RED, "相"),
    SHI_R(Chess.TYPE_RED, "士"),
    MA_R(Chess.TYPE_RED, "马"),
    JU_R(Chess.TYPE_RED, "車"),
    PAO_R(Chess.TYPE_RED, "炮"),
    BING_R(Chess.TYPE_RED, "兵"),

    JIANG_B(Chess.TYPE_BLACK, "将"),
    XIANG_B(Chess.TYPE_BLACK, "相"),
    SHI_B(Chess.TYPE_BLACK, "士"),
    MA_B(Chess.TYPE_BLACK, "马"),
    JU_B(Chess.TYPE_BLACK, "車"),
    PAO_B(Chess.TYPE_BLACK, "炮"),
    BING_B(Chess.TYPE_BLACK, "卒");

    public final static int TYPE_RED = 0;
    public final static int TYPE_BLACK = 1;

    private String text;
    private int type;

    Chess(int type, String text) {
        this.text = text;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public String getText() {
        return text;
    }
}
