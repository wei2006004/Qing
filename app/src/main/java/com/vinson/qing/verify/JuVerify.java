package com.vinson.qing.verify;

import com.vinson.qing.play.BoardStatus;

/**
 * Created by Vinson on 2017/4/13.
 * e-mail: wei2006004@foxmail.com
 */

public class JuVerify extends ChessVerify {
    public JuVerify(int type) {
        super(type);
    }

    @Override
    public boolean playVerify(BoardStatus status, int fromx, int fromy, int tox, int toy) {
        if (fromx != tox && fromy != toy) {
            return false;
        }
        if (status.hasChess(tox, toy) && getType() == status.get(tox, toy).getType()) {
            return false;
        }

        if (fromx == tox) {
            for (int i = Math.min(fromy, toy) + 1; i <= Math.max(fromy, toy) - 1; i++) {
                if (status.hasChess(fromx, i)) return false;
            }
        }

        if (fromy == toy) {
            for (int i = Math.min(fromx, tox) + 1; i <= Math.max(fromx, tox) - 1; i++) {
                if (status.hasChess(i, fromy)) return false;
            }
        }
        return true;
    }
}
