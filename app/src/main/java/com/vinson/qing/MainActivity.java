package com.vinson.qing;

import android.content.Intent;

import com.vinson.qing.manual.ChessManualActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @OnClick(R.id.btn_chess)
    void onChessClick() {
        startActivity(new Intent(MainActivity.this, ChessPlayActivity.class));
    }

    @OnClick(R.id.btn_record)
    void onRecordClick() {
        startActivity(new Intent(MainActivity.this, ChessManualActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
