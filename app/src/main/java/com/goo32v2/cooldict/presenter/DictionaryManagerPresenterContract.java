package com.goo32v2.cooldict.presenter;

import android.support.annotation.NonNull;

import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.DataSource;

/**
 * Created on 29-Jun-16. (c) CoolDict
 */

public interface DictionaryManagerPresenterContract extends BasePresenter {

    void get(@NonNull DataSource.GetListCallback<DictionaryModel> callback);

    void save(DictionaryModel model);

    void remove(DictionaryModel model);

}
