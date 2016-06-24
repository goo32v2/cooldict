package com.goo32v2.cooldict.words.interfaces;

import android.support.annotation.NonNull;

import com.goo32v2.cooldict.BasePresenter;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.data.sources.interfaces.DataSource;

/**
 * Created on 14-May-16. (c) CoolDict
 */

public interface WordPresenterContract extends BasePresenter {

    void start(String dict);

    void loadWords();

    void loadWords(String dictionaryName);

    void startSettingsActivity();

    void startAddNewWordActivity();

    void startWordDetailsActivity(@NonNull WordModel word);

    void showMessage(String msg);

    void getAllDictionaries(@NonNull DataSource.GetListCallback<DictionaryModel> callback);
}
