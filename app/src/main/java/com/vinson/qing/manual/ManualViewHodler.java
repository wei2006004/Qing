package com.vinson.qing.manual;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vinson.qing.R;
import com.vinson.qing.bean.ChessData;

/**
 * Created by Vinson on 2017/4/18.
 * e-mail: wei2006004@foxmail.com
 */

public class ManualViewHodler extends RecyclerView.ViewHolder {

    public void setActionListener(UiActionListener actionListener) {
        this.actionListener = actionListener;
    }

    private UiActionListener actionListener;

    private TextView player1;
    private TextView player2;
    private TextView time;
    private TextView duration;

    private ChessData chessData;
    private int position;

    private ManualViewHodler(View itemView) {
        super(itemView);
        player1 = $(R.id.player1);
        player2 = $(R.id.player2);
        time = $(R.id.time);
        duration = $(R.id.duration);
    }

    public void bindData(ChessData data, int pos) {
        position = pos;
        chessData = data;
        player1.setText(data.redPlayer);
        player2.setText(data.greenPlayer);
        time.setText(String.valueOf(data.startTime));
        duration.setText(String.valueOf(data.endTime - data.startTime));
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actionListener != null) {
                    actionListener.onClick(chessData, position);
                }
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (actionListener != null) {
                    actionListener.onLongPressed(chessData, position);
                }
                return true;
            }
        });
    }

    public static ManualViewHodler createViewHodler(Context context) {
        return new ManualViewHodler(View.inflate(context, R.layout.layout_manual_item, null));
    }

    @SuppressWarnings("unchecked")
    private <T> T $(int resd){
        return (T)itemView.findViewById(resd);
    }

    public ChessData getChessData() {
        return chessData;
    }
}
