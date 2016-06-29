package com.goo32v2.cooldict.words;

import android.support.annotation.NonNull;

import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.data.sources.DictionaryRepository;
import com.goo32v2.cooldict.data.sources.WordRepository;
import com.goo32v2.cooldict.data.sources.interfaces.DataSource;
import com.goo32v2.cooldict.data.sources.interfaces.DictDataSource;
import com.goo32v2.cooldict.data.sources.interfaces.WordDataSource;
import com.goo32v2.cooldict.words.interfaces.WordPresenterContract;
import com.goo32v2.cooldict.words.interfaces.WordViewContract;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 14-May-16. (c) CoolDict
 */

public class WordsPresenter implements WordPresenterContract {

    private final WordDataSource mWordsRepository;
    private final DictDataSource mDictionaryRepository;
    private final WordViewContract mWordsView;


    public WordsPresenter(@NonNull WordRepository wordsRepository,
                          @NonNull DictionaryRepository dictionaryRepository,
                          @NonNull WordViewContract wordsView) {
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
    public void getWordsByDictionary(final String id, @NonNull DataSource.GetListCallback<WordModel> callback) {
        mWordsRepository.getWordsByDictionary(id, callback);
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
