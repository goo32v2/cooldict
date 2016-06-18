package com.goo32v2.cooldict.data.sources.interfaces;

import android.support.annotation.NonNull;

import com.goo32v2.cooldict.data.models.DictionaryModel;

/**
 * Created on 22-May-16. (c) CoolDict
 */

public interface DictDataSource extends DataSource<DictionaryModel> {

    // FIXME: 18-Jun-16 doing something with this
    void getByTitle(@NonNull final String dictionaryTitle, @NonNull GetEntryCallback<DictionaryModel> callback);

    void getDefaultDictionary(@NonNull final GetEntryCallback<DictionaryModel> callback);

    void deleteAll();

}
