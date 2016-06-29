package com.goo32v2.cooldict.wordmanager.interfaces;

import android.support.annotation.NonNull;

import com.goo32v2.cooldict.BasePresenter;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.data.sources.interfaces.DataSource;

/**
 * Created on 26-Jun-16. (c) CoolDict
 */

public interface WordManagerPresenterContract extends BasePresenter {

    void getDictionaryNames(@NonNull DataSource.GetListCallback<String> callback);

    void showMessage(String msg);

    void create(String originalWord, String translatedWord, String dictionaryId);

    void update(String id, String originalText, String translatedText, String dictionary);

    void populate(WordModel wordModel);
}
