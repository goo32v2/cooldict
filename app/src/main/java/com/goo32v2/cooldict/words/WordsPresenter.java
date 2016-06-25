package com.goo32v2.cooldict.words;

import android.support.annotation.NonNull;

import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.data.sources.DictionaryRepository;
import com.goo32v2.cooldict.data.sources.WordRepository;
import com.goo32v2.cooldict.data.sources.interfaces.DataSource;
import com.goo32v2.cooldict.words.interfaces.WordPresenterContract;
import com.goo32v2.cooldict.words.interfaces.WordViewContract;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 14-May-16. (c) CoolDict
 */

public class WordsPresenter implements WordPresenterContract {

    private final WordRepository mWordsRepository;
    private final DictionaryRepository mDictionaryRepository;
    private final WordViewContract mWordsView;

    private List<WordModel> wordModelList;
    private List<DictionaryModel> dictionaryModelList;


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

        getWords(new DataSource.GetListCallback<WordModel>() {
            @Override
            public void onLoaded(List<WordModel> entries) {
                wordModelList = entries;
            }

            @Override
            public void onDataNotAvailable() {
                wordModelList = new ArrayList<>();
            }
        });

        getDictionaries(new DataSource.GetListCallback<DictionaryModel>() {
            @Override
            public void onLoaded(List<DictionaryModel> entries) {
                dictionaryModelList = entries;
            }

            @Override
            public void onDataNotAvailable() {
                dictionaryModelList = new ArrayList<>();
            }
        });
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
    public void showMessage(String msg) {
        mWordsView.showMessage(msg);
    }

    public List<WordModel> getRecentWordModelList() {
        return wordModelList;
    }

    public List<DictionaryModel> getRecentDictionaryModelList() {
        return dictionaryModelList;
    }
}
