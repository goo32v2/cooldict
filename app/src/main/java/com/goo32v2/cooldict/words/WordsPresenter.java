package com.goo32v2.cooldict.words;

import android.support.annotation.NonNull;

import com.goo32v2.cooldict.data.models.DictionaryModel;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.data.sources.DictionaryRepository;
import com.goo32v2.cooldict.data.sources.WordRepository;
import com.goo32v2.cooldict.data.sources.interfaces.DataSource;
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
            loadWords(true);
        } else{
            loadWords(dict, true);
        }
    }

    @Override
    public void result(int requestCode, int resultCode) {
        // TODO: 29-May-16 implement when task added successful
    }

    public void loadWords(final boolean showLoadingUi) {
        if (showLoadingUi) {
            mWordsView.setLoadingIndicator(true);
        }

        mWordsRepository.get(getCallback(showLoadingUi));
    }

    @Override
    public void loadWords(final String dictionaryName, final boolean showLoadingUi) {
        if (showLoadingUi) {
            mWordsView.setLoadingIndicator(true);
        }

        mDictionaryRepository.getByTitle(dictionaryName, new DataSource.GetEntryCallback<DictionaryModel>() {
            @Override
            public void onLoaded(DictionaryModel entry) {
                mWordsRepository.get(entry.getId(), getCallback(showLoadingUi));
            }

            @Override
            public void onDataNotAvailable() {
                getCallback(showLoadingUi).onDataNotAvailable();
            }
        });


    }

    private DataSource.GetListCallback<WordModel> getCallback(final boolean showLoadingUi){
        return new DataSource.GetListCallback<WordModel>() {
            @Override
            public void onLoaded(List<WordModel> entry) {
                if (!mWordsView.isActive()) {
                    return;
                }
                if (showLoadingUi) {
                    mWordsView.setLoadingIndicator(false);
                }
                mWordsView.showWords(entry);
            }

            @Override
            public void onDataNotAvailable() {
                if (!mWordsView.isActive()) {
                    return;
                }
                if (showLoadingUi) {
                    mWordsView.setLoadingIndicator(false);
                }
                // TODO: 29-May-16 what about error?
                mWordsView.showNoWords();
            }
        };
    }

    @Override
    public void addNewWord() {
        mWordsView.showAddWord();
    }

    @Override
    public void openWordDetail(@NonNull WordModel word) {
        checkNotNull(word);
        mWordsView.showWordDetailUi(word);
    }

    @Override
    public DictionaryModel getCurrentDictionary() {
        // TODO: 17-May-16 Implement serialization for save state
        return null;
    }
}
