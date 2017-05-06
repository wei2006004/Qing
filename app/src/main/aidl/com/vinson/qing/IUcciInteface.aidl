// IUcciInteface.aidl
package com.vinson.qing;

// Declare any non-default types here with import statements
import com.vinson.qing.IMoveResultListener;

interface IUcciInteface {

    void bestMove(String fenText, int side, IMoveResultListener listener);
}
