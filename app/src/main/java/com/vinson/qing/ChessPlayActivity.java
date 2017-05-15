package com.vinson.qing;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vinson.qing.bean.Chess;
import com.vinson.qing.bean.ChessData;
import com.vinson.qing.bean.ChessTrack;
import com.vinson.qing.utils.ChessUtils;
import com.vinson.qing.utils.DbService;
import com.vinson.qing.utils.L;
import com.vinson.qing.utils.ObserverAdapter;
import com.vinson.qing.widget.AppBar;
import com.vinson.qing.widget.CheckerBoard;
import com.vinson.qingd.Contants;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class ChessPlayActivity extends BaseActivity {

    @BindView(R.id.image_player)
    ImageView playerImage;

    @BindView(R.id.checkerBoard)
    CheckerBoard checkerBoard;

    @BindView(R.id.appbar)
    AppBar appBar;

    ChessData chessData;
    IUcciInteface ucciInteface;

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ucciInteface = IUcciInteface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @OnClick(R.id.btn_save)
    void onSave() {
        chessData.endTime = new Date();
        DbService.saveChessData(chessData, new ObserverAdapter<Object>() {
            @Override
            public void onCompleted() {
                Toast.makeText(ChessPlayActivity.this, "saved", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(ChessPlayActivity.this, "error", Toast.LENGTH_LONG).show();
            }
        });
    }

    @OnClick(R.id.btn_reset)
    void onReset() {
        checkerBoard.setChessList(ChessUtils.getInitChessList());
        initData();
    }

    @OnClick(R.id.btn_change_player)
    void onChangePlayer() {
        try {
            String fen = checkerBoard.getBoardStatus().toFenText();
            L.d("fen:" , fen);
            ucciInteface.bestMove(fen, Contants.SIDE_BLACK, new IMoveResultListener.Stub() {

                @Override
                public void onSuccess(final String result) throws RemoteException {
                    post(new Runnable() {
                        @Override
                        public void run() {
                            ChessTrack track = ChessUtils.moveResultToTask(result);
                            checkerBoard.playChess(track.fromx, track.fromy, track.tox, track.toy);
                            L.d("bind", result);
                        }
                    });
                }

                @Override
                public void onError(int error) throws RemoteException {
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    void post(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
        initView();

        bindChessService();
    }

    private void bindChessService() {
        Intent intent = new Intent(this, ChessService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE | Context.BIND_IMPORTANT);
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

        appBar.setOnBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        appBar.setTitle("对弈");
    }

    private void initData() {
        chessData = new ChessData();
        chessData.startTime = new Date();
        chessData.redPlayer = "棋手1";
        chessData.greenPlayer = "棋手2";
    }

    private void setPlayer(int currentPlayer) {
        if (currentPlayer == CheckerBoard.PLAYER_RED) {
            playerImage.setImageResource(R.drawable.r);
        } else {
            playerImage.setImageResource(R.drawable.b);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
        ucciInteface = null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_play_chess;
    }
}
