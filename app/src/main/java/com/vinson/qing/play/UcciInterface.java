package com.vinson.qing.play;

import android.os.RemoteException;

import com.vinson.qing.IUcciInteface;

/**
 * Created by Vinson on 2017/5/6.
 * e-mail: wei2006004@foxmail.com
 */

public class UcciInterface extends IUcciInteface.Stub{
    @Override
    public String bestMove(String fenText, int side) throws RemoteException {
        return "hello";
    }
}
