package com.vinson.qing.manual;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vinson.qing.R;
import com.vinson.qing.bean.ChessData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vinson on 2017/4/18.
 * e-mail: wei2006004@foxmail.com
 */

public class ManualViewHodler extends RecyclerView.ViewHolder {

    public void setActionListener(UiActionListener actionListener) {
        this.actionListener = actionListener;
    }

    private UiActionListener actionListener;

    @BindView(R.id.player1)
    TextView player1;
    @BindView(R.id.player2)
    TextView player2;
    @BindView(R.id.time)
    TextView time;

    private ChessData chessData;
    private int position;

    private ManualViewHodler(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData(ChessData data, int pos) {
        position = pos;
        chessData = data;
        player1.setText(data.redPlayer);
        player2.setText(data.greenPlayer);
        time.setText(String.valueOf(data.startTime));
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

    public static ManualViewHodler createViewHodler(Context context, ViewGroup parent) {
        return new ManualViewHodler(LayoutInflater.from(context).inflate(R.layout.layout_manual_item, parent, false));
    }

    public ChessData getChessData() {
        return chessData;
    }
}
