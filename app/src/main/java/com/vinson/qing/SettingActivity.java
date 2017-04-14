package com.vinson.qing;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vinson.qing.utils.ChessUtils;
import com.vinson.qing.widget.CheckerBoard;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        final CheckerBoard board = (CheckerBoard) findViewById(R.id.checkerBoard);
        board.setChessPlayListener(new CheckerBoard.ChessPlayListener() {
            @Override
            public void onPlayerChange(int currentPlayer) {
                setPlayer(currentPlayer);
            }
        });
        setPlayer(board.getCurrentPlayer());
        findViewById(R.id.btn_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                board.setChessList(ChessUtils.getInitChessList());
            }
        });
    }

    private void setPlayer(int currentPlayer) {
        TextView textView = (TextView) findViewById(R.id.tv_player);
        if (currentPlayer == CheckerBoard.PLAYER_RED) {
            textView.setText("红方");
            textView.setTextColor(Color.RED);
        } else {
            textView.setText("绿方");
            textView.setTextColor(Color.GREEN);
        }
    }
}
