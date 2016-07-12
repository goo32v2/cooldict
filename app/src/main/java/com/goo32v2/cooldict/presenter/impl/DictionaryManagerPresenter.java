package com.goo32v2.cooldict.presenter.impl;

import android.os.Bundle;
import android.support.v4.util.Pair;
import android.view.View;

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
    public void onCreate(DictionaryManagerViewContract view) {
        this.mView = view;
    }

    @Override
    public void onResume(Bundle bundle) {
        List<Pair<DictionaryModel, View.OnClickListener>> res = new ArrayList<>();

        List<DictionaryModel> dictionaries = get();
        for (final DictionaryModel dictionary : dictionaries) {
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mView.getConfirmationDialog(dictionary).show();
                }
            };
            res.add(Pair.create(dictionary, listener));
        }

        mView.showDictionaries(res);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        this.mView = null;
    }
}
