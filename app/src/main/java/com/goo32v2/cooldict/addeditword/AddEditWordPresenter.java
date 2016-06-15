package com.goo32v2.cooldict.addeditword;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.goo32v2.cooldict.addeditword.interfaces.AddEditWordPresenterContract;
import com.goo32v2.cooldict.addeditword.interfaces.AddEditWordViewContract;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.data.sources.WordRepository;
import com.goo32v2.cooldict.data.sources.interfaces.DataSource;
import com.goo32v2.cooldict.data.sources.interfaces.WordDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 13-Jun-16. (c) CoolDict
 */

public class AddEditWordPresenter implements AddEditWordPresenterContract, DataSource.GetEntryCallback<WordModel>{

    @NonNull
    private final WordDataSource mWordRepository;

    @NonNull
    private final AddEditWordViewContract mAddWordView;

    @Nullable
    private String mWordId;

    public AddEditWordPresenter(@Nullable String wordId, @NonNull WordRepository wordRepository,
                                @NonNull AddEditWordFragment addEditWordFragment) {
        mAddWordView = checkNotNull(addEditWordFragment);
        mWordRepository = checkNotNull(wordRepository);
        mWordId = wordId;

        mAddWordView.setPresenter(this);
    }

    @Override
    public void createWord(String originalWord, String translatedWord, String dictionaryId) {
        WordModel wordModel = new WordModel(originalWord, translatedWord,dictionaryId);
        mWordRepository.save(wordModel);
        mAddWordView.showWordList();
    }

    @Override
    public void updateWord(String wordId, String originalWord, String translatedWord, String dictionaryId) {
        if (mWordId == null) {
            throw new RuntimeException("updateWord() was called but word is new");
        }
        WordModel wordModel = new WordModel(wordId, originalWord, translatedWord,dictionaryId);
        mWordRepository.update(wordId, wordModel);
        mAddWordView.showWordList();
    }

    @Override
    public void populateWord() {
        if (mWordId == null) {
            throw new RuntimeException("populateWord() was called but word is new");
        }
        mWordRepository.get(mWordId, this);
    }

    @Override
    public void start() {
        if (mWordId != null) {
            populateWord();
        }
    }

    @Override
    public void onLoaded(WordModel entry) {
        if (mAddWordView.isActive()) {
//            mAddWordView.setWordId(entry.getId());
            mAddWordView.setOriginalWord(entry.getOriginalWord());
            mAddWordView.setTranslatedWord(entry.getTranslatedWord());
            mAddWordView.setDictionaryId(entry.getDictionaryID());
        }
    }

    @Override
    public void onDataNotAvailable() {
        if (mAddWordView.isActive()){
            mAddWordView.showEmptyWordError();
        }
    }
}
