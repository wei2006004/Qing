package com.vinson.qing.manual;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.vinson.qing.R;
import com.vinson.qing.bean.ChessData;
import com.vinson.qing.bean.ChessTrack;
import com.vinson.qing.widget.CheckerBoard;

public class ManualPlayActivity extends AppCompatActivity {

    private ChessData chessData;
    private int currentTrack;
    CheckerBoard checkerBoard;

    private static final String EXTRA_CHESS_DATA = "EXTRA_CHESS_DATA";

    public static void startActivity(Context context, ChessData chessData) {
        Intent intent = new Intent(context, ManualPlayActivity.class);
        intent.putExtra(EXTRA_CHESS_DATA, chessData);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_play);

        chessData = getIntent().getParcelableExtra(EXTRA_CHESS_DATA);

        checkerBoard = (CheckerBoard) findViewById(R.id.checkerBoard);
        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doNext();
            }
        });
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBack();
            }
        });
        findViewById(R.id.btn_back).setEnabled(false);
    }

    private void doBack() {

    }

    private void doNext() {
        findViewById(R.id.btn_back).setEnabled(true);
        ChessTrack track = chessData.getTracks().get(currentTrack);
        checkerBoard.playChess(track.getChess(), track.fromx, track.fromy, track.tox, track.toy);
        currentTrack ++;
        if (currentTrack == chessData.getTracks().size()) {
            findViewById(R.id.btn_next).setEnabled(false);
        }
    }
}
