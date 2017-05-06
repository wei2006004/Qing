package com.vinson.qing.play;

import android.os.RemoteException;

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
    public String bestMove(String fenText, int side) throws RemoteException {
        if (IS_LOCAL) {
            Qingd.setDepth(2);
            return Qingd.bestMove(fenText, side);
        }
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
        return ret;
    }
}
