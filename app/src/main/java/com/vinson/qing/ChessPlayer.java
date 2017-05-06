package com.vinson.qing;

import com.vinson.qing.bean.Chess;
import com.vinson.qing.bean.ChessInfo;
import com.vinson.qing.bean.BoardStatus;

import java.util.List;

/**
 * Created by Vinson on 2017/4/12.
 * e-mail: wei2006004@foxmail.com
 */

public class ChessPlayer {

    public BoardStatus getBoardStatus() {
        return boardStatus;
    }

    private BoardStatus boardStatus;

    public ChessPlayer() {
        boardStatus = new BoardStatus();
    }

    public boolean verify(Chess chess, int fromx, int fromy, int tox, int toy) {
        if (boardStatus.get(fromx, fromy) != chess) {
            throw new IllegalStateException("chess state error! origin chess:" + boardStatus.get(fromx, fromy).toString() + " request:" + chess.toString());
        }
        return chess.getChessVerify().playVerify(boardStatus, fromx, fromy, tox, toy);
    }

    public void setChessList(List<ChessInfo> chessList) {
        boardStatus.setChessList(chessList);
    }

    public void play(Chess chess, int fromx, int fromy, int tox, int toy) {
        if (boardStatus.get(fromx, fromy) != chess) {
            throw new IllegalStateException("chess state error! origin chess:" + boardStatus.get(fromx, fromy).toString() + " request:" + chess.toString());
        }
        boardStatus.setChess(chess, tox, toy);
        boardStatus.removeChess(fromx, fromy);
    }

    public boolean hasChess(int x, int y) {
        return boardStatus.hasChess(x, y);
    }
}
