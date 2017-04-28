package com.vinson.qing;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vinson.qing.bean.Chess;
import com.vinson.qing.bean.ChessData;
import com.vinson.qing.bean.ChessInfo;
import com.vinson.qing.utils.ChessUtils;
import com.vinson.qing.utils.DbService;
import com.vinson.qing.widget.CheckerBoard;
import com.vinson.qing.widget.ChessView;

import butterknife.BindView;
import butterknife.OnClick;

public class ChessPlayActivity extends BaseActivity {

    @BindView(R.id.image_player)
    ImageView playerImage;

    @BindView(R.id.checkerBoard)
    CheckerBoard checkerBoard;

    @BindView(R.id.tv_player)
    TextView playerText;

    ChessData chessData;

    @OnClick(R.id.btn_save)
    void onSave() {
        chessData.endTime = System.currentTimeMillis();
        DbService.saveChessData(chessData);
        Toast.makeText(ChessPlayActivity.this, "saved", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.btn_reset)
    void onReset() {
        checkerBoard.setChessList(ChessUtils.getInitChessList());
        initData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
        initView();
    }

    private void initView() {
        checkerBoard.setChessPlayListener(new CheckerBoard.ChessPlayListener() {
            @Override
            public void onPlayerChange(int currentPlayer) {
                setPlayer(currentPlayer);
            }

            @Override
            public void onChessPlay(Chess chess, int fromx, int fromy, int tox, int toy) {
                chessData.addTrack(chess, fromx, fromy, tox, toy);
            }
        });
        setPlayer(checkerBoard.getCurrentPlayer());
    }

    private void initData() {
        chessData = new ChessData();
        chessData.startTime = System.currentTimeMillis();
        chessData.redPlayer = "棋手1";
        chessData.greenPlayer = "棋手2";
    }

    private void setPlayer(int currentPlayer) {
        if (currentPlayer == CheckerBoard.PLAYER_RED) {
            playerText.setText("红方");
            playerText.setTextColor(Color.RED);
            playerImage.setImageResource(R.drawable.r);
        } else {
            playerText.setText("绿方");
            playerText.setTextColor(Color.GREEN);
            playerImage.setImageResource(R.drawable.b);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_play_chess;
    }
}
