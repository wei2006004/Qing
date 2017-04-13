package com.vinson.qing;

import com.vinson.qing.bean.Chess;
import com.vinson.qing.play.BoardStatus;

/**
 * Created by Vinson on 2017/4/12.
 * e-mail: wei2006004@foxmail.com
 */

public class ChessPlayer {

    public static boolean playVerify(BoardStatus status, Chess chess, int fromx, int fromy, int tox, int toy) {
        if (status.get(fromx, fromy) != chess) {
            throw new IllegalStateException("chess state error! origin chess:" + status.get(fromx, fromy).toString() + " request:" + chess.toString());
        }
        return chess.getChessVerify().playVerify(status, fromx, fromy, tox, toy);
    }
}
