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
        this.chessInfo = chessInfo;
        setText(chessInfo.chess.getText());
        setGravity(Gravity.CENTER);
        if (chessInfo.chess.getType() == Chess.TYPE_RED) {
            setTextColor(context.getResources().getColor(R.color.chess_red));
            setBackgroundResource(R.drawable.bg_chess_red);
        } else {
            setTextColor(context.getResources().getColor(R.color.chess_green));
            setBackgroundResource(R.drawable.bg_chess_green);
        }
    }

    public ChessInfo getChessInfo() {
        return chessInfo;
    }
}
