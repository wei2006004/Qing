package com.vinson.qing.verify;

import com.vinson.qing.bean.BoardStatus;

/**
 * Created by Vinson on 2017/4/13.
 * e-mail: wei2006004@foxmail.com
 */

public class PaoVerify extends ChessVerify {
    public PaoVerify(int type) {
        super(type);
    }

    @Override
    public boolean playVerify(BoardStatus status, int fromx, int fromy, int tox, int toy) {
        if (fromx != tox && fromy != toy) {
            return false;
        }
        if (status.hasChess(tox, toy)) {
            if (status.get(tox, toy).getType() == getType()) {
                return false;
            } else {
                if (getChessNum(status, fromx, fromy, tox, toy) == 1) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            if (getChessNum(status, fromx, fromy, tox, toy) != 0) {
                return false;
            }
        }
        return true;
    }

    private int getChessNum(BoardStatus status, int fromx, int fromy, int tox, int toy) {
        int count = 0;
        if (fromx == tox) {
            for (int i = Math.min(fromy, toy) + 1; i <= Math.max(fromy, toy) - 1; i++) {
                if (status.hasChess(fromx, i)) count++;
            }
        }

        if (fromy == toy) {
            for (int i = Math.min(fromx, tox) + 1; i <= Math.max(fromx, tox) - 1; i++) {
                if (status.hasChess(i, fromy)) count++;
            }
        }
        return count;
    }
}
