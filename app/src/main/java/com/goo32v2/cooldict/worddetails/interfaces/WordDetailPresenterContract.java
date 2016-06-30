package com.goo32v2.cooldict.worddetails.interfaces;

import com.goo32v2.cooldict.BasePresenter;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.sources.interfaces.DataSource;

/**
 * Created on 15-Jun-16. (c) CoolDict
 */

public interface WordDetailPresenterContract extends BasePresenter {

    void startEditWordActivity();

    void actionDeleteWord();

    void getDictionary(String id, DataSource.GetListCallback<DictionaryModel> callback);
}
