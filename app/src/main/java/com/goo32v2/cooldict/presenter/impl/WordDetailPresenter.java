package com.goo32v2.cooldict.presenter.impl;

import android.support.annotation.NonNull;

import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.data.repositories.DictionaryRepository;
import com.goo32v2.cooldict.data.repositories.WordRepository;
import com.goo32v2.cooldict.data.DataSource;
import com.goo32v2.cooldict.presenter.WordDetailPresenterContract;
import com.goo32v2.cooldict.view.WordDetailViewContract;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 15-Jun-16. (c) CoolDict
 */

public class WordDetailPresenter implements WordDetailPresenterContract{

    private final WordRepository mWordRepository;

    private final WordDetailViewContract mView;
    private final DictionaryRepository mDictionaryRepository;

    private WordModel mWord;

    public WordDetailPresenter(@NonNull WordRepository wordRepository,
                               @NonNull DictionaryRepository dictionaryRepository,
                               @NonNull WordDetailViewContract viewContract,
                               @NonNull WordModel word){
        this.mWordRepository = checkNotNull(wordRepository);
        this.mDictionaryRepository = checkNotNull(dictionaryRepository);
        this.mView = checkNotNull(viewContract);
        this.mWord = word;

        mView.setPresenter(this);
    }

    @Override
    public void startEditWordActivity() {
        mView.startEditWordActivity();
    }

    @Override
    public void actionDeleteWord() {
        mWordRepository.remove(mWord);
        mView.finishActivity();
    }

    @Override
    public void getDictionary(String id, DataSource.GetListCallback<DictionaryModel> callback) {
        mDictionaryRepository.getDictionary(id, callback);
    }

    @Override
    public void start() {
        mView.populate();
    }
}
