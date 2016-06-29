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

    void getWords(@NonNull DataSource.GetListCallback<WordModel> callback);

    void getDictionaries(@NonNull DataSource.GetListCallback<DictionaryModel> callback);

    void getWordsByDictionary(String id, @NonNull DataSource.GetListCallback<WordModel> callback);

    void startSettingsActivity();

    void startAddNewWordActivity();

    void startDictionaryManagerActivity();

    void startWordDetailsActivity(@NonNull WordModel word);

    void showMessage(String msg);
}
