package com.vinson.qing.play;

import android.os.RemoteException;

import com.vinson.qing.IMoveResultListener;
import com.vinson.qing.IUcciInteface;
import com.vinson.qing.bean.MoveResult;
import com.vinson.qing.loader.ChessDataService;
import com.vinson.qing.utils.ServerConnect;
import com.vinson.qingd.Qingd;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Vinson on 2017/5/6.
 * e-mail: wei2006004@foxmail.com
 */

public class UcciInterface extends IUcciInteface.Stub {
    static boolean IS_LOCAL = false;
    ChessDataService service;

    @Override
    public void bestMove(final String fenText, final int side, final IMoveResultListener listener) throws RemoteException {
        if (IS_LOCAL) {
            Qingd.setDepth(2);
            listener.onSuccess(Qingd.bestMove(fenText, side));
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (service == null) {
                    service = ServerConnect.getRetrofit().create(ChessDataService.class);
                }
                Call<MoveResult> resultCall = service.bestMove(fenText, side);
                String ret = "";
                try {
                    Response<MoveResult> response = resultCall.execute();
                    ret = response.body().getResult();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    listener.onSuccess(ret);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
