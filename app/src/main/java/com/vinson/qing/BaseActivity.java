package com.vinson.qing;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;

/**
 * Created by Vinson on 2017/4/28.
 * e-mail: wei2006004@foxmail.com
 */

public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
    }

    protected abstract int getLayoutId();
}
