package com.vinson.qing.verify;

import com.vinson.qing.bean.BoardStatus;

/**
 * Created by Vinson on 2017/4/13.
 * e-mail: wei2006004@foxmail.com
 */

public abstract class ChessVerify {

    private int type;

    public ChessVerify(int type) {
        this.type = type;
    }

    public abstract boolean playVerify(BoardStatus status, int fromx, int fromy, int tox, int toy);

    public int getType() {
        return type;
    }
}
