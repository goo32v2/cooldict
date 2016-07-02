package com.goo32v2.cooldict.view;

import com.goo32v2.cooldict.presenter.WordDetailPresenterContract;

/**
 * Created on 15-Jun-16. (c) CoolDict
 */

public interface WordDetailViewContract extends BaseView<WordDetailPresenterContract> {

    void populate();

    void finishActivity();

    void startEditWordActivity();

}
