package com.goo32v2.cooldict.words;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.data.sources.DictionaryRepository;
import com.goo32v2.cooldict.data.sources.WordRepository;
import com.goo32v2.cooldict.data.sources.interfaces.DataSource;
import com.goo32v2.cooldict.settings.SettingsActivity;
import com.goo32v2.cooldict.utils.ActivityUtils;
import com.goo32v2.cooldict.words.interfaces.WordPresenterContract;
import com.goo32v2.cooldict.words.interfaces.WordViewContract;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 14-May-16. (c) CoolDict
 */

public class WordsPresenter implements WordPresenterContract {
    private final WordRepository mWordsRepository;
    private final DictionaryRepository mDictionaryRepository;
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
    public void start(String dict) {
        if (dict == null){
            loadWords();
        } else{
            loadWords(dict);
        }
    }

    public void loadWords() {
        mWordsRepository.get(getCallback());
    }

    @Override
    public void loadWords(final String dictionaryName) {
        mDictionaryRepository.getByTitle(dictionaryName, new DataSource.GetEntryCallback<DictionaryModel>() {
            @Override
            public void onLoaded(DictionaryModel entry) {
                mWordsRepository.get(entry.getId(), getCallback());
            }

            @Override
            public void onDataNotAvailable() {
                getCallback().onDataNotAvailable();
            }
        });


    }

    private DataSource.GetListCallback<WordModel> getCallback(){
        return new DataSource.GetListCallback<WordModel>() {
            @Override
            public void onLoaded(List<WordModel> entry) {
                if (!mWordsView.isActive()) {
                    return;
                }
                mWordsView.showWords(entry);
            }

            @Override
            public void onDataNotAvailable() {
                if (!mWordsView.isActive()) {
                    return;
                }
                // TODO: 29-May-16 what about error?
                mWordsView.showNoWords();
            }
        };
    }

    @Override
    public void startAddNewWordActivity() {


    }

    @Override
    public void startWordDetailsActivity(@NonNull WordModel word) {
        checkNotNull(word);
        mWordsView.showWordDetailUi(word);
    }

    @Override
    public DictionaryModel getAllDictionaries() {
        // TODO: 17-May-16 Implement serialization for save state
        return null;
    }
}
