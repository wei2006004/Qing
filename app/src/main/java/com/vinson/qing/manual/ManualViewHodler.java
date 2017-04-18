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

    private TextView player1;
    private TextView player2;
    private TextView time;
    private TextView duration;


    private ManualViewHodler(View itemView) {
        super(itemView);
        player1 = $(R.id.player1);
        player2 = $(R.id.player2);
        time = $(R.id.time);
        duration = $(R.id.duration);
    }

    public void bindData(ChessData data) {
        player1.setText(data.redPlayer);
        player2.setText(data.greenPlayer);
        time.setText(String.valueOf(data.startTime));
        duration.setText(String.valueOf(data.endTime - data.startTime));
    }

    public static ManualViewHodler createViewHodler(Context context) {
        return new ManualViewHodler(View.inflate(context, R.layout.layout_manual_item, null));
    }

    @SuppressWarnings("unchecked")
    private <T> T $(int resd){
        return (T)itemView.findViewById(resd);
    }
}
