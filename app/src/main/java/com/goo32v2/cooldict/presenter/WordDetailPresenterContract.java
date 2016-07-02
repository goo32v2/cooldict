package com.goo32v2.cooldict.presenter;

import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.DataSource;

/**
 * Created on 15-Jun-16. (c) CoolDict
 */

public interface WordDetailPresenterContract extends BasePresenter {

    void startEditWordActivity();

    void actionDeleteWord();

    void getDictionary(String id, DataSource.GetListCallback<DictionaryModel> callback);
}
