package com.goo32v2.cooldict.dictionarymanager;

import android.support.annotation.NonNull;

import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.sources.DictionaryRepository;
import com.goo32v2.cooldict.data.sources.interfaces.DataSource;
import com.goo32v2.cooldict.dictionarymanager.interfaces.DictionaryManagerPresenterContract;
import com.goo32v2.cooldict.dictionarymanager.interfaces.DictionaryManagerViewContract;

/**
 * Created on 29-Jun-16. (c) CoolDict
 */

public class DictionaryManagerPresenter implements DictionaryManagerPresenterContract{

    private DictionaryManagerViewContract mView;
    private DictionaryRepository mRepository;

    public DictionaryManagerPresenter(@NonNull DictionaryRepository dictionaryRepository,
                                      @NonNull DictionaryManagerViewContract view){
        mRepository = dictionaryRepository;
        mView = view;

        mView.setPresenter(this);
    }

    @Override
    public void get(@NonNull DataSource.GetListCallback<DictionaryModel> callback) {
        mRepository.getDictionaryList(callback);
    }

    @Override
    public void save(DictionaryModel model) {
        mRepository.save(model);
    }

    @Override
    public void remove(DictionaryModel model) {
        mRepository.remove(model);
    }

    @Override
    public void start() {

    }
}
