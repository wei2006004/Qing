package com.vinson.qing;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.vinson.qing.play.UcciInterface;

public class ChessService extends Service {
    UcciInterface ucciInterface;

    public ChessService() {
        ucciInterface = new UcciInterface();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return ucciInterface;
    }
}
