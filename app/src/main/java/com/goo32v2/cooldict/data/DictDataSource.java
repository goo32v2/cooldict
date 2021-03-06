package com.goo32v2.cooldict.data;

import android.support.annotation.NonNull;

import com.goo32v2.cooldict.data.models.DictionaryModel;

/**
 * Created on 22-May-16. (c) CoolDict
 */

public interface DictDataSource extends DataSource<DictionaryModel> {

    void getDictionaryList(@NonNull GetListCallback<DictionaryModel> callback);

    void getDictionaryById(String id, @NonNull GetListCallback<DictionaryModel> callback);

    void getDictionaryByName(String name, @NonNull GetListCallback<DictionaryModel> callback);

}
