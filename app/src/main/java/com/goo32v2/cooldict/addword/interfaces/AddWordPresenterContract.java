package com.goo32v2.cooldict.addword.interfaces;

import android.support.annotation.NonNull;

import com.goo32v2.cooldict.BasePresenter;
import com.goo32v2.cooldict.data.sources.interfaces.DataSource;

/**
 * Created on 26-Jun-16. (c) CoolDict
 */

public interface AddWordPresenterContract extends BasePresenter {

    void getDictionaryNames(@NonNull DataSource.GetListCallback<String> callback);

    void showMessage(String msg);

    void createWord(String originalWord, String translatedWord, String dictionaryId);
}
