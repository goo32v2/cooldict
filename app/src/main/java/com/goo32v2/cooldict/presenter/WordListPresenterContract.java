package com.goo32v2.cooldict.presenter;

import android.support.annotation.NonNull;

import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.data.DataSource;

/**
 * Created on 14-May-16. (c) CoolDict
 */

public interface WordListPresenterContract extends BasePresenter {

    void getWords(@NonNull DataSource.GetListCallback<WordModel> callback);

    void getDictionaries(@NonNull DataSource.GetListCallback<DictionaryModel> callback);

    void getWordsByDictionaryName(String name, @NonNull DataSource.GetListCallback<WordModel> callback);

    void startSettingsActivity();

    void startAddNewWordActivity();

    void startDictionaryManagerActivity();

    void startWordDetailsActivity(@NonNull WordModel word);

    void showMessage(String msg);
}
