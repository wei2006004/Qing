package com.vinson.qing.verify;

import com.vinson.qing.bean.Chess;
import com.vinson.qing.bean.BoardStatus;

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
        if (status.hasChess(tox, toy) && getType() == status.get(tox, toy).getType()) {
            return false;
        }
        if (getType() == Chess.TYPE_GREEN) {
            if (Math.abs(fromx + fromy - tox - toy) == 1 && fromy <= toy) {
                if (fromy <= 4 && fromx != tox) {
                    return false;
                }
                return true;
            }
        } else {
            if (Math.abs(fromx + fromy - tox - toy) == 1 && fromy >= toy) {
                if (fromy >= 5 && fromx != tox) {
                    return false;
                }
                return true;
            }
        }

        return false;
    }
}
