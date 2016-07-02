package com.goo32v2.cooldict.presenter.impl;

import android.support.annotation.NonNull;

import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.data.repositories.DictionaryRepository;
import com.goo32v2.cooldict.data.repositories.WordRepository;
import com.goo32v2.cooldict.data.DataSource;
import com.goo32v2.cooldict.data.DictDataSource;
import com.goo32v2.cooldict.data.WordDataSource;
import com.goo32v2.cooldict.presenter.WordListPresenterContract;
import com.goo32v2.cooldict.view.WordListViewContract;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 14-May-16. (c) CoolDict
 */

public class WordListPresenter implements WordListPresenterContract {

    private final WordDataSource mWordsRepository;
    private final DictDataSource mDictionaryRepository;
    private final WordListViewContract mWordsView;


    public WordListPresenter(@NonNull WordRepository wordsRepository,
                             @NonNull DictionaryRepository dictionaryRepository,
                             @NonNull WordListViewContract wordsView) {
        mWordsRepository = checkNotNull(wordsRepository, "wordsRepository cannot be null!");
        mDictionaryRepository = checkNotNull(dictionaryRepository, "dictionaryRepository cannot be null!");
        mWordsView = checkNotNull(wordsView, "wordsView cannot be null!");

        mWordsView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getWords(@NonNull DataSource.GetListCallback<WordModel> callback) {
        mWordsRepository.getWordsList(callback);
    }

    @Override
    public void getDictionaries(@NonNull DataSource.GetListCallback<DictionaryModel> callback) {
        mDictionaryRepository.getDictionaryList(callback);
    }

    @Override
    public void getWordsByDictionaryName(final String name, @NonNull final DataSource.GetListCallback<WordModel> callback) {
        mDictionaryRepository.getDictionaryByName(name, new DataSource.GetListCallback<DictionaryModel>() {
            @Override
            public void onLoaded(List<DictionaryModel> entries) {
                mWordsRepository.getWordsByDictionary(entries.get(0).getId(), callback);
            }

            @Override
            public void onDataNotAvailable() {
                showMessage("Problem!");
            }
        });
    }

    @Override
    public void startSettingsActivity() {
        mWordsView.startSettingsActivity();
    }

    @Override
    public void startAddNewWordActivity() {
        mWordsView.startAddWordActivity();
    }

    @Override
    public void startWordDetailsActivity(@NonNull WordModel word) {
        checkNotNull(word);
        mWordsView.startWordDetailActivity(word);
    }

    @Override
    public void startDictionaryManagerActivity() {
        mWordsView.startDictionaryManagerActivity();
    }

    @Override
    public void showMessage(String msg) {
        mWordsView.showMessage(msg);
    }
}
