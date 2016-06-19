package com.goo32v2.cooldict.words.interfaces;

import android.support.annotation.NonNull;

import com.goo32v2.cooldict.BasePresenter;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordModel;

/**
 * Created on 14-May-16. (c) CoolDict
 */

public interface WordPresenterContract extends BasePresenter {

    void start(String dict);

    void result(int requestCode, int resultCode);

    void loadWords(boolean showLoadingUi);

    void loadWords(String dictionaryName,boolean showLoadingUi);

    void addNewWord();

    void openWordDetail(@NonNull WordModel word);

    DictionaryModel getCurrentDictionary();
}
