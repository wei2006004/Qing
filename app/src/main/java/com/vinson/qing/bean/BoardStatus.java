package com.vinson.qing.bean;

import java.util.List;

/**
 * Created by Vinson on 2017/4/13.
 * e-mail: wei2006004@foxmail.com
 */

public class BoardStatus {

    public final static int BROAD_X_NUM = 9;
    public final static int BROAD_Y_NUM = 10;

    private Chess[][] chesses = new Chess[BROAD_Y_NUM][BROAD_X_NUM];

    public void setChessList(List<ChessInfo> list) {
        for (ChessInfo info : list) {
            chesses[info.y][info.x] = info.chess;
        }
    }

    public void setChess(Chess chess, int x, int y) {
        chesses[y][x] = chess;
    }

    public void removeChess(int x, int y) {
        chesses[y][x] = null;
    }

    public boolean hasChess(int x, int y) {
        return chesses[y][x] != null;
    }

    public Chess get(int x, int y) {
        return chesses[y][x];
    }
}
