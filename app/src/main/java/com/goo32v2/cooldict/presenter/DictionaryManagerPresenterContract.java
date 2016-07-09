package com.goo32v2.cooldict.presenter;

import android.view.View;

import com.goo32v2.cooldict.data.dtos.Pair;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.view.DictionaryManagerViewContract;

import java.util.List;

/**
 * Created on 29-Jun-16. (c) CoolDict
 */

public interface DictionaryManagerPresenterContract extends BasePresenter<DictionaryManagerViewContract> {

    List<DictionaryModel> get();

    void save(DictionaryModel model);

    void remove(DictionaryModel model);

    Pair<DictionaryModel, View.OnClickListener> convert(DictionaryModel model, View.OnClickListener listener);

}
