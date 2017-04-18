package com.vinson.qing.verify;

import com.vinson.qing.bean.Chess;
import com.vinson.qing.bean.BoardStatus;

/**
 * Created by Vinson on 2017/4/13.
 * e-mail: wei2006004@foxmail.com
 */

public class XiangVerify extends ChessVerify {
    public XiangVerify(int type) {
        super(type);
    }

    @Override
    public boolean playVerify(BoardStatus status, int fromx, int fromy, int tox, int toy) {
        if (Math.abs(fromx - tox) != 2) {
            return false;
        }
        if (Math.abs(fromy - toy) != 2) {
            return false;
        }
        if (status.hasChess(tox, toy) && getType() == status.get(tox, toy).getType()) {
            return false;
        }
        if (getType() == Chess.TYPE_GREEN) {
            if (toy > 4) {
                return false;
            }
        } else {
            if (toy < 5) {
                return false;
            }
        }
        if (status.hasChess((fromx + tox) / 2, (fromy + toy) / 2)) {
            return false;
        }
        return true;
    }
}
