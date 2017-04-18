package com.vinson.qing.verify;

import com.vinson.qing.bean.BoardStatus;

/**
 * Created by Vinson on 2017/4/13.
 * e-mail: wei2006004@foxmail.com
 */

public class MaVerify extends ChessVerify {
    public MaVerify(int type) {
        super(type);
    }

    @Override
    public boolean playVerify(BoardStatus status, int fromx, int fromy, int tox, int toy) {
        if (status.hasChess(tox, toy) && getType() == status.get(tox, toy).getType()) {
            return false;
        }
        if (Math.abs(fromx - tox) == 1 && Math.abs(fromy - toy) == 2) {
            if (!status.hasChess(fromx, (fromy + toy) / 2)) {
                return true;
            }
        }
        if (Math.abs(fromx - tox) == 2 && Math.abs(fromy - toy) == 1) {
            if (!status.hasChess((fromx + tox) / 2, fromy)) {
                return true;
            }
        }
        return false;
    }
}
