package com.vinson.qing.verify;

import com.vinson.qing.bean.Chess;
import com.vinson.qing.play.BoardStatus;

/**
 * Created by Vinson on 2017/4/13.
 * e-mail: wei2006004@foxmail.com
 */

public class JiangVerify extends ChessVerify {
    public JiangVerify(int type) {
        super(type);
    }

    @Override
    public boolean playVerify(BoardStatus status, int fromx, int fromy, int tox, int toy) {
        if (tox < 3 || tox > 5) {
            return false;
        }

        if (status.hasChess(tox, toy)) {
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

        return Math.abs(fromx + fromy - tox - toy) == 1 && (fromx == tox || fromy == toy);
    }
}
