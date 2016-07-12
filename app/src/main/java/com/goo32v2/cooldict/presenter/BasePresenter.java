package com.goo32v2.cooldict.presenter;

import android.os.Bundle;

/**
 * Created on 14-May-16. (c) CoolDict
 */
public interface BasePresenter<T> {

    void onCreate(T view);

    void onResume(Bundle bundle);

    void onPause();

    void onDestroy();
}
