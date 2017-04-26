package com.vinson.qing.utils;

import android.util.Log;

/**
 * Created by Vinson on 2017/4/12.
 * e-mail: wei2006004@foxmail.com
 */

public class L {
    public static void d(String text) {
        Log.d("chess", text);
    }

    public static void d(String tag, Object value) {
        Log.d("chess", tag + String.valueOf(value));
    }

    public static void d(String tag, Object value, String tag2, Object value2) {
        Log.d("chess", tag + ":" + String.valueOf(value) + " " + tag2 + ":" +String.valueOf(value2));
    }

    public static void d(String tag, Object value, String tag2, Object value2, String tag3, Object value3) {
        Log.d("chess", tag + ":" + String.valueOf(value) + " " + tag2 + ":" +String.valueOf(value2) + " " + tag3 + ":" +String.valueOf(value3));
    }
}
