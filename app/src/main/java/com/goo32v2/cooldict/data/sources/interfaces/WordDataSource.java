package com.goo32v2.cooldict.data.sources.interfaces;

import android.support.annotation.NonNull;

import com.goo32v2.cooldict.data.models.WordModel;

/**
 * Created on 22-May-16. (c) CoolDict
 */

public interface WordDataSource extends DataSource<WordModel> {

    void removeAll();

    void getWord(String id, final @NonNull GetListCallback<WordModel> callback);

    void getWordsList(final @NonNull GetListCallback<WordModel> callback);

    void getWordsByDictionary(String id, final @NonNull GetListCallback<WordModel> callback);
}
