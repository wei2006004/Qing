package com.vinson.qing.manual;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.vinson.qing.BaseActivity;
import com.vinson.qing.R;
import com.vinson.qing.bean.ChessData;
import com.vinson.qing.bean.ChessTrack;
import com.vinson.qing.widget.CheckerBoard;

import butterknife.BindView;
import butterknife.OnClick;

public class ManualPlayActivity extends BaseActivity {

    private ChessData chessData;
    private int currentTrack;

    @BindView(R.id.checkerBoard)
    CheckerBoard checkerBoard;

    @BindView(R.id.btn_back)
    Button backBtn;

    @BindView(R.id.btn_next)
    Button nextBtn;

    private static final String EXTRA_CHESS_DATA = "EXTRA_CHESS_DATA";

    public static void startActivity(Context context, ChessData chessData) {
        Intent intent = new Intent(context, ManualPlayActivity.class);
        intent.putExtra(EXTRA_CHESS_DATA, chessData);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        chessData = getIntent().getParcelableExtra(EXTRA_CHESS_DATA);
        backBtn.setEnabled(false);
    }

    @OnClick(R.id.btn_back)
    void doBack() {

    }

    @OnClick(R.id.btn_next)
    void doNext() {
//        backBtn.setEnabled(true);
//        ChessTrack track = chessData.getTracks().get(currentTrack);
//        checkerBoard.playChess(track.getChess(), track.fromx, track.fromy, track.tox, track.toy);
//        currentTrack ++;
//        if (currentTrack == chessData.getTracks().size()) {
//            nextBtn.setEnabled(false);
//        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_manual_play;
    }
}
