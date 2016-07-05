package com.goo32v2.cooldict.presenter.impl;

import android.view.View;

import com.goo32v2.cooldict.data.converters.DTOConverters;
import com.goo32v2.cooldict.data.dtos.ModelDTO;
import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.model.impl.DictionaryManagerModel;
import com.goo32v2.cooldict.presenter.DictionaryManagerPresenterContract;
import com.goo32v2.cooldict.view.DictionaryManagerViewContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 29-Jun-16. (c) CoolDict
 */

public class DictionaryManagerPresenter implements DictionaryManagerPresenterContract {

    private DictionaryManagerViewContract mView;
    private DictionaryManagerModel mModel;

    public DictionaryManagerPresenter(DictionaryManagerModel model){
        this.mModel = model;
    }

    @Override
    public List<DictionaryModel> get() {
        return mModel.get();
    }

    @Override
    public void save(DictionaryModel model) {
        mModel.save(model);
    }

    @Override
    public void remove(final DictionaryModel model) {
        mModel.remove(model);
    }

    @Override
    public ModelDTO<DictionaryModel, View.OnClickListener> convert(DictionaryModel model, View.OnClickListener listener) {
        return DTOConverters.convertDictionaryToDTO(model, listener);
    }

    @Override
    public void start() {
        List<ModelDTO<DictionaryModel, View.OnClickListener>> res = new ArrayList<>();

        List<DictionaryModel> dictionaries = get();
        for (final DictionaryModel dictionary : dictionaries) {
            res.add(convert(dictionary, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mView.getConfirmationDialog(dictionary).show();
                }
            }));
        }

        mView.showDictionaries(res);
    }

    @Override
    public void setView(DictionaryManagerViewContract view) {
        mView = view;
    }
}
