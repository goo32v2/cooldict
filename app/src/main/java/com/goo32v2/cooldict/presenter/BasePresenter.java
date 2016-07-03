package com.goo32v2.cooldict.presenter;

/**
 * Created on 14-May-16. (c) CoolDict
 */
public interface BasePresenter<T> {

    void start();

    void setView(T view);
}
