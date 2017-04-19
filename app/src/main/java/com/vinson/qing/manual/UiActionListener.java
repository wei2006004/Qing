package com.vinson.qing.manual;

import com.vinson.qing.bean.ChessData;

/**
 * Created by Vinson on 2017/4/19.
 * e-mail: wei2006004@foxmail.com
 */

public interface UiActionListener {
    void onClick(ChessData chessData, int position);

    void onLongPressed(ChessData chessData, int position);
}
