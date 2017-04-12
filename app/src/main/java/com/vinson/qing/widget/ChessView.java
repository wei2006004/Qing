package com.vinson.qing.widget;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

import com.vinson.qing.R;
import com.vinson.qing.bean.Chess;
import com.vinson.qing.bean.ChessInfo;

/**
 * Created by Vinson on 2017/4/11.
 * e-mail: wei2006004@foxmail.com
 */

public class ChessView extends TextView {

    private ChessInfo chessInfo;

    public ChessView(Context context, ChessInfo chessInfo) {
        super(context);

        setGravity(Gravity.CENTER);
        setChessInfo(chessInfo);
    }

    public void setChessInfo(ChessInfo chessInfo) {
        if (this.chessInfo != null && this.chessInfo.chess == chessInfo.chess) {  // 当相同棋子时不更新界面
            this.chessInfo = chessInfo;
            return;
        }

        this.chessInfo = chessInfo;
        setText(chessInfo.chess.getText());
        if (chessInfo.chess.getType() == Chess.TYPE_RED) {
            setTextColor(getContext().getResources().getColor(R.color.chess_red));
            setBackgroundResource(R.drawable.bg_chess_red);
        } else {
            setTextColor(getContext().getResources().getColor(R.color.chess_green));
            setBackgroundResource(R.drawable.bg_chess_green);
        }
    }

    public ChessInfo getChessInfo() {
        return chessInfo;
    }
}
