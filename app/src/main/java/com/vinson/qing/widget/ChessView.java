package com.vinson.qing.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.vinson.qing.R;
import com.vinson.qing.bean.Chess;
import com.vinson.qing.bean.ChessInfo;

/**
 * Created by Vinson on 2017/4/11.
 * e-mail: wei2006004@foxmail.com
 */

public class ChessView extends ImageView {

    private ChessInfo chessInfo;

    public ChessView(Context context) {
        this(context, null);
    }

    public ChessView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChessView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public void setChessInfo(ChessInfo chessInfo) {
        if (this.chessInfo != null && this.chessInfo.chess == chessInfo.chess) {  // 当相同棋子时不更新界面
            this.chessInfo = chessInfo;
            return;
        }

        this.chessInfo = chessInfo;
        setImageResource(chessInfo.chess.getImageId());
    }

    public ChessInfo getChessInfo() {
        return chessInfo;
    }
}
