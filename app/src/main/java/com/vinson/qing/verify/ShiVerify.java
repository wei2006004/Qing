package com.vinson.qing.verify;

import com.vinson.qing.bean.Chess;
import com.vinson.qing.bean.BoardStatus;

/**
 * Created by Vinson on 2017/4/13.
 * e-mail: wei2006004@foxmail.com
 */

public class ShiVerify extends ChessVerify {
    public ShiVerify(int type) {
        super(type);
    }

    @Override
    public boolean playVerify(BoardStatus status, int fromx, int fromy, int tox, int toy) {
        if (tox < 3 || tox > 5) {
            return false;
        }

        if (status.hasChess(tox, toy) && getType() == status.get(tox, toy).getType()) {
            return false;
        }
        if (getType() == Chess.TYPE_GREEN) {
            if (toy > 2) {
                return false;
            }
        } else {
            if (toy < 7) {
                return false;
            }
        }

        return Math.abs(fromx - tox) == 1 && Math.abs(fromy - toy) == 1;
    }
}
