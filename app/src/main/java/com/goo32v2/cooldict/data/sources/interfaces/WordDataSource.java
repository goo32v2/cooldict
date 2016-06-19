package com.goo32v2.cooldict.data.sources.interfaces;

import android.support.annotation.NonNull;

import com.goo32v2.cooldict.data.models.WordModel;

/**
 * Created on 22-May-16. (c) CoolDict
 */

public interface WordDataSource extends DataSource<WordModel> {

    void get(@NonNull String dictionaryId, @NonNull final GetListCallback<WordModel> callback);

    void removeAll();
}
