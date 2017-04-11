package com.vinson.qing.widget;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.vinson.qing.bean.ChessInfo;

/**
 * Created by Vinson on 2017/4/11.
 * e-mail: wei2006004@foxmail.com
 */

public class ChessView extends View {

    private ChessInfo chessInfo;

    public ChessView(Context context, ChessInfo chessInfo) {
        super(context);
        this.chessInfo = chessInfo;
        setBackgroundColor(Color.RED);
    }

    public ChessInfo getChessInfo() {
        return chessInfo;
    }
}
