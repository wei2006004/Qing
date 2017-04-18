package com.vinson.qing;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.vinson.qing.bean.Chess;
import com.vinson.qing.bean.ChessData;
import com.vinson.qing.bean.ChessInfo;
import com.vinson.qing.bean.ChessTrack;
import com.vinson.qing.manual.ChessLocalLoader;
import com.vinson.qing.utils.ChessUtils;
import com.vinson.qing.widget.CheckerBoard;
import com.vinson.qing.widget.ChessView;

public class ChessPlayActivity extends AppCompatActivity {

    private ChessView chessView;
    private ChessData chessData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_chess);

        initData();
        initView();
    }

    private void initView() {
        chessView = (ChessView)findViewById(R.id.chessView);
        chessView.setChessInfo(new ChessInfo(0, 0, Chess.JIANG_R));

        final CheckerBoard board = (CheckerBoard) findViewById(R.id.checkerBoard);
        board.setChessPlayListener(new CheckerBoard.ChessPlayListener() {
            @Override
            public void onPlayerChange(int currentPlayer) {
                setPlayer(currentPlayer);
            }

            @Override
            public void onChessPlay(Chess chess, int fromx, int fromy, int tox, int toy) {
                chessData.addTrack(chess, fromx, fromy, tox, toy);
            }
        });
        setPlayer(board.getCurrentPlayer());
        findViewById(R.id.btn_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                board.setChessList(ChessUtils.getInitChessList());
                initData();
            }
        });
        findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChessLocalLoader.getInstance().addChessData(chessData);
                Toast.makeText(ChessPlayActivity.this, "saved", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initData() {
        chessData = new ChessData();
        chessData.startTime = System.currentTimeMillis();
        chessData.redPlayer = "棋手1";
        chessData.greenPlayer = "棋手2";
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
