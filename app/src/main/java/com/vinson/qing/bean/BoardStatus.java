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

    public String toFenText() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < BROAD_Y_NUM; i++) {
            for (int j = 0; j < BROAD_X_NUM; j++) {
                char by = 0;
                int count = 0;
                do {
                    if (get(j, i) == null) {
                        by = 0;
                    } else {
                        by = get(j, i).getFenChar();
                    }
                    if (by == 0) {
                        count++;
                        j++;
                    }
                } while (by == 0 && j < BROAD_X_NUM);
                if (count == 0) {
                    builder.append(by);
                } else {
                    builder.append((char) ('0' + count));
                    if (by != 0) {
                        builder.append(by);
                    }
                }
            }
            builder.append('/');
        }
        return builder.substring(0, builder.length() - 1);
    }
}
