package com.goo32v2.cooldict.wordmanager.interfaces;

import com.goo32v2.cooldict.BaseView;

/**
 * Created on 26-Jun-16. (c) CoolDict
 */

public interface WordManagerViewContract extends BaseView<WordManagerPresenterContract> {

    void showMessage(String msg);

    void finishActivity();

    void populateWord(String id, String originalWord, String translatedWrd, String dictionary);

}
