package com.goo32v2.cooldict.data;

import android.support.annotation.NonNull;

import com.goo32v2.cooldict.data.models.WordModel;

/**
 * Created on 22-May-16. (c) CoolDict
 */

public interface WordDataSource extends DataSource<WordModel> {

    void getWordsList(@NonNull final GetListCallback<WordModel> callback);

    void getWordById(@NonNull String wordId, @NonNull final GetListCallback<WordModel> callback);

    void getWordsByDictionary(@NonNull String dictionaryId, @NonNull  final GetListCallback<WordModel> callback);

}
