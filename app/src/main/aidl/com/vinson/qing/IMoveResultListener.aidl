// IMoveResultListener.aidl
package com.vinson.qing;

// Declare any non-default types here with import statements

interface IMoveResultListener {

    void onSuccess(String result);
    void onError(int error);
}
