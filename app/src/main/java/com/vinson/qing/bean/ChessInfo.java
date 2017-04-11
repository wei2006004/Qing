package com.vinson.qing.bean;

/**
 * Created by Vinson on 2017/4/11.
 * e-mail: wei2006004@foxmail.com
 */

public class ChessInfo {
    public int x;
    public int y;
    public Chess chess;

    public ChessInfo(int x, int y, Chess chess) {
        this.x = x;
        this.y = y;
        this.chess = chess;
    }
}
