package com.goo32v2.cooldict.data.sources.interfaces;

import android.support.annotation.NonNull;

import com.goo32v2.cooldict.data.models.DictionaryModel;

/**
 * Created on 22-May-16. (c) CoolDict
 */

public interface DictDataSource extends DataSource<DictionaryModel> {

    void getDefaultDictionary(@NonNull final GetEntryCallback<DictionaryModel> callback);

    void deleteAll();

}
