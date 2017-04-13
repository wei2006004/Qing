package com.vinson.qing.verify;

import com.vinson.qing.play.BoardStatus;

/**
 * Created by Vinson on 2017/4/13.
 * e-mail: wei2006004@foxmail.com
 */

public class BingVerify extends ChessVerify {
    public BingVerify(int type) {
        super(type);
    }

    @Override
    public boolean playVerify(BoardStatus status, int fromx, int fromy, int tox, int toy) {
        return false;
    }
}
